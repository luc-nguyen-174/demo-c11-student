package com.codegym.service.student;

import com.codegym.model.Province;
import com.codegym.model.Student;
import com.codegym.service.IGenericService;

public interface IStudentService extends IGenericService<Student> {
    Iterable<Student> findAllByProvince(Province province);
}
