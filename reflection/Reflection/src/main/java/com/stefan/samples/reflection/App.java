package com.stefan.samples.reflection;

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
    }
}
