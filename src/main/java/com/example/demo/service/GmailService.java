
package com.example.demo.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Base64;


@Service
public class GmailService {

    private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TO_EMAIL_ADDRESS = "janarthananlyfc@gmail.com";

    @Value("${GMAIL_REFRESH_TOKEN}")
    private String refreshToken;

    @Value("${gmail.client.id}")
    private String clientId;

    @Value("${gmail.client.secret}")
    private String clientSecret;

    private Credential getCredentials() throws IOException, GeneralSecurityException {
        return new GoogleCredential.Builder()
                .setTransport(GoogleNetHttpTransport.newTrustedTransport())
                .setJsonFactory(JSON_FACTORY)
                .setClientSecrets(clientId, clientSecret)
                .build()
                .setRefreshToken(refreshToken);
    }

    public void sendResumeEmail(String recipientEmail) throws IOException, GeneralSecurityException, MessagingException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = getCredentials();
        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();

        MimeMessage mimeMessage = createEmail(recipientEmail, TO_EMAIL_ADDRESS);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        mimeMessage.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
        Message message = new Message();
        message.setRaw(encodedEmail);

        message = service.users().messages().send("me", message).execute();
        System.out.println("Message id: " + message.getId());
        System.out.println(message.toPrettyString());
    }

    private MimeMessage createEmail(String to, String from) throws MessagingException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
        email.setSubject("Resume Request");

        String resumeLink = "https://drive.google.com/uc?export=download&id=1om3_1K2IkuXjf0dmH32CVRW9NwD2ruei";
        String emailBody = "<p>Here is the link to my resume:</p><p><a href=\"" + resumeLink + "\">Download Resume</a></p>";
        email.setContent(emailBody, "text/html");

        return email;
    }
}
