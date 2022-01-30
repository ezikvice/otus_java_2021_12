package ru.dimk;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestsRunner {

    private static List<Method> getAnnotatedMethods(Method[] methods, String annotationCanonicalName) {
        List<Method> annotatedMethods = new ArrayList<>();
        for (Method method : methods) {
            if (Arrays.stream(method.getAnnotations()).anyMatch(
                    annotation -> annotation.annotationType().getCanonicalName().equals(annotationCanonicalName))) {
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }


    public static void testClass(String className) {
        try {
            System.out.println("testing...");
            Class<?> c = Class.forName(className);
            Method[] methods = c.getMethods();
            List<Method> beforeMethods = getAnnotatedMethods(methods, "ru.dimk.annotations.Before");
            List<Method> afterMethods = getAnnotatedMethods(methods, "ru.dimk.annotations.After");
            List<Method> testMethods = getAnnotatedMethods(methods, "ru.dimk.annotations.Test");
//                for (Method method : methods) {
//                    if (Arrays.stream(method.getAnnotations()).anyMatch(annotation -> annotation.annotationType().getCanonicalName() == "ru.dimk.annotations.Before")) {
//                        beforeMethods.add(method);
//                    } else if (Arrays.stream(method.getAnnotations()).anyMatch(annotation -> annotation.annotationType().getCanonicalName() == "ru.dimk.annotations.After")) {
//                        afterMethods.add(method);
//                    } else if (Arrays.stream(method.getAnnotations()).anyMatch(annotation -> annotation.annotationType().getCanonicalName() == "ru.dimk.annotations.Test")) {
//                        testMethods.add(method);
//                    }
//                }
            System.out.println("Before methods: ");
            for (Method method : beforeMethods) {
                System.out.println(method.getName());
                try {

                } catch (Exception e) {

                }
            }
            System.out.println("Test methods: ");
            for (Method method : testMethods) {
                System.out.println(method.getName());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testClass("ru.dimk.tests.AnnotationsTest");

    }


}
