package com.lbb.reAttend.dao;

import com.lbb.reAttend.entity.ReAttend;
import org.springframework.stereotype.Repository;

@Repository
public interface ReAttendMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ReAttend record);

    int insertSelective(ReAttend record);

    ReAttend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReAttend record);

    int updateByPrimaryKey(ReAttend record);
}