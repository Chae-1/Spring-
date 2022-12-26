package board.board.service;

import board.board.repository.home.HomeRepository;
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

    public List<BoardListDto> findHomeBoardList(Integer boardSize, Integer currentPage) {
        List<BoardListDto> all = repository.findHomeBoardList(boardSize, currentPage);
        return all;
    }

    public Integer boardSize() {
        return repository.boardSize();
    }
}
