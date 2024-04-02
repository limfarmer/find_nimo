package com.nimo.jdbc.controller;

import com.nimo.jdbc.dao.ParcelDao;
import com.nimo.jdbc.vo.ParcelVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/parcel")
public class ParcelController {
    @GetMapping("/pboard")
    public String selectParcel(Model model){
        ParcelDao parcelDao = new ParcelDao();
        List<ParcelVo> plist = parcelDao.parcelSelect();
        model.addAttribute("plist",plist);
        return "thymeleaf/pboard";
    }
}
