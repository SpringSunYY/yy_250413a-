package com.lz.manage.model.statics.vo;

import java.util.List;

@lombok.Data
public class PieStaticVo {
    private List<String> names;

    private List<Date> dates;

    @lombok.Data
    public static class Date {
        private String name;
        private Long value;
    }
}
