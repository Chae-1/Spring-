package board.board;

import board.board.domain.Member;
import board.board.domain.board.Board;
import board.board.service.BoardService;
import board.board.service.HomeService;
import board.board.web.form.BoardDto;
import board.board.web.form.BoardListDto;
import board.board.web.form.BoardPaging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final HomeService service;
    @GetMapping("/")
    public String home(@SessionAttribute(value=LoginConst.LOGIN_SESSION, required = false)Member member,
                       Model model,
                       @RequestParam(defaultValue = "1")Integer page){
        Integer totalBoardSize = service.boardSize();
        BoardPaging paging = new BoardPaging(totalBoardSize, page);// 현재 페이지의 번호
        model.addAttribute("member", member);
        model.addAttribute("paging", paging);

        List<BoardListDto> boardListDto = service.findHomeBoardList(BoardPaging.BOARD_SIZE, paging.getCurrentPage());
        log.info("boardListDto = {}", boardListDto);
        model.addAttribute("boards", boardListDto);
        if (member == null) {
          return "/home/main";
        }

        return "/home/mainlogined";
    }
}
