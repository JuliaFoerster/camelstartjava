package com.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that makes a web request (input parameters from src/data/input)
 * and saves the output into src/data/output. The route will be called through the main function
 * MySpringBootApplication.java
 */

@Component
public class MySpringBootRouter extends RouteBuilder {

    @Override
    public void configure() {

        // ----- SIMPLE GET REQUEST FROM GEOCODER INPUT long and latitude  -----
        from("file:src/data/input?noop=true")
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        Object message = exchange.getIn().getBody();
                    }
                })
                .log("${body}")
                .enrich("direct:geocoder", Utils.headerEnricherStrategy("postcode"))
                .log("Postcode: ${header.postcode}")
                .log("${body}")
                //ToDo: Correction will not be saved into new file (Enricher does not work as expected!)
                .to("file:src/data/output");


        from("direct:geocoder")
                .setProperty("lon").jsonpath("$.longitude")
                .setProperty("lat").jsonpath("$.latitude")
                .log("Longitude: ${property.lon}")
                .log("Latitude: ${property.lat}")
                .setHeader(Exchange.HTTP_PATH, simple("/postcodes?lon=${property.lon}&lat=${property.lat}"))
                .setHeader(Exchange.HTTP_METHOD, constant(org.apache.camel.component.http4.HttpMethods.GET))
                .to("http4://api.postcodes.io?bridgeEndpoint=true")

                .setBody(constant("postcocde_return_placeholder")); //assume constant feedback

    }
}