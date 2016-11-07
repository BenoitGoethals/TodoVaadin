package be.dragoncave.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Created by benoi on 3/11/2016.
 */

@Component
public class MailUtilImpl implements MailUtil {

    private final JavaMailSender javaMailSender;

    @Autowired
    MailUtilImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public SimpleMailMessage send(String dest, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(dest);
        mailMessage.setReplyTo("someone@localhost");
        mailMessage.setFrom("someone@localhost");
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        javaMailSender.send(mailMessage);
        return mailMessage;
    }

    @Override
    public SimpleMailMessage send(SimpleMailMessage mailMessage) {

        javaMailSender.send(mailMessage);
        return mailMessage;
    }
}
