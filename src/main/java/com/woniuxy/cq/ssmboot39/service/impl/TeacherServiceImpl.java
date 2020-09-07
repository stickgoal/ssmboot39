package com.woniuxy.cq.ssmboot39.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.cq.ssmboot39.common.PageResult;
import com.woniuxy.cq.ssmboot39.entity.Teacher;
import com.woniuxy.cq.ssmboot39.dao.TeacherMapper;
import com.woniuxy.cq.ssmboot39.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lucas
 * @since 2020-08-26
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Autowired
    private TeacherMapper teacherMapper;


    @Override
    public PageResult pageQuery(int pageIdx, int pageSize, String phrase, Date entryDateBefore, Date entryDateAfter) {

        //统计总条数 动态sql
        // select count(*) from woniu_teacher where phrase = ''
        // select count(*) from woniu_teacher where phrase = '' and ...
        // select count(*) from woniu_teacher

        long totalCount = teacherMapper.countTeacher(phrase, entryDateBefore, entryDateAfter);

        //分页查询  动态sql
        // select * from woniu_teacher where phrase = '' limit ?,?
        // select * from woniu_teacher where phrase = '' and ... limit ?,?
        // select * from woniu_teacher  limit ?,?
        //计算开始位置  当前页数-1 * 当前页大小 => 最终所在的位置
        int pageStart = (pageIdx - 1) * pageSize;
        List<Teacher> teachers = teacherMapper.pageQueryTeacher(pageStart, pageSize, phrase, entryDateBefore, entryDateAfter);


        return PageResult.success(totalCount,pageIdx,pageSize,teachers);
    }

    @Override
    public IPage<Teacher> pageQuery2(int pageIdx, int pageSize, String phrase, Date entryDateBefore, Date entryDateAfter) {
        Page<Teacher> page = new Page<>(pageIdx,pageSize);

        IPage<Teacher> teacherIPage =  teacherMapper.pageQueryTeacher2(page,phrase,entryDateBefore,entryDateAfter);


        return teacherIPage;
    }
}
