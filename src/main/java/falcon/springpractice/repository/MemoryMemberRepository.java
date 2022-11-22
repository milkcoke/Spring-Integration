package falcon.springpractice.repository;

import falcon.springpractice.domain.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryMemberRepository implements MemberRepository{
    // static 변수에 대해 동시성 이슈해결을 위해 ConcurrentHashMap 사용
    private static final Map<Long, Member> store = new ConcurrentHashMap<Long, Member>();
    // Prevent from the concurrent issue
    private static final AtomicLong sequence = new AtomicLong();

    @Override
    public Member save(Member member) {
        var currentSequence = sequence.get();

        member.setId(currentSequence);
        store.put(currentSequence, member);

        sequence.set(currentSequence + 1);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // Nullable 할 때는 Optional 로 감싸서 리턴하는게 요새 국룰
        return Optional.ofNullable(store.get(id));
    }


    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member-> member.getName().equals(name))
                // 얘도 Optional 로 감싸져있음.
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<Member>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

    public void initSequence() {
        sequence.set(0);
    }
}
