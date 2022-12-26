package board.board.repository.board;

import board.board.domain.board.Board;
import board.board.domain.board.Comment;
import board.board.web.form.BoardDto;

import java.util.List;

public interface BoardRepository {
    public Board save(BoardDto boardDto, Long memberId);
    public Board findById(Long id);
    public List<Board> findAll();
    public void delete(Long id);
    public Comment addComment(Long memberId, Long boardId, String content);
    public List<Comment> findAllComment(Long boardId);
}
