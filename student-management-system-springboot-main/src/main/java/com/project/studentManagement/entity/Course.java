package com.project.studentManagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "course_id", nullable = false)
	private String cid;
	
	@Column(name = "course_name")
	private String courseName;
	
	@Column(name = "faculty_name")
	private String facultyName;
	
	@Column(name = "course_fee")
	private Double courseFee;
	
	@Column(name = "course_duration",length = 32)
	private String courseDuration;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start_date")
	private Date startDate;
	
	public Course() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public Double getCourseFee() {
		return courseFee;
	}

	public void setCourseFee(Double courseFee) {
		this.courseFee = courseFee;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", cid=" + cid + ", courseName=" + courseName + ", facultyName=" + facultyName
				+ ", courseFee=" + courseFee + ", courseDuration=" + courseDuration + ", startDate=" + startDate + "]";
	}
	
	
}
