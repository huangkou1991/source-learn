package com.learn.tx;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author :lwy
 * @date 2018/7/17 17:19
 */
public class TxLearn {

    public void tx() {

        PlatformTransactionManager transactionManager = null;
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setTimeout(200);
        TransactionStatus status = transactionManager.getTransaction(definition);
        //通过模板方法整合PlatformTransactionManager与Callback

        //TransactionTemplate transactionTemplate=new TransactionTemplate();
    }

    //使用TransactionTemplate  模板方法

    @Transactional()
    public void template(){
        TransactionTemplate template=new TransactionTemplate();
        Object result=template.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {

                return "";
            }
        });
    }
}
