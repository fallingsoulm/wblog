package com.wblog.bi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author luyanan
 * @since 2021/8/2
 **/
@Data
@TableName("ureport_file_tbl")
public class UreportFileEntity {


    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private byte[] content;
    private Date createTime;
    private Date updateTime;
}
