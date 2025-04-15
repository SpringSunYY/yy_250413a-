package com.lz.manage.service;

import com.lz.manage.model.statics.StaticsDto;
import com.lz.manage.model.statics.vo.LineStaticVo;
import com.lz.manage.model.statics.vo.PieStaticVo;

public interface IStaticsService {
    PieStaticVo getStaticsPie(StaticsDto staticsDto);

    LineStaticVo getStaticsLine(StaticsDto staticsDto);
}
