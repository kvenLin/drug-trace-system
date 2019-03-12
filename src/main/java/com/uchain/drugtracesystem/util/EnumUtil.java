package com.uchain.drugtracesystem.util;

import com.uchain.drugtracesystem.enums.CodeMsg;
import com.uchain.drugtracesystem.result.Result;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnumUtil {

    public static Result getCodeMsgResult(Result result){
        switch (result.getCode()){
            case 0:
                return result;
            case 403:
                return Result.error(CodeMsg.PASSWORD_ERROR);
            case 404:
                return Result.error(CodeMsg.USER_NOT_EXIST);
            case 501:
                return Result.error(CodeMsg.USER_ALREADY_EXIST);
            case 502:
                return Result.error(CodeMsg.DRUG_ALREADY_EXIST);
            case 503:
                return Result.error(CodeMsg.DRUG_DO_NOT_EXIST);
            case 504:
                return Result.error(CodeMsg.TRANS_MONEY_ERROR);
            default:
                return Result.error(CodeMsg.UN_KNOWN_FABRIC_ERROR);
        }
    }
}
