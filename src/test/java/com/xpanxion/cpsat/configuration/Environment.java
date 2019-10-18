package com.xpanxion.cpsat.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class Environment {
    private HashMap<String, String> map = new HashMap<>();
    private static Environment instance = null;

    private Environment() {
        Properties properties = new Properties();
        try {
            System.out.println("Reading File : environment.properties");
            properties.load(new FileInputStream("src\\test\\resources\\environment.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<String> propertyNames = properties.stringPropertyNames();
        for (String key : propertyNames) {
            map.put(key, properties.getProperty(key));
        }
    }

    private static Environment getInstance() {
        if (instance == null) {
            instance = new Environment();
        }
        return instance;
    }

    public static String getValue(String key) {
        return getInstance().map.get(key);
    }
}
