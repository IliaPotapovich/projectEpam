package com.potapovich.project.logic;

public class IdGenerator {
    private static int id = 0;

    private IdGenerator() {
    }

    public static int getId() {
        return id;
    }

    public static int getIdWithIncr() {
        return ++id;
    }
}
