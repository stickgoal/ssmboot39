package com.woniuxy.cq.ssmboot39.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.cq.ssmboot39.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lucas
 * @since 2020-08-26
 */
public interface TeacherMapper extends BaseMapper<Teacher> {

    long countTeacher(@Param("phrase") String phrase, @Param("entryDateBefore")Date entryDateBefore, @Param("entryDateAfter")Date entryDateAfter);


    List<Teacher> pageQueryTeacher(@Param("pageStart") int pageStart,@Param("pageSize")int pageSize,@Param("phrase") String phrase, @Param("entryDateBefore")Date entryDateBefore, @Param("entryDateAfter")Date entryDateAfter);


    IPage<Teacher> pageQueryTeacher2(Page<Teacher> page, @Param("phrase") String phrase, @Param("entryDateBefore")Date entryDateBefore, @Param("entryDateAfter")Date entryDateAfter);
}
