package com.lbb.common.task;

import com.lbb.attend.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2018/1/15.
 * quartz 对应Bean task
 */
public class AttendCheckTask {

    @Autowired
    private AttendService attendService;

    public void checkAttend() {
        attendService.checkAttend();
    }
}
