package com.project.studentManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.studentManagement.entity.Course;

public interface CourseRepository extends JpaRepository<Course,Integer>{
	
	@Query("SELECT p FROM Course p WHERE p.cid IN (:cids)")
	public List<Course> getCourseByUser(List<String> cids);
	
	public Course findByCid(String cid);
}
