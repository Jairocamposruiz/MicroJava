package es.jcoder.microjava.beans;

public class MyBeanTwoImplement implements MyBeanDependency{
    @Override
    public void print() {
        System.out.println("Hello from my second own implementation of the bean");
    }
}
