package board.board.web.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BoardDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    public BoardDto() {

    }

    public BoardDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
