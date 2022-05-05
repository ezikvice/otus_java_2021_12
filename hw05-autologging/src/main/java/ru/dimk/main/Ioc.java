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
            Method[] declaredMethods = this.proxedClass.getClass().getDeclaredMethods();

            Optional<Method> methodFounded = Arrays.stream(declaredMethods)
                    .filter(method1 -> methodsAreEquals(method1, method)).findFirst();
            Method declaredMethod = methodFounded.orElse(null);

            if (declaredMethod != null && declaredMethod.isAnnotationPresent(Log.class)) {
                final String parametersString = Arrays.stream(args)
                        .map(Object::toString)
                        .collect(Collectors.joining(", ", "", ""));

                System.out.println("executed method:" + method.getName() + ", params: " + parametersString);
            }

            return method.invoke(proxedClass, args);
        }

        private boolean methodsAreEquals(Method first, Method second) {
            return first.getName().equals(second.getName())
                    && equalParamTypes(first.getParameterTypes(), second.getParameterTypes());
        }

        // стащил из java.base.java.lang.reflect.Executable.java:70
        private boolean equalParamTypes(Class<?>[] params1, Class<?>[] params2) {
            /* Avoid unnecessary cloning */
            if (params1.length == params2.length) {
                for (int i = 0; i < params1.length; i++) {
                    if (params1[i] != params2[i])
                        return false;
                }
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "testLogging=" + proxedClass +
                    '}';
        }
    }
}
