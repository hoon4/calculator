package com.example.Calc.Controller;

import com.example.Calc.Service.CalcService;
import com.example.Calc.Service.MemberService;
import com.example.Calc.SessionConstants;
import com.example.Calc.domain.CalcStatus;
import com.example.Calc.domain.Calculator;
import com.example.Calc.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CalcController {

    private final CalcService calcService;
    private final MemberService memberService;

    @PostMapping("postcalc")
    /* inputMap 파라미터를 받아, 미리 저장된 ageMap에서 해당 이름에 맵핑된 나이를 리턴해주는 메소드 */
    public Map<String,Object> postCalculate(@RequestBody Map<String,Object> inputMap
                                        , HttpServletRequest request) {

        System.out.println("계산식 : " + inputMap.get("calcString"));
        System.out.println("결과 : " + inputMap.get("calcResult"));

        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("calcString", inputMap.get("calcString"));
        returnMap.put("calcResult", inputMap.get("calcResult"));

        Calculator calculator = new Calculator();
        calculator.setCalcString((String)inputMap.get("calcString"));
        calculator.setCalcResult(inputMap.get("calcResult").toString());
        calculator.setStatus(CalcStatus.USE);

        // 로그인된 회원정보 불러오기
        HttpSession session = request.getSession();
        String findId = (String)session.getAttribute("loginId");

        Optional<Member> member = memberService.find(findId);
        Member findMember = member.get();
        calculator.setMember(findMember);

        calcService.saveData(calculator);

        return returnMap;
    }


}
