package com.hostmdy.jobPortal.domain.payload;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AttachmentEmailRequest {
	String to;
	String subject;
	String filePath;
	String text;
}