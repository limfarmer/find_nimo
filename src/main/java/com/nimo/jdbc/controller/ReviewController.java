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
        Model rs = m.addAttribute("rno",rVo);
        // dao에 rs를 변수로 받는 쏴주는 메소드 만들기
        return "thymeleaf/reviewDetail";
    }


    //pno,id(sess)로 식별
    // 여기가 아니라 mypage -> 내가 분양 받은 게시판에서 후기버튼 클릭시 이 컨트롤러 실행되야됨
    // 나중에 분양받은 게시판 생기면 옮기기
    @GetMapping("/reviewTestView")
    public String showReviewInsert(Model m){
        m.addAttribute("reviewTest",new ReviewVo());
        return "thymeleaf/reviewInsert";
    }

    @GetMapping("/reviewTest")
    public String insertReview(@RequestParam("pno")int pno ,@ModelAttribute("reviewTest")ReviewVo rvo, HttpSession sess){
        ReviewDao reviewDao = new ReviewDao(); // 이것도 리뷰dao에 만드는게 맞는지 모르겠음
        String id = (String)sess.getAttribute("id");
        reviewDao.insertReview(rvo,id);
        rvo.setReviewID(id); // 확인 필요
        rvo.setReviewPno(pno);
        rvo.setRno(7);
        return "thymeleaf/reviewBoard";
    }
}
