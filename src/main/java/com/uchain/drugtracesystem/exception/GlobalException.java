package com.uchain.drugtracesystem.exception;

import com.uchain.drugtracesystem.enums.CodeMsg;
import lombok.Data;

/**
 * @Author: clf
 * @Date: 19-1-17
 * @Description:
 * 全局异常类
 */
@Data
public class GlobalException extends RuntimeException{
    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super();
        this.codeMsg = codeMsg;
    }

}
