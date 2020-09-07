package com.woniuxy.cq.ssmboot39.service;

import com.woniuxy.cq.ssmboot39.entity.Student;

public interface StudentService {


    void add(Student student);


    Student findById(int studentId);
}
