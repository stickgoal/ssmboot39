package com.woniuxy.cq.ssmboot39.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Lucas
 * @since 2020-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("woniu_teacher")
public class Teacher implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "teacher_id", type = IdType.AUTO)
    private Integer teacherId;

    private String direction;

    private Date entryDate;

    private String level;

    private String name;

    private String nowClass;

    private String phrase;


}
