package com.dl.gradle.todo;

public class TodoItem {

    private String name;

    private boolean hasDone;

    public TodoItem(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name + (hasDone ?"hasDone" : "need to do") + "!";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasDone() {
        return hasDone;
    }

    public void setHasDone(boolean hasDone) {
        this.hasDone = hasDone;
    }
}
