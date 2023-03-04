package es.jcoder.microjava.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependenciesImplement implements MyBeanWithDependenciesDependency {

    Log LOGGER = LogFactory.getLog(MyBeanWithDependenciesImplement.class);

    private final MyOperationDependency myOperationDependency;

    public MyBeanWithDependenciesImplement(MyOperationDependency myOperationDependency) {
        this.myOperationDependency = myOperationDependency;
    }

    @Override
    public void printWithDependency() {
        int number = 9;
        int response = myOperationDependency.sum(number);
        LOGGER.info("We are in method pringWithDependency in the class MyBeanWithDependenciesImplement");
        LOGGER.debug("The number is " + number + " and the result is " + response);
        System.out.println(response);
        System.out.println("Hello from of the implementation of a bean with dependencies");

        try {
            int value = 10 / 0;
        } catch (Exception e) {
            LOGGER.error("Error in the app with message: " + e.getMessage());
        }
    }
}
