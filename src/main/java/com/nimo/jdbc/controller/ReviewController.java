package com.nimo.jdbc.controller;

import com.nimo.jdbc.dao.ReviewDao;
import com.nimo.jdbc.vo.ReviewVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// 후기 게시판 컨트롤러
@Controller
@RequestMapping("/review")
public class ReviewController {
    @GetMapping("/reviewBoard")
    public String showReivewBoard(Model m){
        ReviewDao rDao = new ReviewDao();
        List<ReviewVo> reviewList = rDao.getReviewBoardList();
        m.addAttribute("reviewBoard",reviewList);
        return "thymeleaf/reviewBoard";
    }
}
