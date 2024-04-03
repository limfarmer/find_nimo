package com.nimo.jdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
    // 정보 수정 요청 화면
    @RequestMapping("/modify")
    public String modify(int ID, Model model) {
        // 선택한 고객의 정보를 DB에서 조회해온 후 화면에 출력할 수 있도록 Model에 담기
        model.addAttribute("vo", dao.accountModify(ID));
        return "thymeleaf/myPagemodify";
    }

    // 고객 정보 수정 저장
    @RequestMapping("/update")
    public String update(myPageVo vo) {
        // 화면에서 수정 입력한 정보를 DB에 저장한 후
        MyPageDao.accountUpdate(vo);
        // 화면으로 연결
        return "thymeleaf/myPageUpdate";
    }

    // 고객 정보 삭제 처리 요청
    @RequestMapping("/delete")
    public String delete(int ID) {
        // 선택한 고객 정보를 DB에서 삭제한 후
        MyPageDao.accountDelete(ID);
        // 목록 화면으로 연결
        return "thymeleaf/myPageDelete";
    }
    @RequestMapping("/log")
    public String myBoard(String select, String ID, Model model) throws Exception {
        model.addAttribute("ID", ID);
        return "thymeleaf.myPageLog";
        }
    }
}
// 코드 세부 내용은 수정해야 하는데 일단 올림