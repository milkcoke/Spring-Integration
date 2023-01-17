package com.example.jpapractice.domain.team;

import com.example.jpapractice.domain.BaseEntity;
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
public class Team extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name", length = 63)
    private String name;

    // 부모쪽에 mappedBy 입력
    // 자식쪽의 멤버명을 적어주면 됨.
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public void registerMember(Member member) {
        members.add(member);
        member.changeTeam(this);
    }
    void changeTeamName(String name) {
        this.name = name;
    }

    @PrePersist()
    public void prePersist() {
        this.updateCreateTimestamp(this.getName());
    }

    @PreUpdate()
    public void preUpdate() {
        this.updateModifiedTimestamp(this.getName());
    }
}

