package es.jcoder.microjava.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:connection.properties")
public class DatabaseConfiguration {

    @Value("${db.url}")
    private String url;

    @Value("${db.driver}")
    private String driver;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Bean
    public DataSource getDataSource(){
        return DataSourceBuilder.create()
                .driverClassName(driver)
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}
