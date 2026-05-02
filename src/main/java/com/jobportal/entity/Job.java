package com.jobportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private String salary;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnoreProperties({"password"})
    private User company;

  
    public Long getId() { 
    return id; 
    }
    public String getTitle() { 
    	
    	return title; 
    	}
    public String getDescription() { 
    	return description; 
    	}
    public String getLocation() { 
    	return location; 
    	}
    public String getSalary() { 
    	return salary; 
    	}
    public User getCompany() {
    	return company; 
    	}

    public void setId(Long id) { 
    	this.id = id; 
    	}
    public void setTitle(String title) { 
    	this.title = title; 
    	}
    public void setDescription(String description) { 
    	this.description = description; 
    	}
    public void setLocation(String location) { 
    	this.location = location; 
    	}
    public void setSalary(String salary) { 
    	this.salary = salary;
    	}
    public void setCompany(User company) {
    	this.company = company; 
    	}
}