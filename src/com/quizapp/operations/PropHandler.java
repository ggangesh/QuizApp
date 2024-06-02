package com.quizapp.operations;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropHandler {
    private static Properties prop;

    public static Properties getApplicationProperties() {
        try {
            FileReader fr = new FileReader("application.properties");
            prop = new Properties();
            prop.load(fr);
        } catch (FileNotFoundException e) {
            System.out.println("Application properties not found. Please check Readme.md");
            e.printStackTrace();
            //TODO better Excpetions Handling
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }
}
