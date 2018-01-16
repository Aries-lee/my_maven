package com.lbb.attend.service;

import com.lbb.attend.entity.Attend;
import com.lbb.common.page.PageQueryBean;
import com.lbb.common.page.QueryCondition;

/**
 * Created by Administrator on 2018/1/13.
 */
public interface AttendService {

    void signAttend(Attend attend);

    PageQueryBean listAttend(QueryCondition condition);

    void checkAttend();
}
