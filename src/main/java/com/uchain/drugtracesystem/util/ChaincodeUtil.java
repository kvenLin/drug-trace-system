package com.uchain.drugtracesystem.util;

import com.uchain.drugtracesystem.fabric.ChaincodeManager;
import com.uchain.drugtracesystem.test.FabricManagerTest;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: clf
 * @Date: 19-2-22
 * @Description:
 */
@Slf4j
public class ChaincodeUtil {
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
}
