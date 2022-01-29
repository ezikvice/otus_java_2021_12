package ru.dimk;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestsRunner {




    public static void testClass(String className){
        try {
            System.out.println("testing...");
            Class<?> c = Class.forName(className);
            Method[] methods = c.getMethods();
            List<Method> beforeMethods = new ArrayList<>();
            List<Method> afterMethods = new ArrayList<>();
            for (Method method : methods) {
                if(Arrays.stream(method.getAnnotations()).anyMatch(annotation -> annotation.annotationType().getCanonicalName()=="ru.dimk.annotations.Before")) {
                    beforeMethods.add(method);
                }
                else if (Arrays.stream(method.getAnnotations()).anyMatch(annotation -> annotation.annotationType().getCanonicalName()=="ru.dimk.annotations.Before")) {
                    afterMethods.add(method);
                }

                System.out.println(method.getName() + ":" + method.getAnnotations());
                for (Annotation annotation : method.getAnnotations()) {
                    System.out.println(annotation.annotationType().getCanonicalName());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testClass("ru.dimk.tests.AnnotationsTest");
//        Class<?> clazz = AnnotationsTest.class;
//        System.out.println(clazz.getCanonicalName());

    }



}
