package me.szp.plugin.ui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.intellij.openapi.options.Configurable;
import me.szp.plugin.entity.ColumnConfig;
import me.szp.plugin.entity.ColumnConfigGroup;
import me.szp.plugin.entity.ColumnConfigType;
import me.szp.plugin.tool.CloneUtils;
import me.szp.plugin.config.Settings;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

/**
 * 表设置面板
 *
 * @author szp
 * @version 1.0.0
 * @since 2020/07/17 13:10
 */
public class TableSettingPanel extends AbstractTableGroupPanel<ColumnConfigGroup, ColumnConfig> implements Configurable {
    private Settings settings = Settings.getInstance();

    TableSettingPanel() {
        super(CloneUtils.cloneByJson(Settings.getInstance().getColumnConfigGroupMap(), new TypeReference<Map<String, ColumnConfigGroup>>() {}), Settings.getInstance().getCurrColumnConfigGroupName());
    }

    @Override
    protected Object[] toRow(ColumnConfig item) {
        return new Object[]{item.getTitle(), item.getType().name(), item.getSelectValue()};
    }

    @Override
    protected ColumnConfig toItem(Object[] rowData) {
        return new ColumnConfig((String) rowData[0], ColumnConfigType.valueOf((String) rowData[1]), (String) rowData[2]);
    }

    @Override
    protected String getItemName(ColumnConfig item) {
        return item.getTitle();
    }

    @Override
    protected ColumnConfig createItem(String value) {
        return new ColumnConfig(value, ColumnConfigType.TEXT);
    }

    @Override
    protected ColumnConfig[] initColumn() {
        ColumnConfig[] columnConfigs = new ColumnConfig[3];
        columnConfigs[0] = new ColumnConfig("title", ColumnConfigType.TEXT);
        columnConfigs[1] = new ColumnConfig("type", ColumnConfigType.SELECT, "TEXT,SELECT,BOOLEAN");
        columnConfigs[2] = new ColumnConfig("selectValue", ColumnConfigType.TEXT);
        return columnConfigs;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Table Editor Config";
    }

    /**
     * Returns the topic in the help file which is shown when help for the configurable is requested.
     *
     * @return the help topic, or {@code null} if no help is available
     */
    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return getMainPanel();
    }

    @Override
    public boolean isModified() {
        refresh();
        return !settings.getColumnConfigGroupMap().equals(group) || !settings.getCurrColumnConfigGroupName().equals(currGroupName);
    }

    @Override
    public void apply() {
        settings.setColumnConfigGroupMap(group);
        settings.setCurrColumnConfigGroupName(currGroupName);
    }

    @Override
    public void reset() {
        this.group = CloneUtils.cloneByJson(settings.getColumnConfigGroupMap(), new TypeReference<Map<String, ColumnConfigGroup>>() {});
        this.currGroupName = settings.getCurrColumnConfigGroupName();
        init();
    }
}
