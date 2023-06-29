package kwy.study.til.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("KWY API 명세서")
                .description("KWY API 명세서입니다.")
                .version("1.0.0");

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .components(new Components())
                .info(info);
    }
}
