package board.board.repository.member;

import board.board.domain.Member;
import board.board.web.form.MemberDto;

public interface MemberRepository {
    Member save(MemberDto memberDto);
    Member findByMemberId(String memberId);
    Member findById(Long id);

}
