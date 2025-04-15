package com.lz.manage.service.impl;

import com.lz.common.utils.StringUtils;
import com.lz.common.utils.spring.SpringUtils;
import com.lz.manage.mapper.StaticsMapper;
import com.lz.manage.model.statics.StaticsDto;
import com.lz.manage.model.statics.ro.StaticRo;
import com.lz.manage.model.statics.vo.LineStaticVo;
import com.lz.manage.model.statics.vo.PieStaticVo;
import com.lz.manage.service.IStaticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: recruit
 * Package: com.lz.manage.service.impl
 * Author: YY
 * CreateTime: 2025-04-15  21:30
 * Description: StaticsServiceImpl
 * Version: 1.0
 */
@Service
public class StaticsServiceImpl implements IStaticsService {
    @Resource
    private StaticsMapper staticsMapper;

    @Override
    public PieStaticVo getStaticsPie(StaticsDto staticsDto) {
        List<StaticRo> staticRoList = staticsMapper.getStaticsPie(staticsDto);
        PieStaticVo pieStaticVo = new PieStaticVo();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<PieStaticVo.Date> dates = new ArrayList<>();
        //如果为空直接返回
        if (StringUtils.isEmpty(staticRoList)) {
            return pieStaticVo;
        }
        for (StaticRo staticRo : staticRoList) {
            PieStaticVo.Date date = new PieStaticVo.Date();
            date.setName(staticRo.getName());
            date.setValue(staticRo.getTotal());
            dates.add(date);
            names.add(staticRo.getName());
        }
        pieStaticVo.setNames(names);
        pieStaticVo.setDates(dates);
        return pieStaticVo;
    }

    @Override
    public LineStaticVo getStaticsLine(StaticsDto staticsDto) {
        List<StaticRo> staticRoList = staticsMapper.getStaticsLine(staticsDto);
        LineStaticVo lineStaticVo = new LineStaticVo();
        //如果没有返回
        if (StringUtils.isEmpty(staticRoList)) {
            return lineStaticVo;
        }
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Long> totals = new ArrayList<>();

        for (StaticRo staticRo : staticRoList) {
            names.add(staticRo.getName());
            totals.add(staticRo.getTotal());
        }
        lineStaticVo.setTotals(totals);
        lineStaticVo.setNames(names);
        return lineStaticVo;
    }
}
