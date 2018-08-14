package com.learn;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author :lwy
 * @date 2018/6/17 17:27
 */
public class TestCase {


    //简单的聚合操作
    @Test
    public void test1() {
        List<String> strings = new ArrayList<>();
        strings.add("rty");
        strings.add("aob");
        strings.add("abo");

        Optional<String> largest = strings.stream().max(String::compareToIgnoreCase);
        if (largest.isPresent()) {
            System.out.println(largest.get());
        }
    }

    //JavaScript引擎 nashorn
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager=new ScriptEngineManager();
        ScriptEngine engine=manager.getEngineByName("nashorn");
        Object result=engine.eval("'hello world ?'.length");

        System.out.println(result);
    }
}
