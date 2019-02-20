package com.uchain.drugtracesystem.service.impl;

import com.uchain.drugtracesystem.form.drug.DrugForm;
import com.uchain.drugtracesystem.model.domain.Drug;
import com.uchain.drugtracesystem.result.Result;
import com.uchain.drugtracesystem.service.DrugService;
import org.springframework.stereotype.Service;

/**
 * @Author: clf
 * @Date: 19-2-10
 * @Description:
 */
@Service
public class DrugServiceImpl implements DrugService {
    @Override
    public Result addDrug(DrugForm drugForm) {
        return null;
    }

    @Override
    public void deleteDrug(Long drugId) {

    }

    @Override
    public Result updateDrug(Drug drug) {
        return null;
    }
}
