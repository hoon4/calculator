package com.example.Calc.Controller;

import com.example.Calc.DTO.LoginForm;
import com.example.Calc.DTO.MemberForm;
import com.example.Calc.Service.CalcService;
import com.example.Calc.Service.MemberService;
import com.example.Calc.SessionConstants;
import com.example.Calc.domain.Calculator;
import com.example.Calc.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CalcService calcService;
    private final MemberService memberService;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        // 세션이 없으면 홈으로 이동
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }

        // 세션에 저장된 회원 조회
        Member loginMember = (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);

        // 세션에 회원 데이터가 없으면 홈으로 이동
        if (loginMember == null) {
            return "home";
        }

        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);

        return "loginHome";
    }

    @GetMapping("calc")
    public String calc() {
        return "calcTest";
    }

    @GetMapping("/join")
    public String join(@ModelAttribute MemberForm memberForm) {
        return "joinForm";
    }

    @PostMapping("/join")
    public String login(@ModelAttribute @Validated MemberForm memberForm,
                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "joinForm";
        }

        Member joinMember = new Member();
        joinMember.setLoginId(memberForm.getLoginId());
        joinMember.setName(memberForm.getName());
        joinMember.setPassword(memberForm.getPassword());

        memberService.join(joinMember);

        return "home";
    }

    @GetMapping("history")
    public String list(Model model) {
        List<Calculator> calculatorList = calcService.showResult();
        model.addAttribute("result", calculatorList);
        return "history";
    }

    @GetMapping("/{calcId}/delete")
    public String getDelete(@PathVariable("calcId") Long calcId, Model model) {
        calcService.deleteCalc(calcId);
        List<Calculator> calculatorList = calcService.showResult();
        model.addAttribute("result", calculatorList);

        return "redirect:/history";
    }

}
