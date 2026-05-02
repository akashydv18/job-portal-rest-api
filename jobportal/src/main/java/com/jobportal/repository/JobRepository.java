package com.jobportal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.entity.Job;


public interface JobRepository extends JpaRepository<Job, Long>{
	Page<Job> findByLocation(String location, Pageable pageable);

}
