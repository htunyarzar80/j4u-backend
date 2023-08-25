package com.hostmdy.jobPortal.domain.payload;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderConfirmEmailRequest {
	private String to;
	private String subject;
	private OrderRequest orderRequest;
}