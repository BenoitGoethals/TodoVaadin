package be.dragoncave.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.subethamail.wiser.Wiser;

import javax.mail.internet.MimeMessage;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by benoi on 3/11/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailUtilImplTest {

    @Autowired
    private MailUtil mailUtil;

    private Wiser wiser;

    @Before
    public void setup(){
         wiser = new Wiser();
        wiser.setPort(1026);
        wiser.setHostname("localhost");
        wiser.start();
    }

    @Test
    public void send() throws Exception {
        mailUtil.send("benoit.goethals","Here is a sample subject !","sdfsfsd");
        assertThat(wiser.getMessages()).hasSize(1);
        MimeMessage message = wiser.getMessages().iterator().next().getMimeMessage();
        assertThat(message.getSubject()).isEqualTo("Here is a sample subject !");
    }

    @After
    public void shutdown(){
        wiser.stop();
        wiser=null;
    }

}