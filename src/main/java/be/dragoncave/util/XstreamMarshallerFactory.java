package be.dragoncave.util;

import be.dragoncave.domain.Country;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.xstream.XStreamMarshaller;

/**
 * Created by benoit on 02/11/2016.
 */
@Configuration
public class XstreamMarshallerFactory {

    /*
    <bean id="xstreamMarshallerBean" class="org.springframework.oxm.xstream.XStreamMarshaller">
    <property name="annotatedClasses" value="com.javatpoint.Employee"></property>
</bean>
</beans>
     */
    @Bean
    public XStreamMarshaller createXStreamMarshaller() {
        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        xStreamMarshaller.setAnnotatedClasses(Country.class);
        return xStreamMarshaller;
    }


}
