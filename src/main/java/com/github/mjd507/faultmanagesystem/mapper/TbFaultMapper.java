package com.github.mjd507.faultmanagesystem.mapper;

import com.github.mjd507.faultmanagesystem.entity.TbFault;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TbFaultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbFault record);

    int insertSelective(TbFault record);

    TbFault selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbFault record);

    List<TbFault> selectByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    List<TbFault> selectByWhen(@Param("start") Date start, @Param("end") Date end);

    Integer cntAll();

}