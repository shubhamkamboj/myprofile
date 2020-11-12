package net.codejava.mailSender;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendMail {

	  @Autowired
	    private JavaMailSender javaMailSender;
	  
	  
	public void sendEmail(String to,String subject,String text) {
		
		 SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(to);
	        msg.setSubject(subject);
	        msg.setText(text);

	        javaMailSender.send(msg);
	}
	
	 void sendEmailWithAttachment() throws MessagingException, IOException{
		 MimeMessage msg = javaMailSender.createMimeMessage();

	        // true = multipart message
	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
	        helper.setTo("1@gmail.com");

	        helper.setSubject("Testing from Spring Boot");

	        helper.setText("<h1>Check attachment for image!</h1>", true);

	        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

	        javaMailSender.send(msg);	
	 }
	
	
	
	
}
