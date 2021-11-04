package com.flower.demo.entity;

public class Role {
    private Integer id;

    private String name;

    private String descriptName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescriptName() {
        return descriptName;
    }

    public void setDescriptName(String descriptName) {
        this.descriptName = descriptName == null ? null : descriptName.trim();
    }
}