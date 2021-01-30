package com.github.mjd507.faultmanagesystem.mapper;


import com.github.mjd507.faultmanagesystem.entity.TbFaultStatus;

import java.util.List;

public interface TbFaultStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbFaultStatus record);

    int insertSelective(TbFaultStatus record);

    TbFaultStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbFaultStatus record);

    int updateByPrimaryKey(TbFaultStatus record);

    List<TbFaultStatus> selectAll();

}