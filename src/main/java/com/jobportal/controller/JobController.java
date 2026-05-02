package com.jobportal.controller;

import com.jobportal.entity.Job;
import com.jobportal.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
@Tag(name = "Jobs", description = "Job posting and search APIs")
@SecurityRequirement(name = "Bearer Authentication")
public class JobController {

    @Autowired
    private JobService jobService;

    @Operation(summary = "Post a new job",
               description = "Only COMPANY role can post jobs")
    @PostMapping("/post")
    public ResponseEntity<Job> postJob(@RequestBody Job job) {
        Authentication auth = SecurityContextHolder
                                .getContext()
                                .getAuthentication();
        String companyEmail = auth.getName();
        return ResponseEntity.ok(jobService.postJob(job, companyEmail));
    }

    @Operation(summary = "Get all jobs with pagination",
               description = "Default: page=0, size=10")
    @GetMapping("/all")
    public ResponseEntity<Page<Job>> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(jobService.getAllJobs(page, size));
    }

    @Operation(summary = "Search jobs by location",
               description = "Filter jobs by city name with pagination")
    @GetMapping("/search")
    public ResponseEntity<Page<Job>> searchByLocation(
            @RequestParam String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(
            jobService.getJobsByLocation(location, page, size)
        );
    }

    @Operation(summary = "Get job by ID",
               description = "Returns single job details by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }
}