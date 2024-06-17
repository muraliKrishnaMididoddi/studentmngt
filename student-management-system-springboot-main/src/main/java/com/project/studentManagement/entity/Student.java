package com.project.studentManagement.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name = "students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "student_name")
	private String studentName;
	
	@Column(name = "age")
	private String age;
	
	@Column(name = "address")
	private String address;

	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Lob
	@Column(name = "photo")
	private String photo;
	
	@Column(name = "course_ids")
	@ElementCollection(targetClass=String.class)
	private List<String> courseIds;
	
	public Student() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<String> getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(List<String> courseIds) {
		this.courseIds = courseIds;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", studentName=" + studentName + ", age=" + age + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", photo=" + photo + ", courseIds=" + courseIds
				+ "]";
	}
	
}
