package board.board.web.controller;

import board.board.domain.Member;
import board.board.service.MemberService;
import board.board.web.form.MemberDto;
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
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService service;
    @GetMapping("/add")
    public String joinForm(@ModelAttribute MemberDto memberDto) {
        log.info("memberDto {}", memberDto);
        return "joinForm";
    }
    @PostMapping("/add")
    public String join(@ModelAttribute @Validated MemberDto memberDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String passwordValidation = memberDto.getPasswordValidation();
        if (!memberDto.getPassword().equals(passwordValidation)){
            bindingResult.reject("notValidated", "패스워드가 다릅니다.");
        }
        if (bindingResult.hasErrors()) {
            return "joinForm";
        }
        Member member = service.join(memberDto);
        redirectAttributes.addAttribute("id", member.getId()); // 식별자
        return "redirect:/members/{id}";
    }

    @GetMapping("/{id}")
    public String memberInfo(@PathVariable Long id, Model model) {
        log.info("id = {}", id);
        Member member = service.findById(id);
        log.info("member {}", member);
        model.addAttribute("member", member);
        return "joinIdentity";
    }
}
