package board.board.web.controller;

import board.board.LoginConst;
import board.board.domain.board.Board;
import board.board.domain.Member;
import board.board.domain.board.Comment;
import board.board.service.BoardService;
import board.board.web.form.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping
    public String boardForm(@ModelAttribute BoardDto boardDto) {
        return "/board/boardAdd";
    }

    @PostMapping
    public String addBoard(@ModelAttribute @Validated BoardDto boardDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @SessionAttribute(value = LoginConst.LOGIN_SESSION) Member member) {
        if (bindingResult.hasErrors()) {
            log.info("오류 있음");
            return "/board/boardAdd";
        }

        Board board = boardService.save(boardDto, member.getId());
        redirectAttributes.addAttribute("boardId", board.getId());
        return "redirect:/board/{boardId}";
    }

    @GetMapping("/{boardId}") // board의 내용 뿐 아니라 해당 게시판 id에 해당하는 comment도 불러들여 와야한다.
    public String board(@PathVariable Long boardId, Model model, @ModelAttribute Comment comment) {
        Board board = boardService.findById(boardId);
        board.setCommentList(boardService.allCommentList(boardId));
        model.addAttribute("board", board);
        return "/board/board";
    }

    @PostMapping("/{boardId}/comment")
    public String comment(@SessionAttribute(value=LoginConst.LOGIN_SESSION)Member member,
                          @ModelAttribute Comment comment,
                          RedirectAttributes redirectAttributes,
                          @PathVariable Long boardId) {
        boardService.addComment(member.getId(), boardId, comment.getContent());
        redirectAttributes.addAttribute("boardId", boardId);
        return "redirect:/board/{boardId}";
    }

}
