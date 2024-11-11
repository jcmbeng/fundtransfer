package com.jcmbeng.fundtransfer.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${api.config.dev-url}")
    private String devUrl;

    @Value("${api.config.prod-url}")
    private String prodUrl;

    @Value("${api.config.version}")
    private String apiVersion;

    @Value("${api.config.title}")
    private String title;

    @Value("${api.config.description}")
    private String description;

    @Value("${api.config.termsOfService}")
    private String termsOfService;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("jclaudembeng@gmail.com");
        contact.setName("JCM");
        contact.setUrl("https://www.neobank-api.com");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title(title)
                .version(apiVersion)
                .contact(contact)
                .description(description)
                .termsOfService(termsOfService)
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
