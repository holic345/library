package com.example.library.global.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * <h4> 상수 값 정의 클래스 </h4>
 * <ul>
 *     <li> 불변성을 갖는다.</li>
 * </ul>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Define {

    public static ServerType        SERVER_TYPE = null;
    public static String            SERVER_VERSION = StringUtils.EMPTY;

    public static Integer           LOG_MAX_LENGTH = 1024;

    @Getter
    @AllArgsConstructor
    public enum ServerType {

        LOCAL ("로컬 서버"),
        DEV   ("개발 서버"),
        STG   ("스테이징(검수) 서버"),
        PRD   ("상용 서버");

        private final String desc;
    }
}
