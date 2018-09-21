package com.learn.rpc.service;

import suishen.esb.meta.RpcResult;
import suishen.squirrel.rpc.meta.RpcStat1dViaItemIdBean;
import suishen.squirrel.rpc.meta.RpcStat1dViaStatDateBean;

/**
 * @Author :lwy
 * @Date : 2018/9/17 10:34
 * @Description : 按天統計RPC接口
 */
public interface IItemIdStat1dService {


    /**
     * 通过按照多个条目查询
     * @param bean
     * @return
     */
    RpcResult getStatByItem(RpcStat1dViaItemIdBean bean);

    /**
     * 通过按照多天查询
     * @param bean
     * @return
     */
    RpcResult getStatByStatDates(RpcStat1dViaStatDateBean bean);
}
