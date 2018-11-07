package com.uchain.drugtracesystem.util;

import com.alibaba.fastjson.JSONObject;
import com.uchain.drugtracesystem.enums.CodeMsg;
import com.uchain.drugtracesystem.fabric.ChaincodeManager;
import com.uchain.drugtracesystem.model.Drug;
import com.uchain.drugtracesystem.model.User;
import com.uchain.drugtracesystem.result.Result;
import com.uchain.drugtracesystem.test.FabricManagerTest;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class FabricMethod {
    private static ChaincodeManager registerManager;
    private static ChaincodeManager drugTraceManager;

    static {
        try {
            registerManager = FabricManagerTest.obtain().getManager(1);
            drugTraceManager = FabricManagerTest.obtain().getManager(2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static Result register(User user){
        String[] args = {user.getId(),user.getPassword(),user.getRole(),
                String.valueOf(user.getBalance()), String.valueOf(user.getCrePoint())};
        JSONObject jsonObject;
        try {
            jsonObject = registerManager.invoke("regist", args);
        } catch (Exception e) {
            return Result.error(CodeMsg.FABRIC_RESPONSE_ERROR);
        }
        Result result = JSONObject.parseObject(jsonObject.get("data").toString(),Result.class);
        return EnumUtil.getCodeMsgResult(result);
    }

    public static Result login(String id,String password){
        String args[] = {id,password};
        JSONObject jsonObject;
        try {
            jsonObject = registerManager.invoke("login",args);
        } catch (Exception e) {
            return Result.error(CodeMsg.FABRIC_RESPONSE_ERROR);
        }
        Result result = JSONObject.parseObject(jsonObject.get("data").toString(),Result.class);
        return EnumUtil.getCodeMsgResult(result);
    }

    public static Result queryUser(String userId){
        String args[] = {userId};
        JSONObject jsonObject;
        try {
             jsonObject = registerManager.query("query",args);
        } catch (Exception e) {
            return Result.error(CodeMsg.FABRIC_RESPONSE_ERROR);
        }
        Result result = JSONObject.parseObject(jsonObject.get("data").toString(),Result.class);
        if (result.getCode()==0){
            log.info("jsonObject:{}",jsonObject);
            User user = JSONObject.parseObject(JSONObject.parseObject(jsonObject.get("data").toString()).get("user").toString(), User.class);
            result.setData(user);
        }
        return EnumUtil.getCodeMsgResult(result);
    }

    public static Result transMoney(String buyerId,String sellerId,BigDecimal price){
        String args[] = {buyerId,sellerId, String.valueOf(price)};
        JSONObject jsonObject;
        try {
            jsonObject = registerManager.invoke("transMoney",args);
        } catch (Exception e) {
            return Result.error(CodeMsg.FABRIC_RESPONSE_ERROR);
        }
        if ((Integer)jsonObject.get("code")==200){
            return Result.success();
        }
        return Result.error(CodeMsg.TRANS_MONEY_ERROR);
    }
    //增加积分
    public static Result addPoints(String userId,Integer points){
        String args[] = {userId, String.valueOf(points)};
        JSONObject jsonObject;
        try {
            jsonObject = registerManager.invoke("getPoints",args);
        } catch (Exception e) {
            return Result.error(CodeMsg.FABRIC_RESPONSE_ERROR);
        }
        Result result = JSONObject.parseObject(jsonObject.get("data").toString(),Result.class);
        return EnumUtil.getCodeMsgResult(result);
    }

    public static Result delete(String userId){
        String args[] = {userId};
        JSONObject jsonObject;
        try {
            jsonObject = registerManager.invoke("delete",args);
        } catch (Exception e) {
            return Result.error(CodeMsg.FABRIC_RESPONSE_ERROR);
        }
        Result result = JSONObject.parseObject(jsonObject.get("data").toString(),Result.class);
        return EnumUtil.getCodeMsgResult(result);
    }


    //查询用户的历史操作记录
    public static Result userHistory(String userId){
        String args[] = {userId};
        JSONObject jsonObject;
        try {
            jsonObject = registerManager.query("getHistoryForKey",args);
        } catch (Exception e) {
            return Result.error(CodeMsg.FABRIC_RESPONSE_ERROR);
        }
        return Result.success(JSONObject.parseObject(jsonObject.toString()));
    }

    public static Result drugInit(Drug drug){
        String args[] = {drug.getId(),drug.getOwnerId(),drug.getName(), String.valueOf(drug.getPrice()),drug.getFileHash(),drug.getDescription()};
        JSONObject jsonObject;
        try {
            jsonObject = drugTraceManager.invoke("drugInit",args);
        } catch (Exception e) {
            return Result.error(CodeMsg.FABRIC_RESPONSE_ERROR);
        }
        Result result = JSONObject.parseObject(jsonObject.get("data").toString(),Result.class);
        return EnumUtil.getCodeMsgResult(result);
    }
    public static Result trans(String drugId,String agencyId,String place){
        String args[] = {drugId,agencyId,place};
        JSONObject jsonObject;
        try {
            jsonObject = drugTraceManager.invoke("trans",args);
        } catch (Exception e) {
            return Result.error(CodeMsg.FABRIC_RESPONSE_ERROR);
        }
        Result result = JSONObject.parseObject(jsonObject.get("data").toString(),Result.class);
        return EnumUtil.getCodeMsgResult(result);
    }

    public static Result buy(String drugId,String buyerId,String endPlace){
        String args[] = {drugId,buyerId,endPlace};
        JSONObject jsonObject;
        try {
            jsonObject = drugTraceManager.invoke("buy",args);
        } catch (Exception e) {
            return Result.error(CodeMsg.FABRIC_RESPONSE_ERROR);
        }
        Result result = JSONObject.parseObject(jsonObject.get("data").toString(),Result.class);
        return EnumUtil.getCodeMsgResult(result);
    }

    public static Result queryDrug(String drugId){
        String args[] = {drugId};
        JSONObject jsonObject;
        try {
            jsonObject = drugTraceManager.query("query",args);
        } catch (Exception e) {
            return Result.error(CodeMsg.FABRIC_RESPONSE_ERROR);
        }
        Result result = JSONObject.parseObject(jsonObject.get("data").toString(),Result.class);
        if (result.getCode()==0){
            log.info("jsonObject:{}",jsonObject);
            Drug drug = JSONObject.parseObject(JSONObject.parseObject(jsonObject.get("data").toString()).get("drug").toString(), Drug.class);
            result.setData(drug);
        }
        return EnumUtil.getCodeMsgResult(result);
    }

    public static Result drugHistory(String drugId){
        String args[] = {drugId};
        JSONObject jsonObject;
        try {
            jsonObject = drugTraceManager.query("queryHistoryForKey",args);
        } catch (Exception e) {
            return Result.error(CodeMsg.FABRIC_RESPONSE_ERROR);
        }
        return Result.success(JSONObject.parseObject(jsonObject.toString()));
    }


    public static Result rangeDrugQuery(String startId,String endId){
        String args[] = {startId,endId};
        JSONObject jsonObject;
        try {
            jsonObject = drugTraceManager.query("testRangeQuery",args);
        } catch (Exception e) {
            return Result.error(CodeMsg.FABRIC_RESPONSE_ERROR);
        }
        return Result.success(JSONObject.parseObject(jsonObject.toString()));
    }




}
