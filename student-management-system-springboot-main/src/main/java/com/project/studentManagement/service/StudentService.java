package com.project.studentManagement.service;

import java.util.List;

import com.project.studentManagement.entity.Student;

public interface StudentService {
	List<Student> getAllStudents();
	
	Student saveStudent(Student student);
	
	Student getStudentById(Long id);
	
	Student updateStudent(Student student);
	
	void deleteStudentById(Long id);
	
	Student getStudentByPhoneNumber(String phoneNumber);
}
