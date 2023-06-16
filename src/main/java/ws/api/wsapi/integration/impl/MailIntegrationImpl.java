package ws.api.wsapi.integration.impl;

import jakarta.mail.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ws.api.wsapi.integration.MailIntegration;
@Component
public class MailIntegrationImpl implements MailIntegration {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(String mailTo, String msg,String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(msg);
        simpleMailMessage.setTo(mailTo);
        javaMailSender.send(simpleMailMessage);
    }
}
