package com.phuc.pcoreservice.service.impl;

import com.phuc.pcoreservice.model.TaskConfigVPS;
import com.phuc.pcoreservice.repository.ITaskRepo;
import com.phuc.pcoreservice.request.ConfigRunningVPSRequest;
import com.phuc.pcoreservice.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private ITaskRepo taskRepo;

    @Override
    public ResponseEntity<?> configVPSRunning(ConfigRunningVPSRequest request) {
        TaskConfigVPS taskConfigVPS = new TaskConfigVPS();
        taskConfigVPS.setIpVPS(request.getIpAddress());
        taskConfigVPS.setTotalGmail(request.getTotalGmail());
        taskConfigVPS.setTotalProxy(request.getTotalProxy());

        Boolean bl = taskRepo.saveConfigVPS(taskConfigVPS);

        return ResponseEntity.ok().body("save data success!");
    }
}
