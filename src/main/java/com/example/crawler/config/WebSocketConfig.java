package com.example.crawler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * @author kpq
 * @since 1.0.0
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter createEndPoint() {
        return new ServerEndpointExporter();
    }
}
