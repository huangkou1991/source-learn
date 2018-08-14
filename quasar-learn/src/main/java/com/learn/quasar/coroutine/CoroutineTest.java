package com.learn.quasar.coroutine;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.channels.Channel;
import co.paralleluniverse.strands.channels.Channels;

/**
 * @author :lwy
 * @date 2018/8/3 9:39
 */
public class CoroutineTest {


    private static void printer(Channel<Integer> in) throws InterruptedException, SuspendExecution {
        Integer v;
        while ((v = in.receive()) != null) {
            System.out.println(v);
        }
    }

    public static void main(String[] args) throws SuspendExecution, InterruptedException {

        Channel<Integer> natures = Channels.newChannel(-1);
        Channel<Integer> squares = Channels.newChannel(-1);

        //Producer
        new Fiber<>(() -> {
            for (int i = 0; i < 10; i++) {

                natures.send(i);
            }
            natures.close();
        }).start();


        //Consumer
        new Fiber<Void>(() -> {
            Integer v;
            while ((v = natures.receive()) != null) {
                squares.send(v * v);
            }
            squares.close();
        }).start();

        printer(squares);
    }
}
