package com.learn;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.learn.guice.Communication;
import com.learn.guice.module.BasicModule;

import java.util.Scanner;

/**
 * @author :lwy
 * @date 2018/5/31 18:07
 * https://blog.csdn.net/u011054333/article/details/57179999
 */
public class RunGuice {

    public static void main(String[] args) {
        Injector injector= Guice.createInjector(new BasicModule());
        Communication comms = injector.getInstance(Communication.class);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                System.exit(0);
            } else {
                comms.sendMessage(input);
            }

        }
    }
}
