package com.uchain.drugtracesystem.util;

import lombok.Getter;

/**
 * @Author: clf
 * @Date: 19-1-28
 * @Description:
 * 文件类型
 */
@Getter
public enum FileType {
    /**
     * 表格文件
     */
    EXCEL(1),
    /**
     * 文档文件
     */
    WORD(2),
    /**
     * 视频文件
     */
    VIDEO(3),
    /**
     * 图片文件
     */
    IMAGE(4),
    ;
    private Integer value;

    FileType(Integer value) {
        this.value = value;
    }
}
