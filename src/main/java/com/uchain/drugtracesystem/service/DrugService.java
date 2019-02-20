package com.uchain.drugtracesystem.service;

import com.uchain.drugtracesystem.form.drug.DrugForm;
import com.uchain.drugtracesystem.model.domain.Drug;
import com.uchain.drugtracesystem.result.Result;

/**
 * @Author: clf
 * @Date: 19-2-10
 * @Description:
 */
public interface DrugService {
    /**
     * 添加某种药品
     * @param drugForm
     * @return
     */
    Result addDrug(DrugForm drugForm);

    /**
     * 删除某种药品
     * @param drugId
     */
    void deleteDrug(Long drugId);

    /**
     * 更新某种药品信息
     * @param drug
     * @return
     */
    Result updateDrug(Drug drug);
}
