package es.jcoder.microjava.configurations;

import es.jcoder.microjava.beans.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBeanDependency beanOperation() {
//        return new MyBeanImplement();
        return new MyBeanTwoImplement();
    }

    @Bean
    public MyOperationDependency beanOperationOperation() {
        return new MyOperationImplement();
    }

    // En este caso tenemos que agregar que depencencias tiene este Bean
    @Bean
    public MyBeanWithDependenciesDependency beanWithDependenciesOperation(MyOperationDependency myOperationDependency) {
        return new MyBeanWithDependenciesImplement(myOperationDependency);
    }
}
