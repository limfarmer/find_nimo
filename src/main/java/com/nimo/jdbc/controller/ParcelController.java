package com.nimo.jdbc.controller;

import com.nimo.jdbc.dao.ParcelDao;
import com.nimo.jdbc.vo.ParcelVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/parcel")
public class ParcelController {
    @GetMapping("/pboard")
    public String selectParcel(Model model, HttpSession session){
        ParcelDao parcelDao = new ParcelDao();
        List<ParcelVo> plist = parcelDao.parcelSelect();
        String id = (String) session.getAttribute("id");
        model.addAttribute("plist",plist);
        model.addAttribute("id",id);
        return "thymeleaf/pboard";
    }
}
