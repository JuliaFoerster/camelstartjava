package com.example;

import io.specto.hoverfly.junit.core.HoverflyConfig;

import io.specto.hoverfly.junit.core.SimulationSource;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.ClassRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

/**
 * Two test function defined below. Both are running on CaptureOrSimulation mode.
 * The first test uses a rest-template, the second test activates the Camel route in MySpringBootRouter.java
 * through the main function 'MySpringBootApplication.java'.
 * In MySpringBootApplication.java you will find two options on how to activate the route.
 * Option 2 does not allow JUnit Testing (inout parameters coming from line 96 in this script will not be accepted)
 * Option 1 works perfectly fine for JUnit Testing
 */

@SpringBootTest
public class MySpringBootApplicationTest {

    public MySpringBootApplicationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    // teardown at end of all tests
    public static void tearDownClass() {
    }

    @Before
    // To execute a new text
    public void setUp() {
    }

    @After
    // executed for every single unit test (annotated with @Test)
    public void tearDown() {
    }

    /**
    Test 1:
    Use rest template to capture a web-request to api.postcodes.io and simulate it for all runs after the first run.
     */

//    @ClassRule
//    public static HoverflyRule hoverflyRule = HoverflyRule
//            .inCaptureOrSimulationMode("postcode_request_template.json", HoverflyConfig.localConfigs().proxyLocalHost()).printSimulationData();
//
//    @Test
//    public void testRestTemplate() throws IOException {
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        String result = restTemplate.getForObject("http://api.postcodes.io/postcodes/SK142DR", String.class);
//
//        assertEquals("{\"status\":200,\"result\":{\"postcode\":\"SK14 2DR\",\"quality\":1,\"eastings\":394147," +
//                "\"northings\":395098,\"country\":\"England\",\"nhs_ha\":\"North West\"," +
//                "\"longitude\":-2.089603,\"latitude\":53.452623,\"european_electoral_region\":\"North West\"," +
//                "\"primary_care_trust\":\"Tameside and Glossop\",\"region\":\"North West\"," +
//                "\"lsoa\":\"Tameside 028E\",\"msoa\":\"Tameside 028\",\"incode\":\"2DR\",\"outcode\":\"SK14\"," +
//                "\"parliamentary_constituency\":\"Stalybridge and Hyde\",\"admin_district\":\"Tameside\"," +
//                "\"parish\":\"Tameside, unparished area\",\"admin_county\":null,\"admin_ward\":\"Hyde Godley\"," +
//                "\"ced\":null,\"ccg\":\"NHS Tameside and Glossop\",\"nuts\":\"Greater Manchester South East\"," +
//                "\"codes\":{\"admin_district\":\"E08000008\",\"admin_county\":\"E99999999\",\"admin_ward\":\"E05000811\"," +
//                "\"parish\":\"E43000162\",\"parliamentary_constituency\":\"E14000967\",\"ccg\":\"E38000182\"," +
//                "\"ced\":\"E99999999\",\"nuts\":\"UKD35\"}}}",result);
//    }

    /**
     * Test 2:
     * Uses a Camel Route (instead of rest template) to capture a web-request to api.postcodes.io and simulate it
     * for all runs after the first run.
     */

    //To Capture and Simulate
    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule
            .inCaptureOrSimulationMode("./postcode_request_camel_route.json", HoverflyConfig.localConfigs().proxyLocalHost()).printSimulationData();

    @Test
    public void testCamelRoutes() throws IOException, InterruptedException {
        System.out.println("testCamelRoutes");

        String result = new String(Files.readAllBytes(Paths.get("./src/data/output/postcodes.json")), StandardCharsets.UTF_8);

        assertEquals("{\n  \"postcodes\" : \"SE16 7TD\",\n  \"longitude\": \"0.629834723775309\" ,\n  \"latitude\":\"51.7923246977375\"\n}\n",result);
    }

}


