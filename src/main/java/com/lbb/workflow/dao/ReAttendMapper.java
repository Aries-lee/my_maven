package com.lbb.workflow.dao;

import com.lbb.workflow.entity.ReAttend;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReAttendMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ReAttend record);

    int insertSelective(ReAttend record);

    ReAttend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReAttend record);

    int updateByPrimaryKey(ReAttend record);

    List<ReAttend> selectReAttendRecord(String username);
}