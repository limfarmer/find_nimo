package com.nimo.jdbc.controller;
//회원가입컨트롤러

import com.nimo.jdbc.dao.LoginDao;
import com.nimo.jdbc.vo.AccountVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/members")
public class LoginController {
    // 승용님이 한 로그인 메소드
    @GetMapping("/membersselect")
    public String selectMembers(Model model) {
        LoginDao loginDao = new LoginDao();
        String id = "pkmm";
        AccountVo accountVoList = loginDao.memberSelect(id);
        model.addAttribute("memberslist", accountVoList);
        return "thymeleaf/membersBoard";
    }
    @GetMapping("/login")
    public String loginIdPw(Model model){
        LoginDao loginDao = new LoginDao();
        AccountVo lvo = new AccountVo();
        model.addAttribute("IdPw",lvo);
        return "thymeleaf/login";
    }
    @PostMapping("/loginOk")
    public String loginOkCheck(Model model, @RequestParam("id") String id, @RequestParam("pwd") String pwd, HttpSession session){
        LoginDao loginDao = new LoginDao();
        Boolean check = loginDao.LoginCheck(id, pwd);
        if (check){
            session.setAttribute("id", id);
//            String id = (String) session.getAttribute("id"); //다른 controller에서 해당 유저의 id가 필요할 땐 이렇게 쓰세요
            return "redirect:/main/mainpage"; //main 아직 없음 만들고 다시/ 그리고 여기서 session해주기
        }else {
            return "redirect:/members/login"; // 로그인 실패 했을때
        }

    }
}
