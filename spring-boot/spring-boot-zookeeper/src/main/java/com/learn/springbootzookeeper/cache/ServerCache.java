package com.learn.springbootzookeeper.cache;

import com.google.common.cache.LoadingCache;
import com.learn.springbootzookeeper.util.ZKUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author :lwy
 * @Date : 2018/8/29 12:42
 * @Description :
 */
@Component
public class ServerCache {

    @Autowired
    private LoadingCache<String,String> cache;

    @Autowired
    private ZKUtil zkUtil;

    private AtomicLong index = new AtomicLong(0);


    public void updateNode(List<String> nodeList) {
        //设置为无效的cache
        cache.invalidateAll();

        nodeList.forEach(node->addCache(node));
    }

    private void addCache(String node) {
        cache.put(node,node);
    }

    public List<String> getAll() {

        List<String> nodeList=new ArrayList<>();
        if(cache.size()==0){
            //刷新
            List<String> nodes=zkUtil.getAllNode();
            for (String node : nodes) {
                String key = node.split("-")[1];
                addCache(key);
            }
        }
        for (Map.Entry<String, String> entry : cache.asMap().entrySet()) {
            nodeList.add(entry.getKey());
        }
        return nodeList;
    }

    /**
     * 选取服务器
     *
     * @return
     */
    public String selectServer() {
        List<String> all = getAll();
        if (all.size() == 0) {
            throw new RuntimeException("路由列表为空");
        }

        //轮训策略 ---LRU,随机，权重等
        Long position = index.incrementAndGet() % all.size();
        if (position < 0) {
            position = 0L;
        }

        return all.get(position.intValue());
    }
}
