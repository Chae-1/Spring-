package board.board.domain;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private String memberId;
    private String memberName;
    private String password;

    public Member() {

    }

    public Member(String memberId, String memberName, String password) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.password = password;
    }
}
