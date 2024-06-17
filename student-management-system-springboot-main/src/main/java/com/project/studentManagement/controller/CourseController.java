package com.project.studentManagement.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.studentManagement.entity.Course;
import com.project.studentManagement.entity.Student;
import com.project.studentManagement.service.CourseService;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping("/courses")
	public String listCourses(Model model) {
		model.addAttribute("courses", courseService.getAllCourses());
		return "courses";
	}

	@GetMapping("/courses/new")
	public String createCourseForm(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		return "create_course";

	}

	@PostMapping("/addCourse")
	public String saveCourse(@ModelAttribute("course") Course course,Model model) {
		System.out.println(course);
		if (course.getCid().isEmpty() || course.getCourseFee() == null || course.getCourseName().isEmpty()
				|| course.getFacultyName().isEmpty()) {
			model.addAttribute("errorMsg", "Mandatory fileds required");
			return "create_course";
		} else if (courseService.findByCid(course.getCid()) == null) {
			System.out.println(course);
			courseService.saveCourse(course);
			return "redirect:/courses";
		} else {
			model.addAttribute("errorMsg","Course ID already exists");
			return "create_course";
		}
	}

	@GetMapping("/editCourse/{id}")
	public String editCourse(@PathVariable Integer id, Model model) {
		model.addAttribute("course", courseService.getCourseById(id));
		return "edit_course";
	}

	@PostMapping("/courses/{id}")
	public String updateCourse(@PathVariable Integer id, @ModelAttribute("course") Course course, Model model) {
		Course existingCourse = courseService.getCourseById(id);
		if(course.getCid().isEmpty() || course.getCourseFee() == null || course.getCourseName().isEmpty()
				|| course.getFacultyName().isEmpty()) {
			model.addAttribute("errorMsg", "Mandatory fileds required");
			return "edit_course";
		}else if(existingCourse.getCid().equalsIgnoreCase(course.getCid())){
			BeanUtils.copyProperties(course, existingCourse);
			System.out.println(existingCourse);
			courseService.updateCourse(existingCourse);
			return "redirect:/courses";
		}
		else if (!existingCourse.getCid().equalsIgnoreCase(course.getCid()) && courseService.findByCid(course.getCid()) == null) {
			System.out.println(existingCourse);
			BeanUtils.copyProperties(course, existingCourse);
			System.out.println(existingCourse);
			courseService.updateCourse(existingCourse);
			return "redirect:/courses";
		}
		else {
			model.addAttribute("errorMsg", "Course ID already exists");
			return "edit_course";
		}
	}

	@GetMapping("/courses/{id}")
	public String deleteCourse(@PathVariable Integer id) {
		courseService.deleteCourseById(id);
		return "redirect:/courses";
	}
}
