package com.ggfitness.model;

public class Classes
{
    private int class_id;
    private String name;
    private String description;
    private int capacity;

    public Classes(String name, String description, int capacity)
    {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
    }
}