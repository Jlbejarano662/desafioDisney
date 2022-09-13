package com.alkemy.disney.service.impl;

import com.alkemy.disney.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Environment env;

    @Value("${alkemy.disney.email.sender}")
    private String emailSender;

    @Value("${alkemy.disney.email.enable}")
    private boolean enable;

    @Override
    public void sendWelcomeEmailTo(String to) {
        if (!enable) {
            return;
        }
        String apikey = env.getProperty("EMAIL_API_KEY");
        Email fromEmanil = new Email(emailSender);
        Email toEmail = new Email(to);
        Content content = new Content(
            "text/plain",
                "Bienvenido/a a Alkemy Disney"
        );
        String subject = "Alkemy Disney";
        Mail mail = new Mail(fromEmanil, subject, toEmail, content);
        SendGrid sg = new SendGrid(apikey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println("Error al enviar el email");
        }
    }

}
