package com.example.library.global.domain;

import com.example.library.global.exception.status.StatusCodeOkVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

/**
 * <h4> 공통 응답 객체 </h4>
 */
@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)  // NULL 필드는 노출하지않는다.
public class LibraryResponse<T> {

    @Schema(name = "code", description = "응답코드", example = "20000000")
    private String code;

    @Schema(name = "desc", description = "응답 메세지", example = "요청 성공")
    private String desc;

    @Schema(name = "result", description = "응답 결과 객체")
    private T result;

    @Schema(name = "errorMsg", description = "상세 에러 메세지")
    @JsonInclude(Include.NON_EMPTY)
    private String errorMsg;

    @ArraySchema(schema = @Schema(name = "errors", description = "객체 유효성 검사 에러 객체"))
    @JsonInclude(Include.NON_EMPTY)
    private List<ValidationError> errors;

    @Getter @Builder
    public static class ValidationError {

        private String field;
        private String message;

        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                                  .field(fieldError.getField())
                                  .message(fieldError.getDefaultMessage())
                                  .build();
        }
    }

    public static <T> LibraryResponse<T> ok(T result) {
        return LibraryResponse.<T>builder()
            .code(StatusCodeOkVO.OK_20000000.getCode())
            .desc(StatusCodeOkVO.OK_20000000.getMessage())
            .result(result)
            .build();
    }

}
