package com.uchain.drugtracesystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum CodeMsg {
    USER_NOT_EXIST(404,"当前用户不存在"),
    PASSWORD_ERROR(403,"密码错误" ),
    UN_KNOWN_FABRIC_ERROR(5000,"区块链未知错误" ),
    FABRIC_RESPONSE_ERROR(5001,"请求state返回数据异常" ),
    UN_KNOW_ERROR(500,"服务器位置错误"),
    USER_ALREADY_EXIST(501,"用户已存在" ),
    METHOD_NAME_ERROR(502,"方法名错误" ),
    PARAM_ERROR(503,"参数不能为空" ),
    TRANS_MONEY_ERROR(504,"转账失败" ),
    DRUG_ALREADY_EXIST(505,"药品id已存在" ),
    DRUG_DO_NOT_EXIST(506,"药品信息不存在" ),
    PASSWORD_NOT_SAME(507,"请确认密码一致" );

    private Integer code;
    private String msg;

}
