package board.board.repository.board;

import board.board.domain.board.Board;
import board.board.domain.board.Comment;
import board.board.web.form.BoardDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class BoardRepositoryV1 implements BoardRepository{
    private final JdbcTemplate boardJdbcTemplate;

    public BoardRepositoryV1(DataSource dataSource) {
        boardJdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Board save(BoardDto boardDto, Long memberId) {
        String sql = "insert into board(board_title, board_content, member_id) values(?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Board board = new Board(boardDto.getTitle(), boardDto.getContent(), memberId);
        boardJdbcTemplate.update((con) -> {
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, boardDto.getTitle());
            pstmt.setString(2, boardDto.getContent());
            pstmt.setLong(3, memberId);
            return pstmt;
        }, keyHolder);
        board.setId(keyHolder.getKey().longValue());
        return board;
    }

    @Override
    public Board findById(Long id) {
        String sql = "select board_id, board_title, board_content, member_id from board where board_id = ?";
        Board board = boardJdbcTemplate.queryForObject(sql, boardRowMapper(), id);
        return board;
    }

    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();
            board.setId(rs.getLong("board_id"));
            board.setMemberId(rs.getLong("member_id"));
            board.setBoardContent(rs.getString("board_content"));
            board.setTitle(rs.getString("board_title"));
            return board;
        };
    }

    @Override
    public List<Board> findAll() {
        String sql = "select board_id, board_title, board_content, member_id from board";
        List<Board> boardList = boardJdbcTemplate.query(sql, boardRowMapper());
        return boardList;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Comment addComment(Long memberId, Long boardId, String content) {
        String sql = "insert into comment(member_id, board_id, comment_content) values (?, ?, ?)";
        Comment comment = new Comment(memberId, boardId, content);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        boardJdbcTemplate.update(((con) -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, memberId);
            preparedStatement.setLong(2, boardId);
            preparedStatement.setString(3, content);
            return preparedStatement;
        }), keyHolder);
        return comment;
    }

    @Override
    public List<Comment> findAllComment(Long boardId) {
        String sql = "select c.member_id, c.board_id, c.comment_content, m.member_loginId " +
                "from comment c " +
                "inner join member m " +
                "on m.member_id = c.member_id where c.board_id = ?";
        List<Comment> commentList = boardJdbcTemplate.query(sql, commentRowMapper(), boardId);
        return commentList;
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> {
          Comment comment = new Comment();
          comment.setBoardId(rs.getLong("member_id"));
          comment.setMemberId(rs.getLong("board_id"));
          comment.setContent(rs.getString("comment_content"));
          comment.setWriter(rs.getString("member_loginId"));
          return comment;
        };
    }
}
