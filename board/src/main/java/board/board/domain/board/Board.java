package board.board.domain.board;

import lombok.Data;

import java.util.List;

@Data
public class Board {
    private Long id;
    private String title;
    private String boardContent;
    private Long memberId;
    private String writer;
    private List<Comment> commentList;
    public Board() {

    }
    public Board(String title, String boardContent, Long memberId) {
        this.title = title;
        this.boardContent = boardContent;
        this.memberId = memberId;
    }
}
