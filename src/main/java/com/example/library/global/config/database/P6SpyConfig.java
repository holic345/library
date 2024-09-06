package com.example.library.global.config.database;

import com.p6spy.engine.spy.P6SpyOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

/**
 * <h4> P6Spy Query Log 설정 클래스 </h4>
 */
@Configuration
public class P6SpyConfig {

    @PostConstruct
    public void setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(P6SpyPrettySqlFormatter.class.getName());
    }
}
