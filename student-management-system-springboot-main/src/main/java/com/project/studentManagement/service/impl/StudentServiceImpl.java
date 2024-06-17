package com.project.studentManagement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.studentManagement.entity.Student;
import com.project.studentManagement.repository.StudentRepository;
import com.project.studentManagement.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	private StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentById(Long id) {
		studentRepository.deleteById(id);	
	}

	@Override
	public Student getStudentByPhoneNumber(String phoneNumber) {
		return studentRepository.getStudentByPhoneNumber(phoneNumber);
	}

}
