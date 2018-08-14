package com.learn.active.conn;

import com.learn.active.model.Employees;
import org.javalite.activejdbc.Base;

import java.sql.Connection;

/**
 * @author :lwy
 * @date 2018/6/3 9:58
 */
public class Conn {


    public static Connection getConn() {

        Base.open("com.mysql.jdbc.Driver",
                "jdbc:mysql://mysql.cornfield-ads.master:3306/test_suishen_dao", "mysqlsiud", "mysql!@#456");
        return Base.connection();
    }

    public static void close() {
        Base.close();
    }


    public static void main(String[] args) {
        Base.open("com.mysql.jdbc.Driver",
                "jdbc:mysql://mysql.cornfield-ads.master:3306/test_suishen_dao", "mysqlsiud", "mysql!@#456");
        Connection connection = Base.connection();
        System.err.println(connection);

        Employees e=new Employees();
        e.set("first_name", "John");
        e.set("last_name", "Doe");
        e.saveIt();
        Base.close();
    }

}
