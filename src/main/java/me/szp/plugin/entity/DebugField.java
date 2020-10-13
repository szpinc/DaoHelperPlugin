package me.szp.plugin.entity;

import lombok.Data;

/**
 * 调试字段实体类
 *
 * @author szp
 * @version 1.0.0
 * @since 2020/09/03 11:09
 */
@Data
public class DebugField {
    /**
     * 字段名
     */
    private String name;
    /**
     * 字段类型
     */
    private Class<?> type;
    /**
     * 字段值
     */
    private String value;
}
