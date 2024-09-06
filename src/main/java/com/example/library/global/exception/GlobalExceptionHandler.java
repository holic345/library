package com.example.library.global.exception;

import com.example.library.global.domain.LibraryResponse;
import com.example.library.global.exception.list.CustomBadRequestException;
import com.example.library.global.exception.list.CustomInternalServerErrorException;
import com.example.library.global.exception.status.StatusCodeBadRequestVO;
import com.example.library.global.exception.status.StatusCodeInternalServerErrorVO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * <h4> 전역 예외 처리 클래스 </h4>
 */
@Order(99)
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger _logger = LoggerFactory.getLogger("exceptionLogger");

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        pageNotFoundLogger.warn(ex.getMessage());
        return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseEntity.status(StatusCodeBadRequestVO.BAD_REQUEST_40000000.getHttpStatus())
            .body(LibraryResponse.builder()
                      .code(StatusCodeBadRequestVO.BAD_REQUEST_40000000.getCode())
                      .desc(StatusCodeBadRequestVO.BAD_REQUEST_40000000.getMessage())
                      .errors(methodArgumentNotValidException.getBindingResult()
                                  .getFieldErrors()
                                  .stream()
                                  .map(c -> {
                                      return LibraryResponse.ValidationError.builder()
                                          .field(c.getField())
                                          .message(c.getDefaultMessage())
                                          .build();
                                  })
                                  .collect(Collectors.toList()))
                      .build());
    }

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return ResponseEntity.status(statusCode)
            .body(LibraryResponse.builder()
                      .code(String.valueOf(statusCode.value()))
                      .desc(ex.getMessage())
                      .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException constraintViolationException) {

        return ResponseEntity.status(StatusCodeBadRequestVO.BAD_REQUEST_40000000.getHttpStatus())
            .body(LibraryResponse.builder()
                      .code(StatusCodeBadRequestVO.BAD_REQUEST_40000000.getCode())
                      .desc(StatusCodeBadRequestVO.BAD_REQUEST_40000000.getMessage())
                      .errors(constraintViolationException.getConstraintViolations()
                                  .stream()
                                  .map(c -> {
                                      Stream<Path.Node> stream = StreamSupport.stream(c.getPropertyPath().spliterator(), false);
                                      List<Path.Node> list = stream.toList();

                                      return LibraryResponse.ValidationError.builder()
                                          .field(list.getLast().getName())
                                          .message(c.getMessage())
                                          .build();
                                  })
                                  .collect(Collectors.toList()))
                      .build());
    }

    /**
     * <h6> CustomBadRequest Global Exception </h6>
     * @param customBadRequestException
     * @return
     */
    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<Object> handleCustomBadRequestException(CustomBadRequestException customBadRequestException) {
        return ResponseEntity.status(customBadRequestException.getErrorCode().getHttpStatus())
            .body(LibraryResponse.builder()
                      .code(customBadRequestException.getErrorCode().getCode())
                      .desc(customBadRequestException.getErrorCode().getMessage())
                      .errorMsg(customBadRequestException.getMessage())
                      .build());
    }

    /**
     * <h6> CustominternalServerError Global Exception </h6>
     * @param customInternalServerErrorException
     * @return
     */
    @ExceptionHandler(CustomInternalServerErrorException.class)
    public ResponseEntity<Object> handleCustomInternalServerErrorException(CustomInternalServerErrorException customInternalServerErrorException) {
        return ResponseEntity.status(customInternalServerErrorException.getErrorCode().getHttpStatus())
            .body(LibraryResponse.builder()
                      .code(customInternalServerErrorException.getErrorCode().getCode())
                      .desc(customInternalServerErrorException.getErrorCode().getMessage())
                      .errorMsg(customInternalServerErrorException.getMessage())
                      .build());
    }

    /**
     * <h6> IllegalArgument Global Exception </h6>
     * @param illegalArgumentException
     * @param request
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException, WebRequest request) {

        _logger.error("IllegalArgumentException", illegalArgumentException);

        return ResponseEntity.status(StatusCodeInternalServerErrorVO.INTERNAL_SERVER_ERROR_50000000.getHttpStatus())
            .body(LibraryResponse.builder()
                      .code(StatusCodeInternalServerErrorVO.INTERNAL_SERVER_ERROR_50000000.getCode())
                      .desc(StatusCodeInternalServerErrorVO.INTERNAL_SERVER_ERROR_50000000.getMessage())
                      .errorMsg(illegalArgumentException.getMessage())
                      .build());
    }

    /**
     * <h6> NullPointer Global Exception </h6>
     * @param nullPointerException
     * @param request
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException nullPointerException, WebRequest request) {

        _logger.error("NullPointerException", nullPointerException);

        return ResponseEntity.status(StatusCodeInternalServerErrorVO.INTERNAL_SERVER_ERROR_50000000.getHttpStatus())
            .body(LibraryResponse.builder()
                      .code(StatusCodeInternalServerErrorVO.INTERNAL_SERVER_ERROR_50000000.getCode())
                      .desc(StatusCodeInternalServerErrorVO.INTERNAL_SERVER_ERROR_50000000.getMessage())
                      .errorMsg(nullPointerException.getStackTrace() != null ? nullPointerException.getStackTrace()[0].toString() : "nullPointerException")
                      .build());
    }
}
