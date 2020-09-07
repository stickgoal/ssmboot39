package com.woniuxy.cq.ssmboot39.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniuxy.cq.ssmboot39.common.PageResult;
import com.woniuxy.cq.ssmboot39.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lucas
 * @since 2020-08-26
 */
public interface ITeacherService extends IService<Teacher> {

    PageResult pageQuery(int pageIdx, int pageSize, String phrase, Date entryDateBefore, Date entryDateAfter);

    IPage<Teacher> pageQuery2(int pageIdx, int pageSize, String phrase, Date entryDateBefore, Date entryDateAfter);
}
