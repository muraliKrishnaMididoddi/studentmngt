package com.project.studentManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.studentManagement.entity.Student;
import com.project.studentManagement.entity.User;
import com.project.studentManagement.repository.CourseRepository;
import com.project.studentManagement.repository.UserRepository;
import com.project.studentManagement.service.CourseService;
import com.project.studentManagement.service.StudentService;

@Controller
public class HomeController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private UserRepository UserRepository;
	
	@GetMapping("/login")
	public String showLogin(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	@GetMapping("/home")
	public String showHomePage(Model model) {
		return "home";
	}
	@GetMapping("/home_courses")
	public String showCourses(Model model) {
		model.addAttribute("courses", courseService.getAllCourses());
		return "courses_home";
	}
	@PostMapping("/userLogin")
	public String userLogin(@ModelAttribute("user") User user, RedirectAttributes redirAttr,Model model) {
		System.out.println(user);
		if(user.getUserName().toLowerCase().equals("admin") && user.getUserPassword().toLowerCase().equals("admin"))
			return "admin-login";
		else if(UserRepository.findByUserNameAndUserPassword(user.getUserName(), user.getUserPassword()) !=null) {
			Student student = studentService.getStudentByPhoneNumber(user.getUserName());
			System.out.println(student);
			List<String> list = student.getCourseIds();
			model.addAttribute("courses",courseRepository.getCourseByUser(list));
			model.addAttribute("student",student);
			return "student-login";
		}
			else {
				redirAttr.addFlashAttribute("errorMsg", "Invalid credentails");
				return "redirect:/login";
			}
		}
	@PostMapping("/cancelChange")
	public String cancelPassowrdChange(@RequestParam String phNumber, RedirectAttributes redirAttr,Model model) {
			Student student = studentService.getStudentByPhoneNumber(phNumber);
			List<String> list = student.getCourseIds();
			model.addAttribute("courses",courseRepository.getCourseByUser(list));
			model.addAttribute("student",student);
			return "student-login";
		}
	@GetMapping("/signout")
	public String signOut() {
		return "home";
	}
}
