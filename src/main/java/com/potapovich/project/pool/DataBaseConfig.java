package com.potapovich.project.pool;


import java.util.ResourceBundle;

public class DataBaseConfig {
    static String url;
    static String user;
    static String password;

    private static final String CONFIG_PROPERTIES = "configuration";

    static {
        ResourceBundle prop = ResourceBundle.getBundle(CONFIG_PROPERTIES);
        url = prop.getString("url");
        user = prop.getString("user");
        password = prop.getString("password");
    }
}
