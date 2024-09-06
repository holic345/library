package com.example.library.global.config;

import com.example.library.global.interceptor.HeaderCheckInterceptor;
import com.example.library.global.interceptor.LoggingInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <h4> SpringBoot 환경설정 </h4>
 * <ul>
 *     <li> 인터셉터 </li>
 *     <li> 언어설정 </li> -- 필수 유무 확인 필요
 * </ul>
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfigure implements WebMvcConfigurer {

    private final LoggingInterceptor _loggingInterceptor;
    private final HeaderCheckInterceptor _headerCheckInterceptor;

//    /**
//     * 변경된 언어 정보를 기얼할 로케일 리졸버 생성
//     * 세션 저장 방식 사용
//     */
//    @Bean
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
////        sessionLocaleResolver.setDefaultLocale(new Locale(constructorProperties.getMessageProperty().getLocale()));
//        return sessionLocaleResolver;
//    }
//
//    /**
//     * 언어 변경을 위한 Interceptor 생성
//     */
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
////        lci.setParamName(constructorProperties.getMessageProperty().getStatus().getParamName());
//        return lci;
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // Swagger URL 제외
        registry.addInterceptor(_loggingInterceptor)
            .excludePathPatterns("/v3/api-docs/**",
                                 "/v3/api-docs",
                                 "/swagger-ui/**",
                                 "/healthz");

        // Swagger URL 제외
        registry.addInterceptor(_headerCheckInterceptor)
            .excludePathPatterns("/v3/api-docs/**",
                                 "/v3/api-docs",
                                 "/swagger-ui/**",
                                 "/healthz");
    }
}
