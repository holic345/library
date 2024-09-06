package com.example.library.global.interceptor;

import com.example.library.global.domain.HeaderCheck;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * <h4> 클라이언트 로그인 검증 인터셉터 </h4>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class HeaderCheckInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 요청된 Controller Method
        HandlerMethod method = (HandlerMethod) handler;
        HeaderCheck headerCheck = method.getMethodAnnotation(HeaderCheck.class);

        /*
         * 로그인 필수 o
         * 1. 어노테이션이 없을 경우
         * 2. headerCheck.required == true
         */
        if (headerCheck == null || headerCheck.required()) {

            // 사용자 정보가 없을 경우 throw Exception
//            throw new CustomBadRequestException(ToyStatusCodeBadRequestVO.BAD_REQUEST_40000001);
            // Header에 있는 로그인한 사용자 정보 request에 저장
        }

        return preHandle(request, response, handler);
    }
}
