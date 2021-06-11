package com.youcode.marathon_app;

public class Participant {
    private int id;
    private String cin;
    private String name;
    private int age;
    private int phone;

    public Participant(String name, int age, String cin, int phone) {
        this.name = name;
        this.age = age;
        this.cin = cin;
        this.phone = phone;
    }

    public Participant(int id, String name, int age, String cin, int phone) {
        this.id=id;
        this.name = name;
        this.age = age;
        this.cin = cin;
        this.phone = phone;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
