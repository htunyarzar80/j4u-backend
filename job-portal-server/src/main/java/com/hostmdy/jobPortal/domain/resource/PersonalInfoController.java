package com.hostmdy.jobPortal.domain.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.jobPortal.domain.ApplicationRequest;
import com.hostmdy.jobPortal.domain.Applications;
import com.hostmdy.jobPortal.domain.Education;
import com.hostmdy.jobPortal.domain.Experiences;
import com.hostmdy.jobPortal.domain.JobPost;
import com.hostmdy.jobPortal.domain.PersonalInfo;
import com.hostmdy.jobPortal.domain.PersonalInfoRequest;
import com.hostmdy.jobPortal.domain.User;
import com.hostmdy.jobPortal.domain.jobenum.JobStatus;
import com.hostmdy.jobPortal.service.EducationService;
import com.hostmdy.jobPortal.service.ExperienceSevice;
import com.hostmdy.jobPortal.service.PersonalInfoService;
import com.hostmdy.jobPortal.service.impl.PersonalInfoServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/info")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PersonalInfoController {
	
	private final PersonalInfoService personalInfoService;
	private final EducationService educationService;
	private final ExperienceSevice experienceSevice;
	

	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody PersonalInfo perInfo){
		PersonalInfo savedPerInfo = personalInfoService.save(perInfo);
		
		return new ResponseEntity<PersonalInfo>(savedPerInfo,HttpStatus.CREATED);
	}
	
	@PostMapping("/submit")
	public ResponseEntity<String> submitApplication(@RequestBody PersonalInfoRequest applicationRequest) {
	    try {
	        PersonalInfo personalInfo = applicationRequest.getPersonalInfo();
	        System.out.println("Person Info"+personalInfo.toString());
	        List<Education> educationList = applicationRequest.getEducationLst();
	        System.out.println("Educaiton list size is "+educationList.size());
	        List<Experiences> experienceList = applicationRequest.getExperiencesLst();
	        System.out.println("experiecne list size is "+experienceList.size());
	        User user= applicationRequest.getUser();
	      
	        
	        System.out.println(educationList.size());
	        System.out.println("USER ID IS"+user.getId());
	       	        //save the personal Info
	        PersonalInfo savedPersonalInfo= personalInfoService.save(personalInfo,user);
	        System.out.println("Finished save personal Info");
	        System.out.println("new id of personal info is"+savedPersonalInfo.getId());
	        

	         
	        // Save the education entities
	        for (Education education : educationList) {
	        	System.out.println("in the for each loop for education");
	        	education.setPersonalInfo(personalInfo);
	        	personalInfo.getEducations().add(education);
	            educationService.saveEducation(education);
	        }

	        // Save the experience entities
	        for (Experiences experience : experienceList) {
	        	System.out.println("in the for each loop for experiences");
	        	experience.setPersonalInfo(personalInfo);
	        	personalInfo.getExperiences().add(experience);
	        	experienceSevice.save(experience);
	        }

	       
	       
// Save the personal info entity
	        
	        
	        
	        
	        return ResponseEntity.ok("PersonalInfo submitted successfully!");
	    } catch (Exception e) {
	    	System.out.println("In the catch");
	    	e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit personalInfo.");
	    }
	}
 
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> findPerInfoById(@PathVariable Long id){
		Optional<PersonalInfo> perInfoOpt = personalInfoService.findPersonalInfoById(id);
		
		if(perInfoOpt.isEmpty())
			return new ResponseEntity<String>("PerInfo not found",HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<PersonalInfo>(perInfoOpt.get(),HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllPerInfos(){
		List<PersonalInfo> perInfoList = personalInfoService.findPersonalInfo();
		
		if(perInfoList.isEmpty())
			return new ResponseEntity<String>("no perInfo found",HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<List<PersonalInfo>>(perInfoList,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePerInfoById(@PathVariable Long id) {
		
		
		personalInfoService.deletePersonalInfoById(id);
		
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	
	}
	
	
}
