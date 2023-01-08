package com.example.jpapractice.domain.member;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


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

    public static Member registerMember(String name) {
        var member = new Member();
        member.name = name;
        return member;
    }
    @Transactional
    public void changeMemberInfo(String name) {
        this.name = name;
    }
}
