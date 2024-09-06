package com.example.library.global.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

/**
 * <h4> 요청 data, 응답 data 를 interceptor 에서 한번에 처리 하기 위한 설정 </h4>
 * <ul>
 *     <li> OncePerRequestFilter </li>
 *     <ul>
 *         <li> Http 요청에 대해 필터가 한번만 실행 되도록 보장 </li>
 *         <li> 리소스 사용 최적화 </li>
 *         <li> 응답 시간 개선 </li>
 *         <li> 부작용 방지 </li>
 *     </ul>
 * </ul>
 */
@Component
public class DataWrappingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // http 요청의 request 를 캐싱
        ContentCachingRequestWrapper cachingRequest = new ContentCachingRequestWrapper(request);

        // http 응답의 response 를 캐싱
        ContentCachingResponseWrapper cachingResponse = new ContentCachingResponseWrapper(response);

        // 캐싱된 request 와 캐싱된 response 를 필터로 요청 (특정 기능이 완료 되기전 까지의 모든 request, response 가 저장)
        filterChain.doFilter(cachingRequest, cachingResponse);

        /*
         * 모든 response 가 저장된 response 에서 응답 본문을 복사
         *  - 원본 data 를 보존 하며 이후 복사한 응답 본문을 수정, 수정된 응답 본문을 응답 할 수있음
         */
        cachingResponse.copyBodyToResponse();
    }
}
