package falcon.springpractice.repository;

import falcon.springpractice.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

// 모든 테스트는 분리되어 있고 상태를 가져선 안된다.
// 순서에 의존적이지 않은 테스트 코드를 작성해야한다.
// @AfterEach 사용
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 유닛 테스트 하나가 종료될 때마다 Repository 를 초기화해준다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
        repository.initSequence();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("milkcoke");

        // 0으로 ID 저장된 다음에
        repository.save(member);

        // Optional 에서 값 꺼낼땐 get 쓰는게 좋음
        assertThat(0L).isEqualTo(member.getId());
        Member resultMember = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(resultMember);
    }

    @Test
    public void findByName() {
        var member1 = new Member();
        member1.setName("milkcoke1");
        repository.save(member1);

        var member2 = new Member();
        member2.setName("milkcoke2");
        repository.save(member2);

        var resultMember = repository.findByName("milkcoke1").get();

        assertThat(resultMember).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        var member1 = new Member();
        member1.setName("milkcoke1");
        repository.save(member1);

        var member2 = new Member();
        member2.setName("milkcoke2");
        repository.save(member2);

        var members = repository.findAll();

        assertThat(members.size()).isEqualTo(2);
    }
}
