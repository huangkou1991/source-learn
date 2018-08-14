package com.learn.dozer;

public class People {
    private String peopleName;
    private String peopleAddress;
    private int age;

    public People() {
    }

    public People(String peopleName, String peopleAddress, int age) {
        this.peopleName = peopleName;
        this.peopleAddress = peopleAddress;
        this.age = age;
    }


    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getPeopleAddress() {
        return peopleAddress;
    }

    public void setPeopleAddress(String peopleAddress) {
        this.peopleAddress = peopleAddress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
