
package com.dcl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.dcl.service.MailSender;

@Service
public class MailSenderServiceImpl implements MailSender{
	@Autowired
private JavaMailSender mailsender;

	@Override
	public void sendmail(String to, String subject, String body) {
	SimpleMailMessage message=new SimpleMailMessage();
	message.setTo(to);
	message.setSubject(subject);
	message.setText(body);
	mailsender.send(message);
		
	}

}
