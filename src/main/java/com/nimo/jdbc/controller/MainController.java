package com.nimo.jdbc.controller;

import com.nimo.jdbc.dao.ParcelDao;
import com.nimo.jdbc.dao.ReviewDao;
import com.nimo.jdbc.vo.ParcelVo;
import com.nimo.jdbc.vo.ReviewVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {
    @GetMapping("/mainpage")
    public String mainView(Model model){
        ParcelDao pdao = new ParcelDao();
        ReviewDao rdao = new ReviewDao();
        List<ParcelVo> plist= pdao.parcelSelect();
        List<ReviewVo> rlist = rdao.getReviewBoardList();
        model.addAttribute("plist",plist);
        model.addAttribute("rlist",rlist);
        return "thymeleaf/mainPage";
    }
}
