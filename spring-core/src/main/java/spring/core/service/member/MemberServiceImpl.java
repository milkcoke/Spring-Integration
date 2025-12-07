package spring.core.service.member;

import spring.core.domain.member.Member;
import spring.core.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    // 생성자에 붙이면 Member Repository 타입에 맞는 애를 자동으로 DI 해줌.
    @Autowired // ac.getBean(MemberRepository.class) 같은 코드가 들어가는 것과 같은 효과
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
