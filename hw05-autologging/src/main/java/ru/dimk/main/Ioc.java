package ru.dimk.main;

import ru.dimk.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

class Ioc {

    private Ioc() {
    }

    static TestLogging createProxedClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLoggingImpl());
        return (TestLogging) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLogging.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLogging proxedClass;

        DemoInvocationHandler(TestLogging proxedClass) {
            this.proxedClass = proxedClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method myMethod = proxedClass.getClass().getMethod(method.getName(), method.getParameterTypes());
            if (myMethod.isAnnotationPresent(Log.class)) {
                final String parametersString = Arrays.stream(args)
                        .map(Object::toString)
                        .collect(Collectors.joining(", ", "", ""));

                System.out.println("executed method:" + method.getName() + ", params: " + parametersString);
            }
            return method.invoke(proxedClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "testLogging=" + proxedClass +
                    '}';
        }
    }
}
