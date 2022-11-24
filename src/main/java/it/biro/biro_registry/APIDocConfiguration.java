package it.biro.biro_registry;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${springdoc.version}") String version,
            @Value("${springdoc.description}") String description,
            @Value("${springdoc.title}") String title,
            @Value("${springdoc.licenseName}") String licenseName,
            @Value("${springdoc.licenseURI}") String licenseURI,
            @Value("${springdoc.summary}") String summary,
            @Value("${springdoc.securityEnabled}") boolean securityEnabled,
            @Value("${springdoc.securitySchemes}") String securitySchemes,
            @Value("${springdoc.securityScheme}") String securityScheme) {
        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version(version)
                        .license(new License().name(licenseName).url(licenseURI))
                        .summary(summary)
                        .description(description));
        if (securityEnabled) {
            openAPI.components(new Components().addSecuritySchemes(securitySchemes,
                    new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme(securityScheme)));
        }

        return openAPI;
    }
}
