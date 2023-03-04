package es.jcoder.microjava.beans;

public class MyOperationImplement implements MyOperationDependency {
    @Override
    public int sum(int number) {
        return number + 1;
    }
}
