package com.zodiac.polit.bean;

public class AppBean {

    private int name;

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AppBean{" +
                "name=" + name +
                '}';
    }
}