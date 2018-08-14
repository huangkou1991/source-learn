package com.learn.dozer;

import java.util.Objects;

/**
 * 目标文件
 */
public class Source {
    private String name;
    private int age;

    public Source() {
    }

    public Source(String name, int age) {
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
        if (!(o instanceof Source)) return false;
        Source source = (Source) o;
        return age == source.age &&
                Objects.equals(name, source.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age);
    }
}
