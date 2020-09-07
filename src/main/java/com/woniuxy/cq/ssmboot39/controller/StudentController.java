package com.woniuxy.cq.ssmboot39.controller;

import com.woniuxy.cq.ssmboot39.common.Result;
import com.woniuxy.cq.ssmboot39.common.StudentStatusEnum;
import com.woniuxy.cq.ssmboot39.common.ValidationUtil;
import com.woniuxy.cq.ssmboot39.controller.form.StudentForm;
import com.woniuxy.cq.ssmboot39.entity.Student;
import com.woniuxy.cq.ssmboot39.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("student")
    public Result addStudent(@RequestBody @Valid StudentForm studentForm, BindingResult result) {
        log.info("增学生：{}", studentForm);
        if(result.hasErrors()){
            String validationResult = ValidationUtil.extract(result);
            log.info("校验失败{}",validationResult);
            return Result.fail("ILLEGAL_ARGUMENTS",validationResult);
        }


        Student student = new Student();
        BeanUtils.copyProperties(studentForm,student);
        student.setStatus(StudentStatusEnum.IN_SCHOOL.getCode());
        student.setRegTime(new Date());
        studentService.add(student);

        return Result.success();
    }

    @GetMapping("students")//改
    public void getAllStudent() {
        log.info("查询所有学生");
    }

    @GetMapping("student/{studentId}")//查
    public Result getStudent(@PathVariable("studentId") int studentId) {
        log.info("传入的id为{}", studentId);

        Student student = studentService.findById(studentId);

        return Result.success(student);
    }

    @DeleteMapping("student/{studentId}")//删
    public void delStudent(@PathVariable("studentId") int studentId) {
        log.info("传入的id为{}", studentId);



    }

    @PutMapping("student/{studentId}")//改
    public void downgrade(@PathVariable("studentId")int studentId,String classname) {
        log.info("学生降级为{},{}", studentId,classname);
        //校验学生存在，校验班级存在


    }

}
