package be.dragoncave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

/**
 * Created by benoit on 7/11/2016.
 */
@Profile("test-profile")
@Configuration
@ComponentScan(
        basePackages="be.dragoncave",
        excludeFilters = {
                @ComponentScan.Filter(type = ASSIGNABLE_TYPE,
                        value = {
                                ToDoApplication.class,

                        })
        })
public class SpecificTestConfig {
    public static void main(String[] args) {
        SpringApplication.run(SpecificTestConfig.class, args);

    }
}
