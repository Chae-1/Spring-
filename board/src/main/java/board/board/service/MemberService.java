package board.board.service;

import board.board.domain.Member;
import board.board.repository.member.MemberRepository;
import board.board.web.form.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repository;

    public Member join(MemberDto memberDto) {
        Member saved = repository.save(memberDto);
        return saved;
    }

    public Member findById(Long id) {
        return repository.findById(id);
    }
}
