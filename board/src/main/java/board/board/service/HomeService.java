package board.board.service;

import board.board.repository.board.BoardRepository;
import board.board.repository.home.HomeRepository;
import board.board.repository.member.MemberRepository;
import board.board.web.form.BoardListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final HomeRepository repository;

    public List<BoardListDto> findAll() {
        List<BoardListDto> all = repository.findAll();
        return all;
    }
}
