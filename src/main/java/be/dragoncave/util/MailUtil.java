package be.dragoncave.util;

import org.springframework.mail.SimpleMailMessage;

/**
 * Created by benoit on 04/11/2016.
 */
public interface MailUtil {
    SimpleMailMessage send(String dest, String subject, String body);

    SimpleMailMessage send(SimpleMailMessage mailMessage);
}
