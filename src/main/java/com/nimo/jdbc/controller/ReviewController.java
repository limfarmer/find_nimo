package com.nimo.jdbc.controller;

import com.nimo.jdbc.dao.ReviewDao;
import com.nimo.jdbc.vo.ReviewVo;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

// 후기 게시판 컨트롤러
@Controller
@RequestMapping("/review")
public class ReviewController {
    ReviewDao rDao = new ReviewDao();
    // 후기 게시판 리스트 보여주는 컨트롤러
    @GetMapping("/reviewBoard")
    public String showReivewBoard(Model m){
        List<ReviewVo> reviewList = rDao.getReviewBoardList();
        m.addAttribute("reviewBoard",reviewList);
        return "thymeleaf/reviewBoard";
    }
    // 후기 상세 정보 보여주는 컨트롤러
    @GetMapping("/reviewDetail")
    public String showReviewDetail(@RequestParam("rno") int rno, Model m){
        ReviewVo rVo = rDao.getReviewDetail(rno);
        m.addAttribute("reviewDetail",rVo);
        // dao에 rs를 변수로 받는 쏴주는 메소드 만들기
        return "thymeleaf/reviewDetail";
    }
}
