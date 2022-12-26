package board.board.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberDto {
    @NotBlank
    private String memberName;
    @NotBlank
    private String memberId;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordValidation;

    public MemberDto () {

    }

    public MemberDto(String memberName, String memberId, String password, String passwordValidation) {
        this.memberName = memberName;
        this.memberId = memberId;
        this.password = password;
        this.passwordValidation = passwordValidation;
    }
}
