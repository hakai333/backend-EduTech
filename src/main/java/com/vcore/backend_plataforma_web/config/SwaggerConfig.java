package com.vcore.backend_plataforma_web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI customOpenAPI(){
            return new OpenAPI()
                    .info(new Info()
                            .title("EDUTECH INNOVATORS SPA")
                            .version("1.0")
                            .description("Documentacion de modernizacion para plataforma Edutech"));
        }
}
