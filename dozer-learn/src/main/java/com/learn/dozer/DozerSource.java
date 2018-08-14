package com.learn.dozer;

import java.util.Objects;

/**
 * 映射文件
 */
public class DozerSource {
    private String name;
    private int age;
    public DozerSource() {

    }
    public DozerSource(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DozerSource)) return false;
        DozerSource dest = (DozerSource) o;
        return age == dest.age &&
                Objects.equals(name, dest.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age);
    }
}
