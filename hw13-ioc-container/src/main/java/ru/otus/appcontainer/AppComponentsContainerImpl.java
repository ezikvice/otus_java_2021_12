package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

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
        List<? extends Class<?>> classes = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(AppComponent.class))
                .sorted(Comparator.comparingInt(o -> o.getAnnotation(AppComponent.class).order()))
                .map(method -> method.getReturnType())
                .toList();
        for (Method method : methods) {
            if(method.isAnnotationPresent(AppComponent.class)){
                AppComponent annotation = method.getAnnotation(AppComponent.class);

//                TODO: отсортировать по полю order у аннотации
                appComponents.add(method);
                appComponentsByName.put(method.getName(), method);
            }
        }

    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
//        TODO: сделать правильно
        return (C)appComponents.get(0);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C)appComponentsByName.get(componentName);
    }
}
