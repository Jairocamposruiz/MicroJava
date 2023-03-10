package es.jcoder.microjava.configurations;

import es.jcoder.microjava.caseuse.GetUser;
import es.jcoder.microjava.caseuse.GetUserImplement;
import es.jcoder.microjava.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
