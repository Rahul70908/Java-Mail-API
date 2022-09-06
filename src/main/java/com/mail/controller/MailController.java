package com.mail.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mail.pojo.Mail;
import com.mail.pojo.MailAttachment;
import com.mail.service.EmailSenderService;

@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	EmailSenderService emailSenderService;
	
	@PostMapping(path = "/sendMail")
	public String send(@RequestBody Mail mail) {
		return emailSenderService.sendSimpleEmail(mail);
	}
	
	@PostMapping(path = "/sendMailAttach")
	public String send(@RequestBody MailAttachment mail) throws MessagingException {
		return emailSenderService.sendAttachmentMail(mail);
	}
}