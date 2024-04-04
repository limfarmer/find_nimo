package com.nimo.jdbc.controller;

import com.nimo.jdbc.dao.MyPageDao;
import com.nimo.jdbc.vo.AccountVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
    MyPageDao myPageDao = new MyPageDao(); // 다른 메소드에서도 계속 써서 위로 빼놓음
    // 정보 수정 버튼 클릭시 회원 정보를 input 태그에 담아서 화면을 보여주는 Controller
    @GetMapping("/modify")
    public String getModifyInfo(Model model, HttpSession sess) {
        String getId =  (String) sess.getAttribute("id");
        model.addAttribute("mypageInfo", myPageDao.showMypageMemInfo(getId));
        return "thymeleaf/mypageModify";
    }
    // 회원 정보 수정후 확인버튼 누를시 DB에 정보를 보내주는 Controller
    @PostMapping("/modify")
    public String mypageUpdate(@ModelAttribute("mypageInfo") AccountVo updateInfo, HttpSession sess) {
        String getId =  (String) sess.getAttribute("id");
        myPageDao.updateMypage(updateInfo,getId);
        return "thymeleaf/mypageMain";
    }
}

