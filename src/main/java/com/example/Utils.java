package com.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.script.ScriptBuilder;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.apache.camel.processor.aggregate.AggregationStrategy;


public class Utils {
	public static Processor javascript(String scriptResourceName) {
		try {
			String driver = "jvm-npm.js";
			return new ScriptBuilder("javaScript", ""
					+ "load({name:'"+driver+"',script:com.google.common.io.CharStreams.toString("+
					"new java.io.InputStreamReader(java.lang.Thread.currentThread().getContextClassLoader()."+
					"getResourceAsStream('"+driver+"'), com.google.common.base.Charsets.UTF_8))});\n"
					+ Resources.toString(Resources.getResource(scriptResourceName), Charsets.UTF_8));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	 //Creates a strategy, which sets the additional body as a header on the original exchange.
	public static AggregationStrategy headerEnricherStrategy(final String headerName) {
		return new AggregationStrategy() {
			//https://camel.apache.org/maven/current/camel-core/apidocs/org/apache/camel/processor/aggregate/AggregationStrategy.html
			public Exchange aggregate(Exchange original, Exchange newExchange) {
				original.getIn().setHeader(headerName, newExchange.getIn().getBody()); //new header will be set on top
				return original;
			}
		};
	}
}
