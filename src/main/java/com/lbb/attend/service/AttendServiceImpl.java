package com.lbb.attend.service;

import com.lbb.attend.dao.AttendMapper;
import com.lbb.attend.entity.Attend;
import com.lbb.common.page.PageQueryBean;
import com.lbb.common.page.QueryCondition;
import com.lbb.common.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/13.
 */
@Service("attendServiceImpl")
public class AttendServiceImpl implements AttendService{


    private static final int MORNING_HOUR = 9;
    private static final int MORNING_MINUTE = 30;

    private static final int NOON_HOUR = 12;
    private static final int NOON_MINUTE = 00;

    private static final int EVENING_HOUR = 18;
    private static final int EVENING_MINUTE = 30;
    //缺勤整天
    private static final Integer ABSENCY_DAY = 480;
    //考勤状态
    private static final Byte ATTEND_STATUS_NORMAL = 1;
    private static final Byte ATTEND_STATUS_ABNORMAL = 2;

    private Log log = LogFactory.getLog(Attend.class);

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");



    @Autowired
    private AttendMapper attendMapper;

    /**
     *@Author AriesLi [www.coder520.com]
     *@Date 2018/1/13 19:23
     *@Description 打卡
    */
    @Override
    public void signAttend(Attend attend) {
        try {
            Date today = new Date();
            attend.setAttendDate(today);
            attend.setAttendWeek((byte)DateUtils.getTodayWeek());
            //查询当天有没发生打卡
            Attend todaySignRecord = attendMapper.selectTodaySignRecord(attend.getUserId());
            Date noon = DateUtils.getDate(NOON_HOUR,NOON_MINUTE);
            Date morningAttend = DateUtils.getDate(MORNING_HOUR,MORNING_MINUTE);
            if(null == todaySignRecord) {
                //记录不存在
                if(today.compareTo(noon) <=0) {
                    //上午打卡 9:30
                    attend.setAttendMorning(today);
                    if(today.compareTo(morningAttend)>0) {
                        attend.setAttendStatus(ATTEND_STATUS_ABNORMAL);
                        attend.setAbsence(DateUtils.getMinute(morningAttend,today));
                    }
                }else {
                    //下午打卡
                    attend.setAttendEvening(today);
                }
                attendMapper.insertSelective(attend);
            } else {
                //记录存在
                if(today.compareTo(noon) <=0) {
                    //上午
                    return;
                }else {
                    //下午打卡 18:30
                    todaySignRecord.setAttendEvening(today);
                    Date eveningAttend = DateUtils.getDate(EVENING_HOUR,EVENING_MINUTE);
                    if (today.compareTo(eveningAttend)<0) {
                        //18:30 早退
                        todaySignRecord.setAttendStatus(ATTEND_STATUS_ABNORMAL);
                        todaySignRecord.setAbsence(DateUtils.getMinute(today,eveningAttend));
                    } else {
                        todaySignRecord.setAttendStatus(ATTEND_STATUS_NORMAL);
                        todaySignRecord.setAbsence(0);
                    }
                    attendMapper.updateByPrimaryKeySelective(todaySignRecord);
                }
            }

        } catch (Exception e) {
            log.error("签到异常",e);
            e.printStackTrace();
        }
    }

    /**
     *@Author AriesLi [www.coder520.com]
     *@Date 2018/1/13 19:41
     *@Description 后端分页查询服务
    */
    @Override
    public PageQueryBean listAttend(QueryCondition condition) {
        //查询count 条目数 有才去查
        int count = attendMapper.countByCondition(condition);
        PageQueryBean pageResult = new PageQueryBean();
        if (count>0) {
            pageResult.setTotalRows(count);
            pageResult.setCurrentPage(condition.getCurrentPage());
            pageResult.setPageSize(condition.getPageSize());
            List<Attend> attendList = attendMapper.selectAttendPage(condition);
            pageResult.setItems(attendList);
        }
        return pageResult;
    }

    /**
     *@Author AriesLi [www.coder520.com]
     *@Date 2018/1/15 16:01
     *@Description quartz调用task 查询缺勤 设置考勤异常
    */
    @Override
    @Transactional
    public void checkAttend() {
        List<Long> userIdList =attendMapper.selectTodayAbsence();
        if(CollectionUtils.isNotEmpty(userIdList)){
            List<Attend> attendList = new ArrayList<Attend>();
            for(Long userId:userIdList){
                Attend attend = new Attend();
                attend.setUserId(userId);
                attend.setAttendDate(new Date());
                attend.setAttendWeek((byte) DateUtils.getTodayWeek());
                attend.setAbsence(ABSENCY_DAY);
                attend.setAttendStatus(ATTEND_STATUS_ABNORMAL);
                attendList.add(attend);
            }
            //批量插入
            attendMapper.batchInsert(attendList);
        }

        //检查下午未打卡 置考勤异常
        List<Attend> absenceList = attendMapper.selectTodayEveningAbsence();
        if(CollectionUtils.isNotEmpty(absenceList)) {
            for(Attend attend:absenceList) {
                attend.setAbsence(ABSENCY_DAY);
                attend.setAttendStatus(ATTEND_STATUS_ABNORMAL);
                attendMapper.updateByPrimaryKeySelective(attend);
            }
        }
    }
}
