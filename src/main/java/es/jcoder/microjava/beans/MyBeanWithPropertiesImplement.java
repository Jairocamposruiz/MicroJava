package es.jcoder.microjava.beans;

public class MyBeanWithPropertiesImplement implements MyBeanWithProperties {
    private final String name;
    private final String lastname;

    public MyBeanWithPropertiesImplement(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    @Override
    public String function() {
        return name + "-" + lastname;
    }
}
