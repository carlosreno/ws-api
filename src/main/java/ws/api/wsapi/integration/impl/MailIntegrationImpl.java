package ws.api.wsapi.integration.impl;

import jakarta.mail.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import ws.api.wsapi.integration.MailIntegration;

public class MailIntegrationImpl implements MailIntegration {

    
    private JavaMailSender javaMailSender;

    @Override
    public void send(String mailTo, String msg) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Acesso liberado");
        simpleMailMessage.setText("Login:davi");
        simpleMailMessage.setTo(mailTo);
    }
}
