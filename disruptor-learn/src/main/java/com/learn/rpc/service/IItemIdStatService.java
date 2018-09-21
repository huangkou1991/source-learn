package com.learn.rpc.service;

import suishen.esb.meta.RpcResult;
import suishen.squirrel.rpc.meta.RpcItemIdStatNdBean;

/**
 * @Author :lwy
 * @Date : 2018/9/5 18:40
 * @Description : 累计统计RPC接口
 */
public interface IItemIdStatService {

    /**
     * 累计查询服务
     *
     * @param bean
     *
     */
    RpcResult getItemIdStatNdService(RpcItemIdStatNdBean bean);
}
