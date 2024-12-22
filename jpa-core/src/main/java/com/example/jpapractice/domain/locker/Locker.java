package com.example.jpapractice.domain.locker;

import com.example.jpapractice.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Locker {
    @Id @GeneratedValue
    @Column(name = "locker_id")
    private Long id;

    @Column(name = "locker_name")
    private String name;

    public Locker(String name) {
        this.name = name;
    }
    /**
     * Locker 로 부터 Member 조회가 자주 필요할 때만 추가하면 된다.
     */
    // mapped by 걸린 곳은 조회만 가능함.
//    @OneToOne(mappedBy = "locker")
//    @Column(name = "member_id")
//    private Member member;
}
