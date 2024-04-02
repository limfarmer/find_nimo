package com.nimo.jdbc.controller;

import com.nimo.jdbc.dao.ReviewDao;
import com.nimo.jdbc.vo.ReviewVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "thymeleaf/reviewDetail";
    }
}
