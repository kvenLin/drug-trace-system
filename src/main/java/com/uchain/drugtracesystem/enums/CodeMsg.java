package com.uchain.drugtracesystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum CodeMsg {
    AUTHENTICATION_ERROR(401, "用户认证失败,请重新登录"),
    PASSWORD_ERROR(402, "密码错误"),
    PERMISSION_DENNY(403, "权限不足"),
    USER_NOT_EXIST(404, "当前用户不存在"),
    NOT_FOUND(404,"url错误,请求路径未找到" ),
    REQUEST_METHOD_ERROR(550, "不支持%s的请求方式" ),
    SERVER_ERROR(500, "服务器未知错误:%s" ),
    USER_ALREADY_EXIST(501, "用户已存在" ),
    METHOD_NAME_ERROR(502, "方法名错误" ),
    PARAM_ERROR(503, "参数不能为空" ),
    TRANS_MONEY_ERROR(504, "转账失败" ),
    DRUG_ALREADY_EXIST(505, "药品id已存在" ),
    DRUG_DO_NOT_EXIST(506, "药品信息不存在" ),
    PASSWORD_NOT_SAME(507, "请确认密码一致" ),
    UPDATE_ERROR(509, "数据库更新异常"),
    BIND_ERROR(511, "参数校验错误:%s"),
    ADD_ERROR(512, "数据库添加异常"),
    CHAINCODE_NOT_EXIST(513, "链码不存在"),
    ORDERER_NOT_EXIST(514, "当前排序节点不存在"),
    PEER_NOT_EXIST(515, "peer节点不存在"),
    CHAINCODE_ALREADY_EXIST(516, "链码已存在,链码名重复"),
    CONFIG_CHAINCODE_MANAGER_ERROR(517, "装配链码管理器异常"),
    UN_KNOWN_FABRIC_ERROR(5000, "区块链未知错误" ),
    FABRIC_RESPONSE_ERROR(5001,"请求state返回数据异常" );

    private Integer code;
    private String msg;

}
