package ru.dimk;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestsRunner {

    private List<Method> getAnnotatedMethods(Method[] methods, String annotationCanonicalName) {
        List<Method> annotatedMethods = new ArrayList<>();
        for (Method method : methods) {
            if (Arrays.stream(method.getAnnotations()).anyMatch(
                    annotation -> annotation.annotationType().getCanonicalName().equals(annotationCanonicalName))) {
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }

    private  List<Method> getBeforeAnnotationMethods(Method[] methods) {
        return this.getAnnotatedMethods(methods, "ru.dimk.annotations.Before");
    }

    private  List<Method> getAfterAnnotationMethods(Method[] methods) {
        return this.getAnnotatedMethods(methods, "ru.dimk.annotations.After");
    }

    private  List<Method> getTestAnnotationMethods(Method[] methods) {
        return this.getAnnotatedMethods(methods, "ru.dimk.annotations.Test");
    }



    public static void testClass(String className) {
        try {
            System.out.println("testing class " + className + "...");
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getMethods();
            TestsRunner me = new TestsRunner();
            List<Method> beforeMethods = me.getBeforeAnnotationMethods(methods);
            List<Method> afterMethods = me.getAfterAnnotationMethods(methods);
            List<Method> testMethods = me.getTestAnnotationMethods(methods);

            int goodTests = 0;
            int badTests = 0;
            Object obj = null;
            for (Method method : testMethods) {
                System.out.println("testing method: " + method.getName());
                try {
                    obj = clazz.getConstructor().newInstance();
                    for (Method beforeMethod : beforeMethods) {
                        beforeMethod.invoke(obj, null);
                    }
                    method.invoke(obj, null);
                } catch (InstantiationException e) {
                    throw e;
                } catch (Exception e) {
                    badTests += 1;
                    System.out.println("FAIL");
                    continue;
                } finally {
                    for (Method afterMethod : afterMethods) {
                        afterMethod.invoke(obj, null);
                    }
                }
                System.out.println("OK!");
                goodTests++;
            }

            System.out.println("passed: " + goodTests + ", FAILED: " + badTests + ", All: " + (goodTests + badTests));
            System.out.println("----------------------------\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testClass("ru.dimk.tests.AnnotationsTest");
        testClass("ru.dimk.tests.FailBeforeTest");

    }


}
