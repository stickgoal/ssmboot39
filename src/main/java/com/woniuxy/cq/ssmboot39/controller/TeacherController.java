package com.woniuxy.cq.ssmboot39.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniuxy.cq.ssmboot39.common.PageResult;
import com.woniuxy.cq.ssmboot39.common.Result;
import com.woniuxy.cq.ssmboot39.common.ValidationUtil;
import com.woniuxy.cq.ssmboot39.controller.form.TeacherForm;
import com.woniuxy.cq.ssmboot39.entity.Teacher;
import com.woniuxy.cq.ssmboot39.service.ITeacherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lucas
 * @since 2020-08-26
 */
@Controller
@RequestMapping("/teacher")
@Slf4j
@CrossOrigin("*")
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @GetMapping("list")
    public PageResult queryList(@RequestParam(defaultValue = "1") int pageIdx, @RequestParam(defaultValue = "10") int pageSize, String phrase
            , Date entryDateBefore, Date entryDateAfter){
        //参数处理
        if(entryDateAfter!=null) {
            // 重置成23:59:59
            Calendar instance = Calendar.getInstance();
            instance.setTime(entryDateAfter);
            instance.set(Calendar.HOUR_OF_DAY, 23);
            instance.set(Calendar.MINUTE, 59);
            instance.set(Calendar.SECOND, 59);
            entryDateAfter = instance.getTime();
        }

        //分页返回结果： 总条数、总页数，当前页内容
        PageResult pageResult =  teacherService.pageQuery(pageIdx,pageSize,phrase,entryDateBefore,entryDateAfter);


        return pageResult;
    }


    @GetMapping("list2")
//    @RequiresRoles({"dev"})//必须有dev角色才能访问
    @RequiresPermissions("repo::create")
    public String queryList2(@RequestParam(defaultValue = "1") int pageIdx, @RequestParam(defaultValue = "10") int pageSize, String phrase
            , Date entryDateBefore, Date entryDateAfter, ModelMap modelMap){
        log.info("xxx{}",pageIdx);
        //参数处理
        if(entryDateAfter!=null) {
            // 重置成23:59:59
            Calendar instance = Calendar.getInstance();
            instance.setTime(entryDateAfter);
            instance.set(Calendar.HOUR_OF_DAY, 23);
            instance.set(Calendar.MINUTE, 59);
            instance.set(Calendar.SECOND, 59);
            entryDateAfter = instance.getTime();
        }

        IPage<Teacher> teacherIpage = teacherService.pageQuery2(pageIdx,pageSize,phrase,entryDateBefore,entryDateAfter);
        modelMap.addAttribute("teacherIpage",teacherIpage);
        modelMap.addAttribute("phrase",phrase);
        modelMap.addAttribute("entryDateBefore",entryDateBefore);
        modelMap.addAttribute("entryDateAfter",entryDateAfter);
        return "teacher/list.html";
    }

    @PostMapping("upgrade")
    @ResponseBody
    public Result upgrade(int teacherId,String level){
        log.info("id:{},level:{}",teacherId,level);

        return Result.success();
    }


    @GetMapping("get")
    @ResponseBody
    public Result getTeacher(int id){

        Teacher teacher = teacherService.getById(id);

        return Result.success(teacher);

    }


    @PostMapping("add1")
    @ResponseBody
    public String add(String name, String level){
        System.out.println(name+level);
        return "success";
    }

    @PostMapping("add")
    @ResponseBody
    public Result addTeacher(@RequestBody @Valid TeacherForm teacherForm, BindingResult result){
        //参数校验
        if(result.hasErrors()) {
            String validationError = ValidationUtil.extract(result);
            return Result.fail("ARGUMENTS_ERROR",validationError);
        }

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherForm,teacher);
        teacher.setLevel("1");
        teacher.setNowClass("1");
        teacher.setPhrase("111");

        teacherService.save(teacher);

        return Result.success(teacher);

    }

    @PostMapping("modify")
    @ResponseBody
    public Result modifyTeacher( @Valid TeacherForm teacherForm, BindingResult result){
        //参数校验
        if(result.hasErrors()) {
            String validationError = ValidationUtil.extract(result);
            return Result.fail("ARGUMENTS_ERROR",validationError);
        }

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherForm,teacher);
        teacher.setLevel("1");
        teacher.setNowClass("1");
        teacher.setPhrase("111");

        teacherService.updateById(teacher);

        return Result.success(teacher);

    }

}

