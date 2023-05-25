package com.mustycodified.Reservlyv1be.services.impl;

import com.mustycodified.Reservlyv1be.ReservlyV1BeApplication;
import com.mustycodified.Reservlyv1be.dtos.requests.JavaMailDto;
import com.mustycodified.Reservlyv1be.services.JavaMailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Date;
import java.util.Hashtable;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class JavaMailServiceImpl implements JavaMailService {

    private final JavaMailSender mailSender;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservlyV1BeApplication.class);
    private static final Marker IMPORTANT = MarkerFactory.getMarker("IMPORTANT");

    @Override
    public ResponseEntity<String> sendMail(JavaMailDto mailDto)  {

        if (!isValidEmail(mailDto.getReceiverEmail()))
            new ResponseEntity<>("Email is not valid", HttpStatus.BAD_REQUEST);

        isEmailDomainValid(mailDto.getReceiverEmail());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("reservlyloding@gmail.com");
        message.setTo(mailDto.getReceiverEmail());
        message.setSentDate(new Date());
        message.setSubject(mailDto.getSubject());
        message.setText(mailDto.getBody());

        try {
            LOGGER.info("Beginning of log *********");
            LOGGER.info(IMPORTANT, "Sending email to: " + mailDto.getReceiverEmail());
            mailSender.send(message);
            return new ResponseEntity<>("Sent", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(IMPORTANT, e.getMessage());
        }

        return new ResponseEntity<>("An Error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public boolean isValidEmail(String email) {
        String regexPattern = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }
    public void isEmailDomainValid(String email) {
        try {
            String domain = email.substring(email.lastIndexOf('@') + 1);
            int result = doLookup(domain);
            LOGGER.info("Domain: " + domain);
            LOGGER.info("Result of domain: " + result);
        } catch (NamingException e) {
            LOGGER.error(e.getMessage());
        }
    }
    public int doLookup(String hostName) throws NamingException {
        Hashtable<String, String> env = new Hashtable<>();
        env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
        DirContext ictx = new InitialDirContext( env );
        Attributes attrs = ictx.getAttributes( hostName, new String[] { "MX" });
        Attribute attr = attrs.get( "MX" );
        if( attr == null ) return( 0 );
        return( attr.size() );
    }
}
