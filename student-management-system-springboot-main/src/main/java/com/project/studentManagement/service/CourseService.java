package com.project.studentManagement.service;

import java.util.List;

import com.project.studentManagement.entity.Course;


public interface CourseService {

	List<Course> getAllCourses();
	
	Course saveCourse(Course course);
	
	Course getCourseById(Integer id);
	
	Course updateCourse(Course Course);
	
	void deleteCourseById(Integer id);
	
	Course findByCid(String cid);
}
