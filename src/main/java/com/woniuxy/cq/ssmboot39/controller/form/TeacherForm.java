package com.woniuxy.cq.ssmboot39.controller.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class TeacherForm {

    private Integer teacherId;

    @NotBlank
    @Size(min = 2,max=20)
    private  String name;

    @NotBlank
    @Size(min = 2,max=20)
    private String direction;

    @Past
    @NotNull
    private Date entryDate;
}
