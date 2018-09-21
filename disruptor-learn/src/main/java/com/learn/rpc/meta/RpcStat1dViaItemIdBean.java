package com.learn.rpc.meta;

import suishen.libs.meta.Bean;

import java.util.List;
import java.util.Objects;

/**
 * @Author :lwy
 * @Date : 2018/9/5 18:58
 * @Description :
 */
public class RpcStat1dViaItemIdBean implements Bean {

    //appKey
    private String appKey;

    //项目名称
    private String projectName;

    //表名称
    private String tableName;

    //模块名称
    private String module;

    //条目列表
    private List<String> itemIds;

    //日期 格式：20180306
    private String date;


    public static Builder newBuilder() {
        return new Builder();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public List<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RpcStat1dViaItemIdBean)) return false;
        RpcStat1dViaItemIdBean that = (RpcStat1dViaItemIdBean) o;
        return Objects.equals(appKey, that.appKey) &&
                Objects.equals(projectName, that.projectName) &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(module, that.module) &&
                Objects.equals(itemIds, that.itemIds) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(appKey, projectName, tableName, module, itemIds, date);
    }

    public static final class Builder {
        private String appKey;
        private String projectName;
        private String tableName;
        private String module;
        private List<String> itemIds;
        private String date;

        private Builder() {
        }

        public Builder withAppKey(String appKey) {
            this.appKey = appKey;
            return this;
        }

        public Builder withProjectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public Builder withTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public Builder withModule(String module) {
            this.module = module;
            return this;
        }

        public Builder withItemIds(List<String> itemIds) {
            this.itemIds = itemIds;
            return this;
        }

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public RpcStat1dViaItemIdBean build() {
            RpcStat1dViaItemIdBean rpcItemIdStat1dBean = new RpcStat1dViaItemIdBean();
            rpcItemIdStat1dBean.setProjectName(projectName);
            rpcItemIdStat1dBean.setTableName(tableName);
            rpcItemIdStat1dBean.setModule(module);
            rpcItemIdStat1dBean.setItemIds(itemIds);
            rpcItemIdStat1dBean.setDate(date);
            rpcItemIdStat1dBean.setAppKey(appKey);
            return rpcItemIdStat1dBean;
        }
    }

    @Override
    public String toString() {
        return "RpcItemIdStat1dBean{" +
                "appKey='" + appKey + '\'' +
                ", projectName='" + projectName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", module='" + module + '\'' +
                ", itemIds=" + itemIds +
                ", date='" + date + '\'' +
                '}';
    }
}
