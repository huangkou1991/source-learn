package com.learn.rpc.meta;

import suishen.libs.meta.Bean;

import java.util.List;
import java.util.Objects;

/**
 * @Author :lwy
 * @Date : 2018/9/5 18:32
 * @Description : 累计查询rpcBean
 */
public class RpcItemIdStatNdBean implements Bean {

    //项目名称
    private String projectName;

    //表名称
    private String tableName;

    //模块名称
    private String module;

    //条目列表
    private List<String> itemIds;

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

    public List<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RpcItemIdStatNdBean)) return false;
        RpcItemIdStatNdBean bean = (RpcItemIdStatNdBean) o;
        return Objects.equals(projectName, bean.projectName) &&
                Objects.equals(tableName, bean.tableName) &&
                Objects.equals(module, bean.module) &&
                Objects.equals(itemIds, bean.itemIds);
    }

    @Override
    public int hashCode() {

        return Objects.hash(projectName, tableName, module, itemIds);
    }

    public static final class Builder {
        private String projectName;
        private String tableName;
        private String module;
        private List<String> itemIds;

        private Builder() {
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

        public RpcItemIdStatNdBean build() {
            RpcItemIdStatNdBean rpcItemIdStatNdBean = new RpcItemIdStatNdBean();
            rpcItemIdStatNdBean.setProjectName(projectName);
            rpcItemIdStatNdBean.setTableName(tableName);
            rpcItemIdStatNdBean.setModule(module);
            rpcItemIdStatNdBean.setItemIds(itemIds);
            return rpcItemIdStatNdBean;
        }
    }

    @Override
    public String toString() {
        return "RpcItemIdStatNdBean{" +
                "projectName='" + projectName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", module='" + module + '\'' +
                ", itemIds=" + itemIds +
                '}';
    }
}
