package com.sbs.system.entity;


import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;

/**
 *  Entity
 *
 * @date 2020-02-13 14:34:53
 */
@Data
@TableName("t_dict_item")
@Excel("字典表")
public class DictItem implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 数据编码
     */
    @TableField("CODE")
    @ExcelField(value = "字典编码")
    @NotBlank(message = "{required}")
    private String code;

    /**
     * 名称
     */
    @TableField("NAME")
    @ExcelField(value = "字典名称")
    private String name;
    
    /**
     * 描述
     */
    @TableField("DESCRIPT")
    @ExcelField(value = "字典描述")
    private String descript;
    
    /**
     * 外键
     */
    @TableField(value = "DICT_ID")
    private Long dictId;
}
