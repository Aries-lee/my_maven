package com.lbb.attend.dao;

import com.lbb.attend.entity.Attend;
import com.lbb.common.page.QueryCondition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Attend record);

    int insertSelective(Attend record);

    Attend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKey(Attend record);

    Attend selectTodaySignRecord(Long userId);

    int countByCondition(QueryCondition condition);

    List<Attend> selectAttendPage(QueryCondition condition);

    List<Long> selectTodayAbsence();

    void batchInsert(List<Attend> attendList);

    List<Attend> selectTodayEveningAbsence();
}