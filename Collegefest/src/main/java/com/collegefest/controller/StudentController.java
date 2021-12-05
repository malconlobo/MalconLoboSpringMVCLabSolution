package com.collegefest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.collegefest.entities.Student;
import com.collegefest.service.StudentOperations;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentOperations studOps;

	@RequestMapping("/list")
	public String listAll(Model model) {
		List<Student> students = studOps.findAll();
		model.addAttribute("Students", students);
		return "list-student";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

		Student student = new Student();

		model.addAttribute("Student", student);

		return "student-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model model) {

		Student student = studOps.findById(id);

		model.addAttribute("Student", student);

		return "student-form";
	}

	@PostMapping("/save")
	public String save(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("department") String department, @RequestParam("country") String country) {
		
		Student student;
		
		if(id != 0) {
			student = studOps.findById(id);
			student.setName(name);
			student.setCountry(country);
			student.setDepartment(department);
		}
		else {
			student = new Student(name,department,country);
		}
		
		studOps.save(student);

		return "redirect:/student/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int id) {
        studOps.delete(id);
        return "redirect:/student/list";
	}
	
}