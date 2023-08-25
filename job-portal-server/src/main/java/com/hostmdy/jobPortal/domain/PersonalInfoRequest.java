package com.hostmdy.jobPortal.domain;

import java.util.List;

import lombok.Data;

@Data
public class PersonalInfoRequest {
	PersonalInfo personalInfo;
	List<Experiences> experiencesLst;
	List<Education> educationLst;
	User user;

}
