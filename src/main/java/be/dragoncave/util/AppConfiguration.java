package be.dragoncave.util;

import be.dragoncave.domain.*;
import be.dragoncave.persistance.CountryRepository;
import be.dragoncave.service.TaskService;
import be.dragoncave.service.UserService;
import be.dragoncave.util.CountryConverter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by benoit on 04/11/2016.
 */
@Component
public class AppConfiguration implements ApplicationContextAware {

    private static ApplicationContext context;

    private AppConfiguration(){}

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public  static <T> T getBean(String name,Class<T> aClass){
        return context.getBean(name,aClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        context = ctx;
    }
}