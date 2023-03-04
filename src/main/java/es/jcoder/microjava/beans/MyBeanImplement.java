package es.jcoder.microjava.beans;

public class MyBeanImplement implements MyBeanDependency {
    @Override
    public void print() {
        System.out.println("Hello from my own implementation of the bean");
    }
}
