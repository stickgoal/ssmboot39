package com.woniuxy.cq.ssmboot39.controller.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
public class StudentForm {

    @NotBlank
    @Length(min = 2,max = 128)
    private String name;

    @Past
    @NotNull
    private Date birthday;

    @NotBlank
    @Length(min = 4,max = 128)
    private String university;

    @NotBlank
    @Length(min = 2,max = 128)
    private String major;

    @NotNull
    private Date universityGraduateTime;

}
