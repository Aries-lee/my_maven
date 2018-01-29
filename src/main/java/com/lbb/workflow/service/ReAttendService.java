package com.lbb.workflow.service;

import com.lbb.workflow.entity.ReAttend;

import java.util.List;

/**
 * Created by Administrator on 2018/1/20.
 */
public interface ReAttendService {

    List<ReAttend> listTasks(String userName);

    void startReAttendFlow(ReAttend reAttend);

    List<ReAttend> listReAttend(String username);

    void approve(ReAttend reAttend);
}
