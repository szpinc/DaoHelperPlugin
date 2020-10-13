package me.szp.plugin.tool;

import me.szp.plugin.entity.GlobalConfig;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

/**
 * 模板工具测试用例
 *
 * @author szp
 * @version 1.0.0
 * @since 2020/09/01 15:28
 */
public class TemplateUtilsTest {

    /**
     * 测试向模板中添加全局配置
     */
    @Test
    public void addGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig("init", "Hello World!");
        String template = "$init,${init}";
        // 添加全局变量
        template = TemplateUtils.addGlobalConfig(template, Collections.singleton(globalConfig));
        Assert.assertEquals(template, "Hello World!,Hello World!");
    }
}