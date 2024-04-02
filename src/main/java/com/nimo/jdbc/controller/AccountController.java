package com.nimo.jdbc.controller;
//회원가입컨트롤러

import com.nimo.jdbc.dao.AccountDao;
import com.nimo.jdbc.vo.AccountVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/members")
public class AccountController {
    @GetMapping("/membersBoard")
    public String selectMembers(Model model) {
        AccountDao accountDao = new AccountDao();
        List<AccountVo> accountVoList = accountDao.memberSelect();
        model.addAttribute("memberslist",accountVoList);
        return "thymeleaf/membersBoard";

    }
}
