package com.github.ryanlove.coordinator.springboot.http.service.impl;

import com.github.ryanlove.coordinator.core.CoordinatorBean;
import com.github.ryanlove.coordinator.core.CoordinatorRegister;
import com.github.ryanlove.coordinator.springboot.GroupTagProperties;
import com.github.ryanlove.coordinator.springboot.http.service.ICoordinatorConfigService;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author ryan
 */
public final class CoordinatorConfigServiceImpl implements ICoordinatorConfigService {

    private GroupTagProperties groupTagProperties;
    private CoordinatorRegister coordinatorRegister;

    @Override
    public String service(String url) {
        if ("/config.json".equals(url)) {
            return returnJSONResult(0,groupTagProperties);
        }
        if ("/resolver.json".equals(url)) {
            Map<String, Map<String, CoordinatorBean>> allCoordinatorBeanMap = coordinatorRegister.findAll();
            Map<String, Map<String, String>> allCoordinatorBeanConvertMap = new HashMap<>();
            allCoordinatorBeanMap.forEach((coordinatorBean, tagMap) -> {
                Map<String, String> resolverMap = new HashMap<>();
                tagMap.forEach((key, value) -> resolverMap.put(key, value.getClass().getSuperclass().getName()));
                allCoordinatorBeanConvertMap.put(coordinatorBean, resolverMap);
            });
            return returnJSONResult(0, allCoordinatorBeanConvertMap);
        }
        return "Do not support this request";
    }

    public static String returnJSONResult(Integer resultCode, Object content) {
        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put("returnCode", resultCode);
        dataMap.put("result", content);
        return JSON.toJSONString(dataMap);
    }

    public void setGroupTagProperties(GroupTagProperties groupTagProperties) {
        this.groupTagProperties = groupTagProperties;
    }

    public void setCoordinatorRegister(CoordinatorRegister coordinatorRegister) {
        this.coordinatorRegister = coordinatorRegister;
    }
}
