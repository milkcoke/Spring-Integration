package com.example.jpapractice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
// 그냥 property 를 다른 테이블에서 공통으로 쓰고싶을 때 사용
// 운영에 필요한 테이블이다.
@MappedSuperclass
public abstract class BaseEntity {

    // @Entity 어노테이션이 존재하지 않아도 @Column 등의 어노테이션 사용은 가능하다.
    // 모든 테이블에 다음 속성을 공통적으로 가져야한다고 가정해보자.
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_timestamp", nullable = false)
    private LocalDateTime createdTimestamp;

    @Column(name = "last_modified_by", nullable = true)
    private String lastModifiedBy;

    @Column(name = "last_modified_timestamp", nullable = true)
    private LocalDateTime lastModifiedTimestamp;

    public void updateCreateTimestamp(String createdBy) {
        this.createdBy = createdBy;
        this.createdTimestamp = LocalDateTime.now();
    }

    public void updateModifiedTimestamp(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTimestamp = LocalDateTime.now();
    }
}
