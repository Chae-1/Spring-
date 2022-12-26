package board.board.domain.board;

import lombok.Data;

@Data
public class Comment {
    private Long memberId;
    private Long boardId;
    private String writer;
    private String content;

    public Comment(Long memberId, Long boardId, String content) {
        this.memberId = memberId;
        this.boardId = boardId;
        this.content = content;
    }

    public Comment() {

    }
}
