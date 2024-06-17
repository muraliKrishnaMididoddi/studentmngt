package com.project.studentManagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.project.studentManagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
	
	@Query("select s from Student s where s.phoneNumber=:phoneNumber")
	public Student getStudentByPhoneNumber(String phoneNumber);
}
