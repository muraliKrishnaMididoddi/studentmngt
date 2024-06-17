package com.project.studentManagement.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.studentManagement.entity.Student;
import com.project.studentManagement.entity.User;
import com.project.studentManagement.repository.CourseRepository;
import com.project.studentManagement.repository.StudentRepository;
import com.project.studentManagement.repository.UserRepository;
import com.project.studentManagement.service.CourseService;
import com.project.studentManagement.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseRepository repo;

	@Autowired
	private UserRepository userRepository;

	// handler method to handle list students and return mode and view
	@GetMapping("/students")
	public String listStudents(Model model) {
		System.out.println(studentService.getAllStudents());
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}

	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		model.addAttribute("courses", courseService.getAllCourses());
		return "create_student";

	}

	@PostMapping("/students")
	public String saveStudent(@RequestParam("file") MultipartFile file, @ModelAttribute("student") Student student,
			Model model) {
		try {
			student.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(student);
		System.out.println(studentService.getStudentByPhoneNumber(student.getPhoneNumber()));
		if (student.getPhoneNumber().isEmpty() || student.getStudentName().isEmpty() || student.getPhoto().isEmpty()) {
			model.addAttribute("errorMsg", "Mandatory fileds required");
			return "create_student";
		} else if (studentService.getStudentByPhoneNumber(student.getPhoneNumber()) == null) {
			model.addAttribute("errorMsg", "");
			studentService.saveStudent(student);
			User user = new User();
			user.setUserName(student.getPhoneNumber());
			user.setUserPassword("student");
			user.setRole("STUDENT");
			userRepository.save(user);
			return "redirect:/students";
		} else {
			model.addAttribute("errorMsg", "Mobile Number exists");
			return "create_student";
		}
	}

	@GetMapping("/editStudent/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		model.addAttribute("courses", courseService.getAllCourses());
		return "edit_student";
	}

	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id, @RequestParam("file") MultipartFile file,
			@ModelAttribute("student") Student student, Model model) {
		Student extStudent = studentService.getStudentById(student.getId());
		if (student.getPhoneNumber().isEmpty() || student.getStudentName().isEmpty()) {
			model.addAttribute("errorMsg", "Mandatory fileds required");
			return "edit_student";
		} else if (!studentService.getStudentById(student.getId()).getPhoneNumber()
				.equalsIgnoreCase(student.getPhoneNumber())
				&& studentService.getStudentByPhoneNumber(student.getPhoneNumber()) != null) {
			model.addAttribute("errorMsg", "Mobile Number exists");
			return "edit_student";
		} else if (!file.isEmpty()) {
			try {
				student.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
				Student existingStudent = studentService.getStudentById(id);
				BeanUtils.copyProperties(student, existingStudent);
				studentService.updateStudent(existingStudent);
				User user = userRepository.findByUserName(extStudent.getPhoneNumber());
				user.setUserName(student.getPhoneNumber());
				userRepository.save(user);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			extStudent.setAddress(student.getAddress());
			extStudent.setAge(student.getAge());
			extStudent.setCourseIds(student.getCourseIds());
			extStudent.setPhoneNumber(student.getPhoneNumber());
			extStudent.setStudentName(student.getStudentName());
			studentService.updateStudent(extStudent);
			User user = userRepository.findByUserName(extStudent.getPhoneNumber());
			user.setUserName(student.getPhoneNumber());
		}
		return "redirect:/students";
	}

	// handler method to handle delete student request

	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}

	@PostMapping("/student-login")
	public String studentPage() {
		return "student-login";
	}

	@GetMapping("/changePassword/{phNumber}")
	public String showChangePasswordForm(@PathVariable String phNumber, Model model) {
		model.addAttribute("phNumber", phNumber);
		return "changePassword";
	}

	@PostMapping("/updatePassword")
	public String updatePassword(@RequestParam("phNumber") String phNumber, @RequestParam("password") String password,
			@RequestParam("confirmPassword") String confirmPassword, Model model, RedirectAttributes redirAttr) {
		if (!password.equalsIgnoreCase(confirmPassword)) {
			redirAttr.addFlashAttribute("errorMsg", "Passwords are not matching, Try again");
			return "redirect:/changePassword/"+phNumber;
		} else {
			User user = userRepository.findByUserName(phNumber);
			user.setUserPassword(confirmPassword);
			userRepository.save(user);
			Student student = studentService.getStudentByPhoneNumber(user.getUserName());
			List<String> list = student.getCourseIds();
			model.addAttribute("courses", repo.getCourseByUser(list));
			model.addAttribute("student", student);
			return "student-login";
		}
	}

}
