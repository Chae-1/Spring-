package board.board.service;

import board.board.domain.Member;
import board.board.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member doLogin(String loginId, String password) {
        Member member = memberRepository.findByMemberId(loginId);
        if (member == null || !(member.getPassword().equals(password))) {
            return null;
        }
        return member;
    }
}
