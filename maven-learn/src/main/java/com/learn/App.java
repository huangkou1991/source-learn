package com.learn;

/**
 * Hello world!
 * 打包测试环境  mvn clean package -P test  --查看pom.xml文件  profiles配置
 * 打包正式环境  mvn clean package -P dev
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
