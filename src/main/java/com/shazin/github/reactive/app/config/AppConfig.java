package com.shazin.github.reactive.app.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({WebConfig.class})
@ComponentScan(basePackages={"com.shazin.github.reactive.app.service.*"})
public class AppConfig {
	
	private final int DEFAULT_PORT = 8080;
	
	@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
        	String strPort = System.getProperty("server.port");
        	int port = DEFAULT_PORT;
        	if(strPort != null && strPort.length() > 0) {
        		try {
        			port = Integer.parseInt(strPort);
        		} catch(NumberFormatException e) {
        			e.printStackTrace();
        		}
        	}
            container.setPort(port);
        });
    }

	
}
