package com.example.jpapractice.domain.member;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Date;


@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    // javax 또는 jakarta 가 JPA 표준 인터페이스
    // Hibernate 은 구현체.

    // JPA 에서는 데이터 변경시 항상 Transaction 안에서 작업해야한다.
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(name = "member_name")
    private String name;
    private Integer  age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Comment("최종 수정 시각")
    private Date lastModifiedDate;

    @Lob
    @Comment("비고 설명")
    private String description;

    public static Member registerMember(String name) {
        var member = new Member();
        member.name = name;
        member.createDate = new Date();
        member.roleType = RoleType.USER;
        return member;
    }
    @Transactional
    public void changeMemberInfo(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.lastModifiedDate = new Date();
    }
}
