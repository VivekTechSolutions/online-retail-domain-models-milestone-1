package com.retail_inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Online Retail Inventory Service")
                        .description("By Vivek Kumar")
                        .version("1.0"))
                .servers(List.of(
                		new Server().url("http://localhost:8082").description("local server 2"),
                        new Server().url("http://localhost:8080").description("Local server"),
                        new Server().url("https://dev.retail.com").description("Development server"),
                        new Server().url("https://prod.retail.com").description("Production server")
                ));
    }
}
