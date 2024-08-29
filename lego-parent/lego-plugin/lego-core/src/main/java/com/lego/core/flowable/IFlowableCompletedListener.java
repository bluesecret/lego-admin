package com.lego.core.flowable;

import java.util.Map;

public interface IFlowableCompletedListener {

    String taskCompleted(Map<String, Object> variables);

    void taskRejected(String code);

    void processCompleted(String code);

    String getTableCode();
}
