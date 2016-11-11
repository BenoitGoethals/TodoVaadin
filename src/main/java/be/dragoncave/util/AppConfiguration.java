package be.dragoncave.util;

import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by benoit on 04/11/2016.
 */
@SpringComponent
public class AppConfiguration implements ApplicationContextAware {

    private static ApplicationContext context;

    private AppConfiguration() {
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        context = ctx;
    }

    public static <T> T getBean(String name, Class<T> aClass) {
        return context.getBean(name, aClass);
    }
}