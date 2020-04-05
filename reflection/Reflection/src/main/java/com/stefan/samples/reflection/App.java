package com.stefan.samples.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            Class cls = Class.forName("com.stefan.samples.reflection.Sample");
            for (Class i : cls.getInterfaces()) {
                System.out.println(i.getName());
            }
            for (Annotation ann : cls.getDeclaredAnnotations()) {
                System.out.println("Annotated with: " + ann.annotationType().getName());
                SampleAnnotation annotation = (SampleAnnotation) ann;
                System.out.println("\tKey is: " + annotation.key());
                System.out.println("\tValue is: " + annotation.value());
            }
            for (Field field : cls.getFields()) {
                System.out.println(field.getName() + ": " + field.getType().getName());
            }
            ISample sample;
            try {
                sample = (ISample) cls.getConstructor().newInstance();
                sample.print();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Sample sampleObject = new Sample();
        sampleObject.a = "haha";
        Class<?> objCls = sampleObject.getClass();
        for (Field f : objCls.getDeclaredFields()) {
            try {
                System.out
                        .println("For concrete object field " + f.getName() + " is " + f.get(sampleObject).toString());
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        FileClassLoader loader = new FileClassLoader();
        try {
            System.out.println("Class name which is found: "
                    + loader.loadClass("com.stefan.samples.reflection.Sample").getName());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
