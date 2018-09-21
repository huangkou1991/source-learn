package com.learn.rpc.meta;

import suishen.libs.meta.Bean;

import java.util.List;

/**
 * @Author :lwy
 * @Date : 2018/9/5 18:58
 * @Description :
 */
public class RpcStat1dViaStatDateBean implements Bean {

    //appKey
    private String appKey;

    //项目名称
    private String projectName;

    //表名称
    private String tableName;

    //模块名称
    private String module;

    //条目列表
    private Long itemId;

    //日期列表
    private List<String> statDates;

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


    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public List<String> getStatDates() {
        return statDates;
    }

    public void setStatDates(List<String> statDates) {
        this.statDates = statDates;
    }


    public static final class Builder {
        //appKey
        private String appKey;
        //项目名称
        private String projectName;
        //表名称
        private String tableName;
        //模块名称
        private String module;
        //条目列表
        private Long itemId;
        private List<String> statDates;

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

        public Builder withItemId(Long itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder withStatDates(List<String> statDates) {
            this.statDates = statDates;
            return this;
        }

        public RpcStat1dViaStatDateBean build() {
            RpcStat1dViaStatDateBean rpcStat1dViaStatDateBean = new RpcStat1dViaStatDateBean();
            rpcStat1dViaStatDateBean.setAppKey(appKey);
            rpcStat1dViaStatDateBean.setProjectName(projectName);
            rpcStat1dViaStatDateBean.setTableName(tableName);
            rpcStat1dViaStatDateBean.setModule(module);
            rpcStat1dViaStatDateBean.setItemId(itemId);
            rpcStat1dViaStatDateBean.setStatDates(statDates);
            return rpcStat1dViaStatDateBean;
        }
    }

    @Override
    public String toString() {
        return "RpcStat1dViaStatDateBean{" +
                "appKey='" + appKey + '\'' +
                ", projectName='" + projectName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", module='" + module + '\'' +
                ", itemId=" + itemId +
                ", statDates=" + statDates +
                '}';
    }
}
