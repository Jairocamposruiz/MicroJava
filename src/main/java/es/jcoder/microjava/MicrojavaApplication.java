package es.jcoder.microjava;

import es.jcoder.microjava.beans.MyBeanDependency;
import es.jcoder.microjava.beans.MyBeanWithDependenciesDependency;
import es.jcoder.microjava.beans.MyBeanWithProperties;
import es.jcoder.microjava.components.ComponentDependency;
import es.jcoder.microjava.pojos.UserPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicrojavaApplication implements CommandLineRunner {

//    Esta es la manera en la que lo he visto siempre usar sin necesidad de constructor pero parece que aconsejan
//    usarlo con constructor porque para testear es mas facil de inyectar los mocks es mas si usamos esta manera
//    el ide nos recomienda que lo cambiemos a el modo con Constructor
//    @Autowired
//    @Qualifier("componentTwoImplement")
//    private ComponentDependency componentDependency;
//
//    @Autowired
//    private MyBeanDependency myBeanDependency;
//
//    @Autowired
//    private MyBeanWithDependenciesDependency myBeanWithDependenciesDependency;
//
//    @Autowired
//    private MyBeanWithProperties myBeanWithProperties;


//    El autowired en versiones recientes de spring ya no es necesario ponerlo en el constructor en caso de ponerlo tampoco afecta a mal
//    La anotación @Qualifier sirve para especificar que implementacion usar si tenemos 2 que implementen la misma interfaz
    private final ComponentDependency componentDependency;
    private final MyBeanDependency myBeanDependency;
    private final MyBeanWithDependenciesDependency myBeanWithDependenciesDependency;
    private final MyBeanWithProperties myBeanWithProperties;
    private final UserPojo userPojo;

    Log LOGGER = LogFactory.getLog(MicrojavaApplication.class);

    @Autowired
    public MicrojavaApplication(
            @Qualifier("componentTwoImplement") ComponentDependency componentDependency,
            MyBeanDependency myBeanDependency,
            MyBeanWithDependenciesDependency myBeanWithDependenciesDependency,
            MyBeanWithProperties myBeanWithProperties,
            UserPojo userPojo
    ) {
        this.componentDependency = componentDependency;
        this.myBeanDependency = myBeanDependency;
        this.myBeanWithDependenciesDependency = myBeanWithDependenciesDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
    }

    public static void main(String[] args) {
        SpringApplication.run(MicrojavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        componentDependency.saludar();
        myBeanDependency.print();
        myBeanWithDependenciesDependency.printWithDependency();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getAge());
        System.out.println(userPojo.getEmail());
        System.out.println(userPojo.getPassword());
        LOGGER.error("This is an error");
        LOGGER.info(("This is only info"));
        LOGGER.debug("This is necessary info if we want to debug");
    }
}
