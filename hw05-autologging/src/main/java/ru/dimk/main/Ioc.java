package ru.dimk.main;

import ru.dimk.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.stream.Collectors;

class Ioc {

    private Ioc() {
    }

    static <T> T createProxedClass(Class<T> clazz) {
        Class<?>[] interfaces = clazz.getInterfaces();
        Class<?> anInterface = interfaces[0];
        InvocationHandler handler = new DemoInvocationHandler(clazz);
        return (T) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{anInterface}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final Class<?> proxedClass;

        DemoInvocationHandler(Class<?> proxedClass) {
            this.proxedClass = proxedClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method myMethod = proxedClass.getMethod(method.getName(), method.getParameterTypes());
            if (myMethod.isAnnotationPresent(Log.class)) {
                final String parametersString = Arrays.stream(args)
                        .map(Object::toString)
                        .collect(Collectors.joining(", ", "", ""));

                System.out.println("executed method:" + method.getName() + ", params: " + parametersString);
            }
            return method.invoke(proxedClass.newInstance(), args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "testLogging=" + proxedClass +
                    '}';
        }
    }
}
