package board.board.web.controller;

import board.board.LoginConst;
import board.board.domain.Member;
import board.board.service.LoginService;
import board.board.web.form.LoginDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @GetMapping("/login")
    public String loginForm (@ModelAttribute LoginDto loginDto) {
        return "/login/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute @Validated LoginDto loginDto, BindingResult bindingResult, HttpServletRequest request) {
        Member member = loginService.doLogin(loginDto.getLoginId(), loginDto.getPassword());
        if (member == null) {
            bindingResult.reject("loginError", "아이디 혹은 패스워드를 확인해주세요.");
        }
        if (bindingResult.hasErrors()) {
            return "/login/login";
        }
        // 로그인
        HttpSession session = request.getSession();
        session.setAttribute(LoginConst.LOGIN_SESSION, member);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
