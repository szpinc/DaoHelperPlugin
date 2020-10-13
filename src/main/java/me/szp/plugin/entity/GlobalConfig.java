package me.szp.plugin.entity;

import me.szp.plugin.ui.base.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局配置实体类
 *
 * @author szp
 * @version 1.0.0
 * @since 2020/07/27 13:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalConfig implements Item {
    /**
     * 名称
     */
    private String name;
    /**
     * 值
     */
    private String value;
}
