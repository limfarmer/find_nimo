package com.nimo.jdbc.controller;

import com.nimo.jdbc.dao.MyPageDao;
import com.nimo.jdbc.vo.AccountVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
    // 정보 수정 요청 화면
    MyPageDao myPageDao = new MyPageDao(); // 다른 메소드에서도 계속 써서 위로 빼놓음
    @GetMapping("/modify")
    public String getModifyInfo(Model model) { // @RequestParam("ID") 나중에 추가 필요
        // 선택한 고객의 정보를 DB에서 조회해온 후 화면에 출력할 수 있도록 Model에 담기
        String ID = "pkmm";
        model.addAttribute("mypageInfo", myPageDao.showMypageMemInfo(ID)); // attribute이름 수정 필요
        return "thymeleaf/mypageModify";
    }
    @PostMapping("/modify")
    public String mypageUpdate(@ModelAttribute("mypageInfo") AccountVo updateInfo) {
        System.out.println("nickname : " + updateInfo.getNICKNAME());
        myPageDao.updateMypage(updateInfo);
        return "thymeleaf/mypageMain";
    }
}

