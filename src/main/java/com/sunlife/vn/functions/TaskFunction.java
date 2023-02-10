package com.sunlife.vn.functions;

import com.sunlife.vn.models.TaskRequest;
import com.sunlife.vn.models.TaskResponse;
import com.sunlife.vn.services.EventDataCollectService;
import com.sunlife.vn.services.impl.EventDataCollectServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.logging.Logger;

@Component
public class TaskFunction implements Function<String, TaskResponse> {

    private static final Logger LOG = Logger.getLogger(String.valueOf(TaskFunction.class));
    @Autowired
    private EventDataCollectService eventDataCollectService;

    @Override
    public TaskResponse apply(String request) {
        TaskResponse response = new TaskResponse();
        try {
            eventDataCollectService.collectAnualLeeter();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        LOG.info("[INFO] " + response.getResult());

        return response;
    }
}
