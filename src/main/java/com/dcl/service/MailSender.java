package com.dcl.service;

public interface MailSender {

	void sendmail(String to,String subject,String body);
}
