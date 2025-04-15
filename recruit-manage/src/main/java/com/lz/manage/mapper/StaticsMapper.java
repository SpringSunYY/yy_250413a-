package com.lz.manage.mapper;

import com.lz.manage.model.statics.StaticsDto;
import com.lz.manage.model.statics.ro.StaticRo;

import java.util.List;

public interface StaticsMapper {
    List<StaticRo> getStaticsPie(StaticsDto staticsDto);

    List<StaticRo> getStaticsLine(StaticsDto staticsDto);
}
