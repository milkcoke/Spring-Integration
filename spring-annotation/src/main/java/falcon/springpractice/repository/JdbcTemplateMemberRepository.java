package falcon.springpractice.repository;

import falcon.springpractice.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    // 생성자가 1개일 때는 Spring Bean 등록시 Autowired 생략 가능.
    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        // 이 형식은 그냥 암기해야함.
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        var jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        var parameters = new HashMap<String, Object>();

        var key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        var memberList = jdbcTemplate.query("SELECT * FROM member WHERE id = ?", memberRowMapper(), id);
        return memberList.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        var memberList = jdbcTemplate.query("SELECT * FROM member WHERE name = ?", memberRowMapper(), name);
        return memberList.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM member", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            var member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }

}
