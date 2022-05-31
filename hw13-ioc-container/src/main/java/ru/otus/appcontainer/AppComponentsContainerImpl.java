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
                for (Class<?> parameterType : parameterTypes) {
                    for (Object appComponent : appComponents) {
                        System.out.println(appComponent.getClass() );
                    }
                }
                Object invoked = method.invoke(o, parameterTypes);
                String name = method.getAnnotation(AppComponent.class).name();
                appComponents.add(invoked);
                appComponentsByName.put(name, invoked);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
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
//        TODO: сделать правильно
//        for (Object appComponent : appComponents) {
//            if(appComponent.equals(componentClass)){
//                return appComponent;
//            }
//        }
        return (C)appComponents.get(0);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C)appComponentsByName.get(componentName);
    }
}
