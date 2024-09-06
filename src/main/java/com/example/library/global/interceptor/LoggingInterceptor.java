package com.example.library.global.interceptor;

import com.example.library.global.domain.Define;
import com.example.library.global.domain.Logging;
import com.example.library.global.utils.CommonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

/**
 * <h4> 로그 작성 클래스 </h4>
 * <ul>
 *     <li> HandlerInterceptor </li>
 *     <ul>
 *         <li> 요청 전/후, 완료 후 작업 수행 기능 </li>
 *     </ul>
 * </ul>
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingInterceptor implements HandlerInterceptor {

    /*
     * LoggerFactory : 로그 name 지정 후 로그 name 에 맞는 로그 파일에 출력
     * @Slf4j, LogManager : 기본 console 에 출력
     */
    private final Logger       _toyLogger      = LoggerFactory.getLogger("toyLogger");
    private final Logger       _adminlogger    = LoggerFactory.getLogger("toyAdminLogger");
    private final Logger       _customerlogger = LoggerFactory.getLogger("toyCustomerLogger");
    private final ObjectMapper _objectMapper;

    @Getter
    @AllArgsConstructor
    public enum ServiceType {

        ADMIN    ("toy/admin"),
        CUSTOMER ("toy/customer"),
        UNKNOWN  (null);

        private final String url;

        public static ServiceType ofUri(String uri) {
            return Stream.of(values())
                .filter(value -> uri.contains(value.url))
                .findFirst()
                .orElse(UNKNOWN);
        }
    }

    /**
     * <h5> 컨트롤러 요청 전 </h5>
     *  - ex) 요청 전, 완료 까지 걸린 시간 처리를 위해 요청 시간 저장
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.setAttribute("SEQ_ID", createSeqId());
        request.setAttribute("REQ_TIME", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmssSSSZ")));

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * <h5> 컨트롤러 요청 후 </h5>
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * <h5> 모든 요청 완료 후 로그 출력 </h5>
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        /* Spring Security 제외 */
        if (request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) {
            return;
        }

        /* file 관련 요청 제외 (file 요청은 byte 단위) */
        if (request.getContentType() != null
            && request.getContentType().contains(MediaType.MULTIPART_FORM_DATA_VALUE)) {

            HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        }

        /* DataWrappingFilter 에서 Http request, response 캐싱 */
        ContentCachingRequestWrapper cachingRequest   = (ContentCachingRequestWrapper) request;
        ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;

        /* 응답 본문이 있을 경우 (4xx, 5xx 오류 이든 200 이든 응답은 존재) */
        if (cachingResponse.getContentAsByteArray().length != 0) {

            /* RestAPI 는 json 응답 */
            if (cachingResponse.getContentType() != null && cachingResponse.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {

                /* 응답 본문 */
                JsonNode responseBody = _objectMapper.readTree(cachingResponse.getContentAsByteArray());

                if (responseBody != null) {

                    Logging logging   = new Logging();
                    String httpMethod = cachingRequest.getMethod();
                    String requestURI = cachingRequest.getRequestURI();
//                    String apiCode    = ApiUtils.getApiCodeFromUri(requestURI, httpMethod);

                    logging.setSEQ_ID(cachingRequest.getAttribute("SEQ_ID").toString());

                    logging.setTRANSACTION_ID(cachingRequest.getHeader("TRANSACTION_ID"));

                    logging.setCLIENT_IP(cachingRequest.getRemoteAddr());

                    logging.setSVC_PROFILE(Define.SERVER_TYPE.name());

                    logging.setSVC_TYPE(ServiceType.UNKNOWN.name());

                    // TODO 요청헤더에 사용자 정보 생성 후 값 세팅
                    logging.setSVC_USER(null);

//                    logging.setAPI_CODE(apiCode);

                    logging.setREQUEST_METHOD(httpMethod);

                    logging.setREQUEST_URI(requestURI);

                    if (cachingRequest.getContentType() != null && cachingRequest.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {

                        /* 요청 본문 (RequestBody) 이 있을 경우 */
                        if (cachingRequest.getContentAsByteArray().length != 0) {
                            /* 요청 본문 */
                            JsonNode requestBody = _objectMapper.readTree(cachingRequest.getContentAsByteArray());
                            logging.setREQUEST_BODY(gzipCompress(requestBody.toString()));
                        }

                        /* 요청 query string 이 있을 경우 (GET) */
                        if (Objects.equals(HttpMethod.GET.name(), httpMethod)) {
                            logging.setREQUEST_QUERY(cachingRequest.getQueryString());
                        }
                    }

                    if (StringUtils.hasText(logging.getREQUEST_BODY())) {
                        logging.setREQUEST_SIZE(logging.getREQUEST_BODY().length());
                    } else {
                        logging.setREQUEST_SIZE(0);
                    }

                    logging.setRESULT_CODE(responseBody.get("code").toString());

                    /* TODO 추후 개발환경에 따라 응답 본문 출력 유무 설정 (로그가 많이찍히면 성능 문제 생김) */
                    logging.setRESPONSE_BODY(gzipCompress(responseBody.toString()));

                    logging.setRESPONSE_SIZE(logging.getRESPONSE_BODY().length());

                    logging.setREQ_TIME((String) cachingRequest.getAttribute("REQ_TIME"));

                    logging.setRES_TIME(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmssSSSZ")));

                    logging.setPRO_TIME(Long.parseLong(logging.getRES_TIME()) - Long.parseLong(logging.getREQ_TIME()));

                    logging.setRESULT_HTTP_CODE(cachingResponse.getStatus());

                    logging.setLOG_TIME(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));

                    switch (ServiceType.ofUri(requestURI)) {
                        case ADMIN:
                            _adminlogger.info(logging.toString());
                            break;
                        case CUSTOMER:
                            _customerlogger.info(logging.toString());
                            break;
                        case UNKNOWN:
                            _toyLogger.info(logging.toString());
                            break;
                    }
                }
            }
        }

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    // 현재 시간 기준 unique seq id 생성
    private String createSeqId() {
        int random_1 = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;
        int random_2 = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;

        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + random_1 + random_2;
    }

    // body 길이가 1024를 초과할 경우 압축
    private String gzipCompress(String body) {

        if (!StringUtils.hasText(body)) {
            return org.apache.commons.lang3.StringUtils.EMPTY;
        }

        if (body.length() > Define.LOG_MAX_LENGTH) {
            return CommonUtils.gzipCompress(body);
        }

        return body;
    }
}
