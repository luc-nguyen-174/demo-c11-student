package com.codegym.controller;

import com.codegym.model.Province;
import com.codegym.model.Student;
import com.codegym.model.StudentForm;
import com.codegym.service.province.IProvinceService;
import com.codegym.service.student.IStudentService;
import com.codegym.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/student")
@PropertySource("classpath:upload_file.properties")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private IProvinceService provinceService;

    @Value("${file-upload}")
    private String fileUpload;

    @ModelAttribute("provinces")
    public Iterable<Province> provinces() {
        return provinceService.findAll();
    }

    @GetMapping("")
    public ModelAndView listCustomers() {
        Iterable<Student> students = studentService.findAll();
        ModelAndView modelAndView = new ModelAndView("/student/index");
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/create-student")
    public ModelAndView createStudentForm() {
        ModelAndView modelAndView = new ModelAndView("/student/create");
        modelAndView.addObject("student", new StudentForm());
        return modelAndView;
    }

    @PostMapping("/save-student")
    public ModelAndView saveStudent(@ModelAttribute StudentForm studentForm) {
        MultipartFile multipartFile = studentForm.getAvt();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(studentForm.getAvt().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Student student = new Student(studentForm.getId(), studentForm.getName(), fileName);
        studentService.save(student);
        ModelAndView modelAndView = new ModelAndView("/student/create");
        modelAndView.addObject("studentForm", studentForm);
        modelAndView.addObject("message","Add new student successfully !");
        return modelAndView;
    }
}
