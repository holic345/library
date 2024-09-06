package com.example.library.global.domain;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * <h4> Spring Data JPA에서 등록/수정자 와 등록/수정일자에 대해서 자동으로 값을 넣어주는 기능 </h4>
 * <ul>
 *     <li> 등록/수정자와 등록/수정일자가 모두 필요한 경우 사용 </li>
 *     <li> 등록/수정일자만 필요한 경우 BaseTimeEntity만 상속받아서 사용 </li>
 *     <li> 등록/수정자만 필요한 경우 해당 클래스를 사용하지못하며 엔티티 단위에서 별도로 생성해야됨 </li>
 * </ul>
 */
@Getter
@MappedSuperclass   // JPA Entity 클래스들이 해당 추상 클래스를 상속할 경우 createDate, modifiedDate를 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class)  // 해당 클래스에 Auditing 기능을 포함
public abstract class BaseEntity extends BaseTimeEntity {

    // Entity가 생성되어 저장될 때 사용자 정보가 자동 저장 (AuditorAwareConfig.java 에서 return 한 사용자 정보)
    @CreatedBy
    @Column(updatable = false, nullable = false)
    private String createdBy;

    // 조회한 Entity 값을 변경할 때 사용자 정보가 자동 저장 (AuditorAwareConfig.java 에서 return 한 사용자 정보)
    @LastModifiedBy
    private String modifiedBy;
}
