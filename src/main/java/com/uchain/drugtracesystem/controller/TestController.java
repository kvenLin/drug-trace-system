package com.uchain.drugtracesystem.controller;

import com.uchain.drugtracesystem.enums.CodeMsg;
import com.uchain.drugtracesystem.form.*;
import com.uchain.drugtracesystem.model.Drug;
import com.uchain.drugtracesystem.model.User;
import com.uchain.drugtracesystem.result.Result;
import com.uchain.drugtracesystem.util.FabricMethod;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin
public class TestController {

    @PostMapping("/login")
    @ApiOperation("用户登录验证")
    public Object login(@RequestBody LoginForm loginForm){
        return FabricMethod.login(loginForm.getUserId(),loginForm.getPassword());
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Object register(@RequestBody RegisterForm registerForm){
        if (!registerForm.getCheckPassword().equals(registerForm.getCheckPassword())){
            return Result.error(CodeMsg.PASSWORD_NOT_SAME);
        }
        User user = new User();
        user.setId(registerForm.getUserId());
        user.setPassword(registerForm.getPassword());
        return FabricMethod.register(user);
    }

    @GetMapping("/queryUser")
    @ApiOperation("查询某一用户信息")
    public Object queryUser(String userId){
        return FabricMethod.queryUser(userId);
    }

    @PostMapping("/transMoney")
    @ApiOperation("交易转账操作")
    public Object transMoney(@RequestBody TransMoneyFrom transMoneyFrom){
        return FabricMethod.transMoney(transMoneyFrom.getBuyerId(),transMoneyFrom.getSellerId(),transMoneyFrom.getPrice());
    }

    @GetMapping("/getUserHistory")
    @ApiOperation("查询用户所有历史操作记录")
    public Object getUserHistory(String userId){
        return FabricMethod.userHistory(userId);
    }

    @PostMapping("/drugInit")
    @ApiOperation("药品的初始化")
    public Object drugInit(@RequestBody DrugInitForm drugInitForm){
        Drug drug = new Drug();
        drug.setId(drugInitForm.getDrugId());
        drug.setName(drugInitForm.getName());
        drug.setFileHash(drugInitForm.getFileHash());
        drug.setOwnerId(drugInitForm.getOwnerId());
        drug.setDescription(drugInitForm.getDescription());
        drug.setPrice(drugInitForm.getPrice());
        return FabricMethod.drugInit(drug);
    }

    @PostMapping("/addTrans")
    @ApiOperation("添加溯源信息")
    public Object addTrans(@RequestBody TransForm transForm){
        return FabricMethod.trans(transForm.getDrugId(),transForm.getAgencyId(),transForm.getPlace());
    }


    @PostMapping("/buy")
    @ApiOperation("购买药品")
    public Object buy(@RequestBody BuyForm buyForm){
        return FabricMethod.buy(buyForm.getDrugId(),buyForm.getBuyerId(),buyForm.getEndPlace());
    }

    @GetMapping("/queryDrug")
    @ApiOperation("获取某一药品信息")
    public Object queryDrug(String drugId){
        return FabricMethod.queryDrug(drugId);
    }

    @GetMapping("/getHistory")
    @ApiOperation("得到某一药品的历史操作记录")
    public Object getDrugHistory(String drugId){
        return FabricMethod.drugHistory(drugId);
    }

    @GetMapping("/rangDrug")
    @ApiOperation("按id范围查询药品信息")
    public Object rangeDrug(String startId,String endId){
        return FabricMethod.rangeDrugQuery(startId,endId);
    }

}
