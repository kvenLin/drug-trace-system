package com.uchain.drugtracesystem.exception;

import com.uchain.drugtracesystem.enums.CodeMsg;
import com.uchain.drugtracesystem.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: clf
 * @Date: 19-1-17
 * @Description:
 * 异常拦截器
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(HttpServletRequest request
            , HttpServletResponse response
            , Exception e){
        if(e instanceof BadCredentialsException){
            return Result.error(CodeMsg.PASSWORD_ERROR);
        }
        if (e instanceof GlobalException){
            GlobalException globalException = (GlobalException) e;
            return Result.error(globalException.getCodeMsg());
        }
        if (e instanceof BindException){
            BindException bindException = (BindException) e;
            List<ObjectError> errors = bindException.getAllErrors();
            String msg = null;
            for (ObjectError error : errors) {
                if (msg==null){
                    msg = error.getDefaultMessage();
                }else {
                    msg += ","+error.getDefaultMessage();
                }
            }
            log.error(msg);
            Result<String> result = Result.error(CodeMsg.BIND_ERROR);
            result.setMsg(String.format(result.getMsg(),msg));
            return result;
        }
        if (e instanceof HttpRequestMethodNotSupportedException){
            HttpRequestMethodNotSupportedException exception = (HttpRequestMethodNotSupportedException) e;
            log.error("请求方式错误,不支持:{}",exception.getMethod());
            Result<String> result = Result.error(CodeMsg.REQUEST_METHOD_ERROR);
            result.setMsg(String.format(result.getMsg(),exception.getMethod()));
            return result;
        }else {
            log.error(e.getMessage());
            e.printStackTrace();
            String requestURI = request.getRequestURI();
            log.info("请求异常的接口:{}",requestURI);
            Result<String> error = Result.error(CodeMsg.SERVER_ERROR);
            error.setMsg(String.format(error.getMsg(),e.getMessage()));
            return error;
        }
    }
}
