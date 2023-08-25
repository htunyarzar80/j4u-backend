package com.hostmdy.jobPortal.service;

import com.hostmdy.jobPortal.domain.payload.AttachmentEmailRequest;
import com.hostmdy.jobPortal.domain.payload.OrderConfirmEmailRequest;
import com.hostmdy.jobPortal.domain.payload.SimpleEmailRequest;

public interface EmailService {
	
	void sendEmail(SimpleEmailRequest email);
	
	void sendTemplateEmail(OrderConfirmEmailRequest email);
	
	void sendAttachmentEmail(AttachmentEmailRequest email);

}