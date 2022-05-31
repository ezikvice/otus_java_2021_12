package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

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
        Method[] methods = configClass.getDeclaredMethods();
        List<Method> methods1 = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(AppComponent.class))
                .sorted(Comparator.comparingInt(o -> o.getAnnotation(AppComponent.class).order()))
//                .map(method -> method.getReturnType())
                .toList();

        try {
            Object o = configClass.getConstructor().newInstance();
            for (Method method : methods1) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Object[] args = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    for (Object appComponent : appComponents) {
                        if (appComponent.getClass().getAnnotatedInterfaces()[0].getType().equals(parameterTypes[i])) {
                            args[i] = appComponent;
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

        for (Method method : methods1) {

        }
//        for (Method method : methods) {
//            if(method.isAnnotationPresent(AppComponent.class)){
//                AppComponent annotation = method.getAnnotation(AppComponent.class);
//
////                TODO: отсортировать по полю order у аннотации
//                appComponents.add(method);
//                appComponentsByName.put(method.getName(), method);
//            }
//        }

    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        for (Object appComponent : appComponents) {
            if (appComponent.getClass().equals(componentClass) ||
                    appComponent.getClass().getAnnotatedInterfaces()[0].getType().equals(componentClass)) {
                return (C) appComponent;
            }
        }
        return null;
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }
}
