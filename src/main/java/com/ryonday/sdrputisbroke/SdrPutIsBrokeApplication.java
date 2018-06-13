package com.ryonday.sdrputisbroke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SdrPutIsBrokeApplication {

    public static void main(String[] args) {

        System.setProperty("sqlite4java.library.path", "build/sqlite4-libs");
        System.setProperty("java.library.path", "build/sqlite4-libs");

        SpringApplication.run(SdrPutIsBrokeApplication.class, args);
    }
}
