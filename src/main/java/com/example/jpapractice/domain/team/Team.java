package com.example.jpapractice.domain.team;

import com.example.jpapractice.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name", length = 63)
    private String name;

    // 부모쪽에 mappedBy 입력
    // 자식쪽의 멤버명을 적어주면 됨.
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    void changeTeamName(String name) {
        this.name = name;
    }
}

