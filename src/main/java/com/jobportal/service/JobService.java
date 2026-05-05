package com.jobportal.service;

import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    public Job postJob(Job job, String companyEmail) {
     
        User company = userRepository.findByEmail(companyEmail)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Company not found: " + companyEmail
                ));
        job.setCompany(company);
        return jobRepository.save(job); 
    }

    public Page<Job> getAllJobs(int page, int size) {
    
        return jobRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Job> getJobsByLocation(String location, int page, int size) {
        return jobRepository.findByLocation(
            location, PageRequest.of(page, size)
        );
    }

    public Job getJobById(Long id) {
  
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Job not found with id: " + id
                ));
    }
}