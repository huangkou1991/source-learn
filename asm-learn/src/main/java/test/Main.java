package test;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author :lwy
 * @date 2018/5/28 19:58
 */
public class Main {

    private static AtomicInteger atomicInteger;

    public static void main(String[] args) {

        Integer integer=2;
    }




    //增加
    public void incr(){
        if(atomicInteger==null){
            synchronized (Main.class){
                //初始化
                atomicInteger=new AtomicInteger(0);
            }
        }else{
            int i = atomicInteger.incrementAndGet();
        }
    }

    //减少
    public void decr(){
        atomicInteger.decrementAndGet();
    }
}
