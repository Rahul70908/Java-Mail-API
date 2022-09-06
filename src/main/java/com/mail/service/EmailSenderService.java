package com.mail.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mail.exception.MailMessageException;
import com.mail.pojo.Mail;
import com.mail.pojo.MailAttachment;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String from;

	public String sendSimpleEmail(Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mail.getToEmail());
		message.setText(mail.getBody());
		message.setSubject(mail.getSubject());
		message.setFrom(from);
		try {
			mailSender.send(message);
			return "Mail Sent...";
		} catch (Exception e) {
			throw new MailMessageException("Error Sending Mail " + e.getMessage());
		}
	}

	public String sendAttachmentMail(MailAttachment mailAttachment) throws MessagingException {
		
		Resource resource = null;
		MimeMessage mimeMessage = null;
		MimeMessageHelper helper = null;
		try {
			mimeMessage = mailSender.createMimeMessage();
			resource = new ClassPathResource("templates/"+mailAttachment.getAttachment());
			helper = new MimeMessageHelper(mimeMessage, true);
			if(resource.exists()) {
				helper.addAttachment(resource.getFilename(), resource);	
			}		
			helper.setFrom(from);
			helper.setTo(mailAttachment.getToEmail());
			helper.setText(mailAttachment.getBody());
			helper.setSubject(mailAttachment.getSubject());
			mailSender.send(mimeMessage);
			return "Mail Sent with Attachment...";
		} catch (MessagingException e) {
			throw new MailMessageException("Can't Send Mail " + e.getMessage());
		}
	}
}