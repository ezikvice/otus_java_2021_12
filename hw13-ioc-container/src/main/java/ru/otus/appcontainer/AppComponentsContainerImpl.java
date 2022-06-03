package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        // You code here...
        List<Method> declaredMethods = Arrays.stream(configClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(AppComponent.class))
                .sorted(Comparator.comparingInt(o -> o.getAnnotation(AppComponent.class).order()))
                .toList();

        try {
            Object o = configClass.getConstructor().newInstance();
            for (Method method : declaredMethods) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Object[] args = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    for (Object appComponent : appComponents) {
                        AnnotatedType[] annotatedInterfaces = appComponent.getClass().getAnnotatedInterfaces();
                        for (AnnotatedType annotatedInterface : annotatedInterfaces) {
                            if (annotatedInterface.getType().equals(parameterTypes[i])) {
                                args[i] = appComponent;
                            }
                        }
                    }
                }

                Object invoked = method.invoke(o, args);
                String name = method.getAnnotation(AppComponent.class).name();
                appComponents.add(invoked);
                appComponentsByName.put(name, invoked);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        for (Object appComponent : appComponents) {
            AnnotatedType[] annotatedInterfaces = appComponent.getClass().getAnnotatedInterfaces();
            for (AnnotatedType annotatedInterface : annotatedInterfaces) {
                if (appComponent.getClass().isAssignableFrom(componentClass) ||
                        annotatedInterface.getType().equals(componentClass)) {
                    return (C) appComponent;
                }
            }
        }
        return null;
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }
}
