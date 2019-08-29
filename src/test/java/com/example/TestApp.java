package com.example;

import org.apache.camel.RoutesBuilder;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

//import org.junit.Test;

import org.junit.jupiter.api.Test;

class TestApp {

    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new MySpringBootRouter();
    }


    @Test
    public void checkFileExistsInOutputDirectory() throws InterruptedException
    {
        Thread.sleep(5000);
        File file = new File("src/data/output");
        assertTrue(file.isDirectory()); //check if the output directory exists
        assertEquals(1,file.listFiles().length); //check if the number of files moved to output directory is 2
    }

}