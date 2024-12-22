package falcon.springpractice.service;

import falcon.springpractice.domain.Member;
import falcon.springpractice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // 외부에서 member repository 를 넣어줌 (DI)
    // 외부에서 넣도록 singleton
    // 요새는 '생성자 의존성 주입' 방식이 권장된다.
    // 의존관계가 동적으로 변해야할 상황이 없기 때문이다.
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 회원이름은 유일성 검증
        validateDuplicateMemberName(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMemberName(Member member) {
        memberRepository.findByName(member.getName())
                // Optional 로 Wrapping 하여 다양한 메소드를 지원.
                .ifPresent(tempMember -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        // Service 는 비즈니스 쪽으로 네이밍해야해!
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
