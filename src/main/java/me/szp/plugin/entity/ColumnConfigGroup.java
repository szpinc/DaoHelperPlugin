package me.szp.plugin.entity;

import lombok.Data;

import java.util.List;

/**
 * 列配置分组
 *
 * @author szp
 * @version 1.0.0
 * @since 2020/07/18 09:33
 */
@Data
public class ColumnConfigGroup implements AbstractGroup<ColumnConfig> {
    /**
     * 分组名称
     */
    private String name;
    /**
     * 元素对象
     */
    private List<ColumnConfig> elementList;
}
