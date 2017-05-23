package com.mebitech.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mebitech.core.api.configuration.IConfigurationService;
import com.mebitech.core.api.mail.IMailService;

/**
 * This class works as a service providing convenience method for sending
 * e-mails. Any bundle/plugin can use it by including its property in the
 * blueprint.xml
 */
public class MailServiceImpl implements IMailService {

    private Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    private IConfigurationService configurationService;

    private List<String> mailList;

    private String obj,subj;

    private ExtendedJobDetailImpl extendedJobDetail=new ExtendedJobDetailImpl();

    @Override
    public void sendMail(List<String> toList,List<String> attachment, String subject, String body) {
        sendMail(toList,attachment, subject, body, "text/plain; charset=ISO-8859-9");
    }

    @Override
    public void sendMail(List<String> toList,  List<String> attachment, String subject, String body, String contentType) {



/*
        if (toList == null || toList.isEmpty()) {
            throw new IllegalArgumentException("toList was null!");
        }

        logger.info("Sending mail to: {}, with subject: {} and body: {}", new Object[]{toList, subject, body});

        Properties properties;
        Session session;
        MimeMessage message;
        Address[] addresses;

        try {
            // Setup mail properties
            properties = System.getProperties();
            String mailPropertyPrefix = configurationService.getMailSmtpSslEnable() ? "mail.smtps." : "mail.smtp.";
            properties.put(mailPropertyPrefix + "port", configurationService.getMailSmtpPort().toString());
            properties.put(mailPropertyPrefix + "auth", configurationService.getMailSmtpAuth() ? "true" : "false");
            properties.put(mailPropertyPrefix + "starttls.enable",
                    configurationService.getMailSmtpStartTlsEnable() ? "true" : "false");
            //properties.put(mailPropertyPrefix + "connectiontimeout", configurationService.getMailSmtpConnTimeout());
            //properties.put(mailPropertyPrefix + "timeout", configurationService.getMailSmtpTimeout());
            //properties.put(mailPropertyPrefix + "writetimeout", configurationService.getMailSmtpWriteTimeout());
            properties.put(mailPropertyPrefix + "ssl.enable", configurationService.getMailSmtpSslEnable());
            logger.debug("Mail service properties have been setup.");

            // Setup mail sender & recipients
            session = Session.getDefaultInstance(properties, null);
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(configurationService.getMailAddress(), "Mebitech"));
            for (String recipient : toList) {
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(recipient.toLowerCase(Locale.ENGLISH).trim()));
            }

            message.setSubject(subject);
            message.setContent(body, contentType);

            // Get SMTP transport
            Transport transport = session.getTransport("smtp");
            // Enter your correct GMail UserID and Password
            transport.connect(configurationService.getMailHost().toString(), configurationService.getMailAddress().toString(),
                    configurationService.getMailPassword().toString());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }*/

        Properties props = new Properties();
        props.put("mail.smtp.host", configurationService.getMailHost());
        props.put("mail.smtp.socketFactory.port", configurationService.getMailSmtpPort());
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", configurationService.getMailSmtpAuth());
        props.put("mail.smtp.port", configurationService.getMailSmtpPort());

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(configurationService.getMailAddress().toString(),configurationService.getMailPassword().toString());
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(configurationService.getMailAddress()));
            for (String recipient : toList) {
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(recipient.toLowerCase(Locale.ENGLISH).trim()));
            }

            Multipart multipart = new MimeMultipart();
            if(attachment.size()!=0){
                for (String attach : attachment) {
                    MimeBodyPart messageBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(attach);
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(source.getName());
                    multipart.addBodyPart(messageBodyPart);
                }
            }

            // Send the complete message parts
            message.setContent(body,contentType);
            message.setContent(multipart);
            message.setSubject(subject);
           /* message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler," +
                    "\n\n No spam to my email, please!");*/

            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void sendMail(List<String> toList, String subject, String body, String contentType) {

        Properties props = new Properties();
        props.put("mail.smtp.host", configurationService.getMailHost());
        props.put("mail.smtp.socketFactory.port", configurationService.getMailSmtpPort());
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", configurationService.getMailSmtpAuth());
        props.put("mail.smtp.port", configurationService.getMailSmtpPort());

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(configurationService.getMailAddress().toString(),configurationService.getMailPassword().toString());
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(configurationService.getMailAddress()));
            for (String recipient : toList) {
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(recipient.toLowerCase(Locale.ENGLISH).trim()));
            }

            // Send the complete message parts
            message.setContent(body,contentType);
            message.setSubject(subject);
           /* message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler," +
                    "\n\n No spam to my email, please!");*/

            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

    public void setMailList(List<String> mailList) {
        this.mailList = mailList;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }

    public void setConfigurationService(IConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ExtendedJobDetailImpl detail = ((ExtendedJobDetailImpl)context.getJobDetail());
        this.configurationService = detail.getConfigurationService();
        sendMail(detail.getMailList(),detail.getObj(),detail.getSubj(),"text/plain; charset=ISO-8859-9");
    }


}
