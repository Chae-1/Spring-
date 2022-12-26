package board.board.repository.home;

import board.board.web.form.BoardListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository
public class HomeRepository {
    private JdbcTemplate jdbcTemplate;

    public HomeRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<BoardListDto> findAll() {
        String sql = "select b.board_id, b.board_title, m.member_loginId\n" +
                "from board b inner join member m \n" +
                "on m.member_id = b.member_id;";
        List<BoardListDto> boardListDto = jdbcTemplate.query(sql, RowMapper(), null);
        return boardListDto;
    }

    private RowMapper<BoardListDto> RowMapper() {
        return (rs, rowNum) -> {
            BoardListDto dto = new BoardListDto();
            dto.setBoardId(rs.getLong("board_id"));
            dto.setBoardTitle(rs.getString("board_title"));
            dto.setWriter(rs.getString("member_loginId"));
            return dto;
        };
    }


}
