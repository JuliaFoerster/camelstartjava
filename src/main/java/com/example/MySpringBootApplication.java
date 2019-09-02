//package com.example;
//import org.apache.camel.CamelContext;
//import org.apache.camel.impl.DefaultCamelContext;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Option 1 (works also when running 'MySpringBootApplicationTest.java")
 */

//@SpringBootApplication
//public class MySpringBootApplication {
//
//    public static void main(String[] args) {
//
//        MySpringBootRouter routeBuilder = new MySpringBootRouter();
//        CamelContext ctx = new DefaultCamelContext();
//        try {
//            ctx.addRoutes(routeBuilder);
//            ctx.start();
//            Thread.sleep(2000);
//            ctx.stop();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}

/**
 * Option 2 (does not work when running 'MySpringBootApplicationTest.java")
 */
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MySpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }

}

