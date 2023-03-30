package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class StudentForm {
    private Long id;
    private String name;
    private MultipartFile avt;

    public StudentForm() {
    }

    public StudentForm(Long id, String name, MultipartFile avt) {
        this.id = id;
        this.name = name;
        this.avt = avt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getAvt() {
        return avt;
    }

    public void setAvt(MultipartFile avt) {
        this.avt = avt;
    }
}
