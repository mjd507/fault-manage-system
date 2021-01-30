package com.github.mjd507.faultmanagesystem.controller;

import com.github.mjd507.faultmanagesystem.entity.TbFault;
import com.github.mjd507.faultmanagesystem.entity.TbFaultStatus;
import com.github.mjd507.faultmanagesystem.entity.TbFaultWhat;
import com.github.mjd507.faultmanagesystem.entity.TbFaultWhere;
import com.github.mjd507.faultmanagesystem.mapper.TbFaultMapper;
import com.github.mjd507.faultmanagesystem.mapper.TbFaultStatusMapper;
import com.github.mjd507.faultmanagesystem.mapper.TbFaultWhatMapper;
import com.github.mjd507.faultmanagesystem.mapper.TbFaultWhereMapper;
import com.github.mjd507.util.http.ApiResponse;
import com.github.mjd507.util.util.MapUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Create by majiandong on 1/26/21 2:10 PM
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/fault")
public class FaultController {

    @Autowired
    private TbFaultWhatMapper whatMapper;

    @Autowired
    private TbFaultWhereMapper whereMapper;

    @Autowired
    private TbFaultStatusMapper statusMapper;

    @Autowired
    private TbFaultMapper faultMapper;

    @GetMapping("/what")
    public ApiResponse listWhat() {
        List<TbFaultWhat> what = whatMapper.selectAll();
        return ApiResponse.ok(what);
    }

    @GetMapping("/where")
    public ApiResponse listWhere() {
        List<TbFaultWhere> where = whereMapper.selectAll();
        return ApiResponse.ok(where);
    }

    @GetMapping("/status")
    public ApiResponse listStatus() {
        List<TbFaultStatus> status = statusMapper.selectAll();
        return ApiResponse.ok(status);
    }

    @GetMapping("/list")
    public ApiResponse listFault(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        if (pageNum == null || pageNum <= 0) pageNum = 1;
        if (pageSize == null) pageSize = 10;
        pageNum = pageSize * (pageNum - 1);
        List<TbFault> where = faultMapper.selectByPage(pageNum, pageSize);
        Integer cnt = faultMapper.cntAll();
        return ApiResponse.ok(MapUtil.newMap().put("total", cnt).put("list", where));
    }

    @PostMapping("/insert")
    public ApiResponse insert(@RequestBody TbFault tbFault) {
        faultMapper.insertSelective(tbFault);
        return ApiResponse.ok();
    }

    @GetMapping("/statistic")
    public ApiResponse statistic() {
        List<TbFault> tbFaultsAll = faultMapper.selectByPage(null, null);

        // 故障状态分布
        List<TbFaultStatus> status = statusMapper.selectAll();
        HashMap<Integer, String> statusMap = new HashMap<>();
        for (TbFaultStatus tbFaultStatus : status) {
            statusMap.put(tbFaultStatus.getCode(), tbFaultStatus.getDescribe());
        }
        // 故障来源分布
        List<TbFaultWhere> wheres = whereMapper.selectAll();
        HashMap<Integer, String> whereMap = new HashMap<>();
        for (TbFaultWhere tbFaultWhere : wheres) {
            whereMap.put(tbFaultWhere.getSource(), tbFaultWhere.getDescribe());
        }
        // 故障类别分布
        List<TbFaultWhat> whats = whatMapper.selectAll();
        HashMap<Integer, String> whatMap = new HashMap<>();
        for (TbFaultWhat tbFaultWhat : whats) {
            whatMap.put(tbFaultWhat.getType(), tbFaultWhat.getDescribe());
        }

        MapUtil allFaultStatistic = getStatistic(tbFaultsAll, statusMap, whereMap, whatMap);
        // 统计本周故障统计
        List<TbFault> tbFaultsWeek = faultMapper.selectByWhen(DateUtils.addDays(new Date(), -7), new Date());
        MapUtil weekFaultStatistic = getStatistic(tbFaultsWeek, statusMap, whereMap, whatMap);

        return ApiResponse.ok(MapUtil.newMap()
                .put("allFault", allFaultStatistic)
                .put("weekFault", weekFaultStatistic)
        );
    }

    private MapUtil getStatistic(List<TbFault> tbFaults, HashMap<Integer, String> statusMap, HashMap<Integer, String> whereMap, HashMap<Integer, String> whatMap) {
        MapUtil mapUtil = MapUtil.newMap();
        HashMap<String, Integer> statusCntMap = new HashMap<>();
        HashMap<String, Integer> whereCntMap = new HashMap<>();
        HashMap<String, Integer> whatCntMap = new HashMap<>();
        for (TbFault tbFault : tbFaults) {
            // 状态分布统计
            String describeStatus = statusMap.get(tbFault.getStatus());
            Integer statusCnt = statusCntMap.getOrDefault(describeStatus, 0);
            statusCntMap.put(describeStatus, ++statusCnt);

            // 来源分布统计
            String describeWhere = whereMap.get(tbFault.getWhere());
            Integer whereCnt = whereCntMap.getOrDefault(describeWhere, 0);
            whereCntMap.put(describeWhere, ++whereCnt);

            // 类别分布统计
            String describeWhat = whatMap.get(tbFault.getWhat());
            Integer whatCnt = whatCntMap.getOrDefault(describeWhat, 0);
            whatCntMap.put(describeWhat, ++whatCnt);
        }
        statusCntMap.put("总故障数", tbFaults.size());

        return mapUtil
                .put("StatusCnt", statusCntMap)
                .put("WhereCnt", whereCntMap)
                .put("WhatCnt", whatCntMap);
    }

}
