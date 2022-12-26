package board.board.web.form;

import lombok.Data;

@Data
public class BoardListDto {
    private Long boardId;
    private String boardTitle;
    private String writer;

}
