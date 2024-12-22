package jpabook.jpashop.repository.member;

import jpabook.jpashop.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberRepositoryTest(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Test
    @Transactional
    public void testMember() {
        // given
        var member = new Member();
        member.changeMemberInfo("memberA", null);
        memberRepository.save(member);

        // when
        Long savedId = memberRepository.save(member);
        var foundMember = memberRepository.findById(savedId);

        // then
        assertThat(member).isEqualTo(foundMember);
        // 같은 영속성 context 내에서는 고유 ID 값이 같으면 동등성, 동일성을 모두 만족한다.
        // 같은 Entity 로 식별한다.
        System.out.println("member: " + member);
        System.out.println("foundMember :" + foundMember);
        assertThat(member).isSameAs(foundMember);
    }
}