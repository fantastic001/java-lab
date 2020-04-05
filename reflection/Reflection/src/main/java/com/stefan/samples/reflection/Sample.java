package com.stefan.samples.reflection;

@SampleAnnotation(key = "something", value = "A")
public class Sample implements ISample {
    public void print() {
        System.out.println("Hello, world");
    }
    public String a;
}