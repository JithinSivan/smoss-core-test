package com.ust.transein.bmw.model;

public class TestModel {
    int id;
    String name;

    @Override
    public String toString() {
        return "TestModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public TestModel(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
