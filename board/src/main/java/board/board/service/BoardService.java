package board.board.service;

import board.board.domain.Member;
import board.board.domain.board.Board;
import board.board.domain.board.Comment;
import board.board.repository.board.BoardRepository;
import board.board.repository.member.MemberRepository;
import board.board.web.form.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    public Board save(BoardDto boardDto, Long memberId) {
        Board board = boardRepository.save(boardDto, memberId);
        return board;
    }

    public Board findById(Long boardId) {
        Board board = boardRepository.findById(boardId);
        Member writer = memberRepository.findById(board.getMemberId());
        board.setWriter(writer.getMemberId());
        return board;
    }

    public List<Board> findByAll() {
        return boardRepository.findAll();
    }

    public Comment addComment(Long memberId, Long boardId, String comment) {
        Comment comment1 = boardRepository.addComment(memberId, boardId, comment);
        return comment1;
    }

    public List<Comment> allCommentList(Long boardId) {
        List<Comment> allComment = boardRepository.findAllComment(boardId);
        return allComment;
    }


}
