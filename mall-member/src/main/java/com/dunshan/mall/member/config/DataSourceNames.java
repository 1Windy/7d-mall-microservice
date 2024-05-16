package com.dunshan.mall.member.config;

/**
 * @author zhenghf
 * @date 2024-05-16
 * @desc
 */

public enum DataSourceNames {
    MASTER("master", "MySQL主库"),
    SHADOW("shadow", "MySQL影子库");

    private String name;
    private String desc;

    private DataSourceNames(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return this.name;
    }
}
