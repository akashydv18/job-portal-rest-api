package com.jobportal.service;
import com.jobportal.entity.*;
import com.jobportal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApplicationService {

	 @Autowired
	    private ApplicationRepository applicationRepository;

	    @Autowired
	    private JobRepository jobRepository;

	    @Autowired
	    private UserRepository userRepository;

	 
	    public Application applyForJob(Long jobId, String seekerEmail) {

	        User seeker = userRepository.findByEmail(seekerEmail).get();
	        Job job = jobRepository.findById(jobId).get();

	       
	        if (applicationRepository.existsBySeekerAndJob(seeker, job)) {
	            throw new RuntimeException("Already applied for this job!");
	        }

	        Application application = new Application();
	        application.setJob(job);
	        application.setSeeker(seeker);
	        application.setStatus(ApplicationStatus.PENDING);

	        return applicationRepository.save(application);
	    }

	    // JOB_SEEKER — Apni saari applications dekho
	    public List<Application> getMyApplications(String seekerEmail) {
	        User seeker = userRepository.findByEmail(seekerEmail).get();
	        return applicationRepository.findBySeeker(seeker);
	    }

	  
	    public List<Application> getJobApplications(Long jobId) {
	        Job job = jobRepository.findById(jobId).get();
	        return applicationRepository.findByJob(job);
	    }

	  
	    public Application updateStatus(Long applicationId, ApplicationStatus status) {
	        Application application = applicationRepository.findById(applicationId).get();
	        application.setStatus(status);
	        return applicationRepository.save(application);
	    }
}
