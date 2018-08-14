package com.learn.active.test;

import com.learn.active.model.Employees;
import com.learn.active.model.Role;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 * @author :lwy
 * @date 2018/6/3 10:12
 */
public class App {

    public static void main(String[] args) {


        App a = new App();
        Base.open("com.mysql.jdbc.Driver",
                "jdbc:mysql://mysql.cornfield-ads.master:3306/test_suishen_dao", "mysqlsiud", "mysql!@#456");
        //a.create2();
        a.select();
        //a.update();
        Base.close();

    }

    private void update() {
        Employees e =Employees.findFirst("first_name = ?", "John");
        e.set("last_name","wade");
        e.saveIt();
    }

    private void select() {

        Employees e =Employees.findFirst("first_name = ?", "John");
        System.err.println(e);
    }

    private void create() {
        Employees e = new Employees();
        e.set("first_name", "John");
        e.set("last_name", "Doe");
        e.saveIt();
    }

    protected void create2() {
        Employees employee = new Employees();
        employee.set("first_name", "hello");
        employee.set("last_name", "world");
        employee.saveIt();
        employee.add(new Role("Java Developer","BN"));
        LazyList<Model> all = Employees.findAll();
        System.out.println(all.size());
    }
}
