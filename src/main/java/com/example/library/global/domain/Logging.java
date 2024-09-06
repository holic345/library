package com.example.library.global.domain;

import lombok.Data;

@Data
public class Logging {
    private String SEQ_ID;              // 로그 단위 Unique ID
    private String TRANSACTION_ID;      // 트랜잭션 ID
    private String LOG_TIME;            // 로그를 파일에 Write 시점 시간
    private String SVC_PROFILE;         // 연동 환경(개발[DEV], 검증[STG], 상용[PRD]
    private String SVC_TYPE;            // 요청 클라이언트 구분(관리자[ADMIN], 사용자[CUSTOMER])
    private String SVC_USER;            // 요청 클라이언트 사용자 정보
    private int RESULT_HTTP_CODE;       // 응답 HTTP 상태코드
    private String RESULT_CODE;         // 서비스 상태코드
    private Long PRO_TIME;              // 서비스 처리 시간
    private String REQ_TIME;            // 서비스 요청 시간
    private String RES_TIME;            // 서비스 응답 시간
    private String CLIENT_IP;           // 요청 클라이언트 아이피
    private String API_CODE;            // API 코드
    private String REQUEST_URI;         // 요청 URI
    private String REQUEST_QUERY;       // 요청 쿼리스트링
    private String REQUEST_METHOD;      // 요청 메소드
    private String REQUEST_HEADER;      // 요청 헤더
    private String REQUEST_BODY;        // 요청 전문
    private Integer REQUEST_SIZE;       // 요청 전문 사이즈
    private String RESPONSE_BODY;       // 응답 전문
    private Integer RESPONSE_SIZE;      // 응답 전문 사이즈
}
