package com.nimo.jdbc.controller;

import com.nimo.jdbc.dao.MyPageDao;
import com.nimo.jdbc.vo.AccountVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
    // 정보 수정 요청 화면
    @GetMapping("/modify") // 테스트 해봤을때 바로 정보수정 창이 안뜨고 수정 완료되셨습니다 화면이 나올경우 GET/ POST 방식으로 수정 필요
    public String getModify(Model model) { // @RequestParam("ID") 나중에 추가 필요
        // 선택한 고객의 정보를 DB에서 조회해온 후 화면에 출력할 수 있도록 Model에 담기
        MyPageDao myPageDao = new MyPageDao();
        String ID = "pkmm";
        model.addAttribute("mypageInfo", myPageDao.showMypageMemInfo(ID)); // attribute이름 수정 필요
        return "thymeleaf/mypageMain";
    }

    // 고객 정보 수정 저장
//    @RequestMapping("/modify")
//    public String update(myPageVo vo) {
//        // 화면에서 수정 입력한 정보를 DB에 저장한 후
//        MyPageDao.accountUpdate(vo);
//        // 화면으로 연결
//        return "thymeleaf/myPageUpdate";
//    }
//
//    // 고객 정보 삭제 처리 요청
//    @RequestMapping("/delete")
//    public String delete(int ID) {
//        // 선택한 고객 정보를 DB에서 삭제한 후
//        MyPageDao.accountDelete(ID);
//        // 목록 화면으로 연결
//        return "thymeleaf/myPageDelete";
//    }

    // Log 공부하신거
//    @RequestMapping("/log")
//    public String myBoard(String select, String ID, Model model) throws Exception {
//        model.addAttribute("ID", ID);
//        return "thymeleaf.myPageLog";
//    }
}
// 코드 세부 내용은 수정해야 하는데 일단 올림