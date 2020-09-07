package com.woniuxy.cq.ssmboot39.service.impl;

import com.woniuxy.cq.ssmboot39.dao.StudentMapper;
import com.woniuxy.cq.ssmboot39.entity.Student;
import com.woniuxy.cq.ssmboot39.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void add(Student student) {
        log.info("新增学生");

        studentMapper.insert(student);

    }

    @Override
    public Student findById(int studentId) {
        return studentMapper.selectByPrimaryKey(studentId);
    }
}
