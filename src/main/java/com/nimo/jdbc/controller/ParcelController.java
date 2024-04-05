package com.nimo.jdbc.controller;

import com.nimo.jdbc.dao.ParcelDao;
import com.nimo.jdbc.vo.AccountVo;
import com.nimo.jdbc.vo.FamilyVo;
import com.nimo.jdbc.vo.ParcelVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/parcel")
public class ParcelController {
    @GetMapping("/pboard") //list
    public String selectParcel(Model model, HttpSession session) {
        ParcelDao parcelDao = new ParcelDao();
        List<ParcelVo> plist = parcelDao.parcelSelect();
        String id = (String) session.getAttribute("id");
        model.addAttribute("plist", plist);
        model.addAttribute("id", id);
        return "thymeleaf/pboard";
    }

    @GetMapping("/parcel") //detailpage
    public String parcelMain(Model model, @RequestParam("pno") int pnum) {

        ParcelDao parcelDao = new ParcelDao();
        ParcelVo parcelMain = parcelDao.parcelMain(pnum);
        model.addAttribute("parcelmain", parcelMain);
        return "thymeleaf/pmain";
    }

    //    @GetMapping("/")
    @GetMapping("/parcelInsert")
    public String parcelInsert(Model model) {
        model.addAttribute("pinsert",new ParcelVo ());
        return "thymeleaf/pinsert";
    }
    @PostMapping("/parcelinsertt")
    public String parcelInsertt(@ModelAttribute("pinsert") ParcelVo pinvo, HttpSession session){
        ParcelDao parcelDao = new ParcelDao();
        String id = (String) session.getAttribute("id");
        pinvo.setMembers_id(id);
        parcelDao.parcelInsert(pinvo);
        return "redirect:/parcel/pboard";
    }
    @PostMapping("/parcelUpdate")
    public String parcelUpdate(@RequestParam("pno") int pnum, HttpSession session) {
        ParcelDao parcelDao =new ParcelDao();
        FamilyVo fvo = new FamilyVo();
        fvo.setPno(pnum);
        String id = (String) session.getAttribute("id");
        fvo.setID(id);
        parcelDao.parcelFamilyUpdate(fvo);
        parcelDao.parcelStatusUpdate(pnum);
        return "redirect:/parcel/pboard";
    }
}
