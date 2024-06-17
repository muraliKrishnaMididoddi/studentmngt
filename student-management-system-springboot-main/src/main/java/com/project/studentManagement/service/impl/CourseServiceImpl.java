package com.project.studentManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.studentManagement.entity.Course;
import com.project.studentManagement.repository.CourseRepository;
import com.project.studentManagement.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public Course getCourseById(Integer id) {
		return courseRepository.findById(id).get();
	}

	@Override
	public Course updateCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public void deleteCourseById(Integer id) {
		courseRepository.deleteById(id);	
		
	}

	@Override
	public Course findByCid(String cid) {
		return courseRepository.findByCid(cid);
	}

}
