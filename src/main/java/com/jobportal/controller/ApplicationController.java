package com.jobportal.controller;

import com.jobportal.entity.Application;
import com.jobportal.entity.ApplicationStatus;
import com.jobportal.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/applications")
@Tag(name = "Applications", description = "Job Application APIs")
@SecurityRequirement(name = "Bearer Authentication")
public class ApplicationController {


    @Autowired
    private ApplicationService applicationService;

  
    @Operation(summary = "Apply for a job",
               description = "JOB_SEEKER can apply for any job")
    @PostMapping("/apply/{jobId}")
    public ResponseEntity<Application> applyForJob(@PathVariable Long jobId) {
        Authentication auth = SecurityContextHolder
                                .getContext()
                                .getAuthentication();
        String seekerEmail = auth.getName();
        return ResponseEntity.ok(
            applicationService.applyForJob(jobId, seekerEmail)
        );
    }

  
    @Operation(summary = "Get my applications",
               description = "JOB_SEEKER can see all their applications")
    @GetMapping("/my")
    public ResponseEntity<List<Application>> getMyApplications() {
        Authentication auth = SecurityContextHolder
                                .getContext()
                                .getAuthentication();
        String seekerEmail = auth.getName();
        return ResponseEntity.ok(
            applicationService.getMyApplications(seekerEmail)
        );
    }

   
    @Operation(summary = "Get applications for a job",
               description = "COMPANY can see all applications for their job")
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Application>> getJobApplications(
            @PathVariable Long jobId) {
        return ResponseEntity.ok(
            applicationService.getJobApplications(jobId)
        );
    }

  
    @Operation(summary = "Update application status",
               description = "COMPANY can ACCEPT or REJECT applications")
    @PutMapping("/status/{applicationId}")
    public ResponseEntity<Application> updateStatus(
            @PathVariable Long applicationId,
            @RequestParam ApplicationStatus status) {
        return ResponseEntity.ok(
            applicationService.updateStatus(applicationId, status)
        );
    }
}
