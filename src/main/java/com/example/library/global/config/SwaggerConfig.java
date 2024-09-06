package com.example.library.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * <h4> Swagger 설정 클래스 </h4>
 */
@Profile({"local", "dev", "stg"})
@Configuration
public class SwaggerConfig {

    /**
     * Swagger 상세 설정
     * @param version
     * @return
     */
    @Bean
    public OpenAPI openApi(@Value("v1.0") String version) {
        Info info = new Info().title("토이 샘플 프로젝트 API Document")
                              .version(version)
                              .description("토이 샘플 프로젝트 인터페이스 명세서 입니다.");

        Server localServer = new Server();
        localServer.url("http://localhost:8080");
        localServer.description("local(8080)");

        return new OpenAPI().info(info)
                            .servers(List.of(localServer));
    }
}
