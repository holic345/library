package com.example.library.global.config.database;

import java.util.Optional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

/**
 * <h4> Spring Data JPA의 Auditing 기능으로 사용할 사용자 정보를 커스텀하게 설정하는 클래스 </h4>
 */
@Configuration
public class AuditorAwareConfig implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // TODO 사용자 정보 설정
        return Optional.of("userID");
    }
}
