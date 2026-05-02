package com.jobportal.entity;

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
	    private User company;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getSalary() {
			return salary;
		}

		public void setSalary(String salary) {
			this.salary = salary;
		}

		public User getCompany() {
			return company;
		}

		public void setCompany(User company) {
			this.company = company;
		}
	    
	    
	}

