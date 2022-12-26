package board.board;

import board.board.domain.Member;
import board.board.domain.board.Board;
import board.board.service.BoardService;
import board.board.service.HomeService;
import board.board.web.form.BoardDto;
import board.board.web.form.BoardListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final HomeService service;
    @GetMapping("/")
    public String home(@SessionAttribute(value=LoginConst.LOGIN_SESSION, required = false)Member member, Model model){
        model.addAttribute("member", member);
        List<BoardListDto> boardListDto = service.findAll();
        model.addAttribute("boards", boardListDto);

        if (member == null) {
          return "/home/main";
        }

        return "/home/mainlogined";
    }
}
