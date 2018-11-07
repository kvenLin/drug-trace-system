package com.uchain.drugtracesystem.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uchain.drugtracesystem.enums.CodeMsg;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result() {
    }

    private Result(T data) {
        this.code=0;
        this.message ="success";
        this.data = data;
    }

    private Result(CodeMsg codeMsg) {
        if (codeMsg==null)
            return;
        this.code = codeMsg.getCode();
        this.message = codeMsg.getMsg();
    }

    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    public static Result success(){
        return success(null);
    }

    public static <T> Result<T> error(CodeMsg codeMsg){
        return new Result<T>(codeMsg);
    }
}
