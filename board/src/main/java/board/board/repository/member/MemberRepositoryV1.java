package board.board.repository.member;

import board.board.domain.Member;
import board.board.web.form.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
@Slf4j
@Repository
public class MemberRepositoryV1 implements MemberRepository{
    private final JdbcTemplate jdbcTemplate;

    public MemberRepositoryV1(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(MemberDto memberDto) {
        String sql = "insert into member(member_loginId, member_name, member_password) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((con) -> {
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, memberDto.getMemberId());
            pstmt.setString(2, memberDto.getMemberName());
            pstmt.setString(3, memberDto.getPassword());
            return pstmt;
        }, keyHolder);
        Member member = new Member(memberDto.getMemberId(), memberDto.getMemberName(), memberDto.getPassword());
        member.setId(keyHolder.getKey().longValue());
        return member;
    }

    @Override
    public Member findByMemberId(String memberId) {
        String sql = "select member_id, member_loginId, member_name, member_password from member where member_loginId = ?";
        log.info("memberId = {}", memberId);
        Member member = jdbcTemplate.queryForObject(sql, RowMapper(), memberId);
        log.info("member ={}", member);
        return member;

    }

    @Override
    public Member findById(Long id) {
        String sql = "select member_id, member_loginId, member_name, member_password from member where member_id = ?";
        Member member = jdbcTemplate.queryForObject(sql, RowMapper(), id);
        log.info("member ={}", member);
        return member;
    }

    private RowMapper<Member> RowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setMemberName(rs.getString("member_name"));
            member.setId(rs.getLong("member_id"));
            member.setMemberId(rs.getString("member_loginId"));
            member.setPassword(rs.getString("member_password"));
            return member;
        };
    }
}
