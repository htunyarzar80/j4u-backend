package com.hostmdy.jobPortal.domain.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.jobPortal.domain.payload.AttachmentEmailRequest;
import com.hostmdy.jobPortal.domain.payload.OrderConfirmEmailRequest;
import com.hostmdy.jobPortal.domain.payload.SimpleEmailRequest;
import com.hostmdy.jobPortal.service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailController {
	private final EmailService emailService;
	
	@PostMapping("/contact")
	public ResponseEntity<String> sendEmail(@RequestBody SimpleEmailRequest emailRequest){
		emailService.sendEmail(emailRequest);
		
		return ResponseEntity.ok("Email Sent");
	}
	
	@PostMapping("/order")
	public ResponseEntity<String> sendOrderConfirmEmail(@RequestBody OrderConfirmEmailRequest
			emailRequest){
		emailService.sendTemplateEmail(emailRequest);
		
		return ResponseEntity.ok("Email Sent");
	}
	
	@PostMapping("/attachment")
	public ResponseEntity<String> sendAttachmentEmail(@RequestBody AttachmentEmailRequest emailRequest){
		emailService.sendAttachmentEmail(emailRequest);
		
		return ResponseEntity.ok("Email Sent");
	}
	
	

}