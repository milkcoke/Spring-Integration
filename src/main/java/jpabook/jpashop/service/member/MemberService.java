package jpabook.jpashop.service.member;

import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// JPA 에 국룰. 모든 메소드를 Transaction 단위로 묶어 중간에 실패시 ROLLBACK, 성공시 COMMIT 처리.
@Transactional(readOnly = true)
@RequiredArgsConstructor // final 이 달려있는 field 에 대해서만 생성자 주입
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = false)
    public Long join(Member member) {
        hasDuplicate(member);
        return memberRepository.save(member);
    }

    // 이름 중복 가입 방지
    public boolean hasDuplicate(Member member){
        // 동일한 이름의 member 가 동시에 회원가입 요청시 둘다 로직이 통과할 수 있으므로
        // userName 에 unique 제약조건을 거는 것을 고려해봄직 하다.
        List<Member>foundMembers = memberRepository.findByName(member.getUserName());
        if (!foundMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        return true;
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
