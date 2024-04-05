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
    @GetMapping("/reviewBoard")
    public String showReivewBoard(Model m){
        List<ReviewVo> reviewList = rDao.getReviewBoardList();
        m.addAttribute("reviewBoard",reviewList);
        return "thymeleaf/reviewBoard";
    }

    @GetMapping("/reviewDetail")
    public String showReviewDetail(@RequestParam("rno") int boardNo, Model m){
        System.out.println(boardNo);
        ReviewVo rVo = rDao.getReviewDetail(boardNo);
        m.addAttribute("rno",rVo);
        // dao에 rs를 변수로 받는 쏴주는 메소드 만들기
        return "thymeleaf/reviewDetail";
    }




}
