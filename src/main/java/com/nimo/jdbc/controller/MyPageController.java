package com.nimo.jdbc.controller;

import com.nimo.jdbc.dao.LoginDao;
import com.nimo.jdbc.dao.MyPageDao;
import com.nimo.jdbc.dao.ReviewDao;
import com.nimo.jdbc.vo.AccountVo;
import com.nimo.jdbc.vo.ParcelVo;
import com.nimo.jdbc.vo.ReviewVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
    MyPageDao myPageDao = new MyPageDao(); // 다른 메소드에서도 계속 써서 위로 빼놓음
    // 마이페이지 메인으로 가는 매핑 메소드
    @GetMapping("/mypageMain")
    public String mypageMain(Model model ,HttpSession sess) {
        String id = (String) sess.getAttribute("id");
        model.addAttribute("id", id);
        return "thymeleaf/mypageMain";
    }
    // 정보 수정 버튼 클릭 시 회원 정보를 input 태그에 담아서 화면을 보여주는 Controller
    @GetMapping("/modify")
    public String getModifyInfo(Model model, HttpSession sess) {
        String getId = (String) sess.getAttribute("id"); //
        model.addAttribute("mypageInfo", myPageDao.showMypageMemInfo(getId));
        // return은 디렉토리 경로를 써주는곳이라 만약 이렇게 쓰려면 저희 thymeleaf 폴더 밑에 mypage라는 폴더를 하나더 만들어야돼용 by 임정후
        // return "thymeleaf/mypage/modify";
        return "thymeleaf/mypageModify";
    }
    // 회원 정보 수정 후 확인 버튼 누를 시 DB에 정보를 보내주는 Controller
    @PostMapping("/modify")
    public String mypageUpdate(@ModelAttribute("mypageInfo") AccountVo updateInfo, HttpSession sess) {
        String getId = (String) sess.getAttribute("id");
        myPageDao.updateMypage(updateInfo,getId);
        return "redirect:/mypage/mypageMain";
    }
    // 나의 분양 게시판을 눌렀을때 내가 쓴 분양 글들을 보여주는 컨트롤러
    @GetMapping("/myParcel")
    public String myParcel(HttpSession session, Model model) {
        String id = (String) session.getAttribute("id");
        List<ParcelVo> list = myPageDao.showMyParcelInfo(id);
        // ParcelVo vo = myParcel(id); 이 코드는 왜쓴지 몰라서 일단 주석처리했어용 글고 myParcel() 은 이 함수의 이름 이에용 by 임정후
        // attribute 이름이 list로 되어있었는데 myPageParcel.html엔 plist로 되어있었어요! by 임
        model.addAttribute("pList", list);
        // 저희 html이름인 mypageParcel로 변경 했습니다. by 임정후
        // return "thymeleaf/myParcel";
        return "thymeleaf/myPageParcel";
    }
    // mypageParcel.html에서 후기 등록 버튼을 누르면 실행되는 컨트롤러 by 임정후
    @GetMapping("/reviewTestView")
    public String showReviewInsert(Model m, @RequestParam("pno") int pno){
        System.out.println("pno : " + pno);
        ReviewVo vo = new ReviewVo();
        vo.setReviewPno(pno);
        m.addAttribute("reviewTest",vo);
        return "thymeleaf/mypageReviewInsert";
    }
    // mypageReviewInsert.html에서 후기 등록을 누르면 등록을 해주는 컨트롤러
    @PostMapping("/reviewTest")
    public String insertReview(@ModelAttribute("reviewTest") ReviewVo rvo, HttpSession sess){
        ReviewDao reviewDao = new ReviewDao();
        String id = (String)sess.getAttribute("id");
        reviewDao.insertReview(rvo,id);
        //rvo.setReviewPno(pno);
        return "redirect:/review/reviewBoard";
    }

    @GetMapping("/deleteConfirm")
    public String memberDelete(HttpSession sess,Model m){
        String id = (String) sess.getAttribute("id");
        AccountVo accountVo = new AccountVo();
        accountVo.setID(id);
        m.addAttribute("deleteMem", accountVo);
        return "thymeleaf/accountDelete";
    }
    // 우리가 쓸거
     @PostMapping("/deleteAccount")
    public String deleteAccount(@ModelAttribute("deleteMem") AccountVo accountVo,
                                HttpSession sess, Model model) {
        LoginDao loginDao = new LoginDao();
        String idid = accountVo.getID();
        String pwd = accountVo.getPW();
        boolean result = loginDao.LoginCheck(idid,pwd); // dao 빨간색
        if(result) {
            // 성공
            myPageDao.deleteMem(idid);
            return "redirect:/members/login";
        } else {
            return "redirect:/mypage/mypageMain";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("id");
        System.out.println("session : "+session.getAttribute("id"));
        return "redirect:/members/login";
    }
    // 4/7 마이페이지 나의 후기 게시판 기능추가
    @GetMapping("mypageReview")
    public String mypageReview(Model m, HttpSession sess){
        String id = (String) sess.getAttribute("id");
        ReviewDao reviewDao = new ReviewDao();
        List<ReviewVo> list = reviewDao.getMyReview(id);
        m.addAttribute("mypageReview",list);
        return "thymeleaf/mypageReviewList";
    }
/*
     // 로그아웃 방법 1
    @RequestMapping("/logout") // post?
    public String logout(HttpSession sess) {
        sess.invalidate(); // Session 무효화
        return "redirect:/main";
    }
    // 로그아웃 방법 2
    @GetMapping("/logoutConfirm") // get => post?
    public String logoutConfirm(HttpSession sess) {
        String nextPage = "redirect:/main";
        sess.invalidate(); // Session 무효화
        return nextPage;
    }

    // 로그아웃 방법 3
    @GetMapping("/logout")
    public String logout(HttpSession sess) {
        if(sess != null) {
            sess.invalidate();
        }
        return "thymeleaf/login";
    }
*/
    /*
매개변수로 (@RequestParam("id") int id와 Model model 사용
Model은 컨트롤러에서 처리한 결과를 화면에 전달하는 역할
@RequestParam("id") String id는 컨트롤러가 넘겨받은 값 중 id의 값을 받아서 String 변수 id에 값을 넘겨줌
     */
// 삭제 방법 1
//    @RequestMapping("/delete")
//    public String getDelete(HttpSession sess, AccountVo avo, RedirectAttributes rttr) { // RedirectAttributes 빨간색
//        AccountVo account = (AccountVo)sess.getAttribute("account");
//        String joinPW = account.getPW();
//        String checkPW = avo.getPW();
//        if(!(joinPW.equals(checkPW))) {
//            rttr.addFlashAttribute("msg", false); // addFlashAttribute 빨간색
//            return "redirect:/account/delete";
//        }
//        sess.invalidate();
//        return "redirect:/";
//    }
    /*
sess.getAttribute("account");를 이용해 로그인할 때 생성되는 세션에서 account값을 받아오기
sess.getAttribute("account");는 AccountVo의 한 형태
(AccountVo)sess.getAttribute("account");의 형태로 형변환 가능
AccountVo의 변수 account에 삽입 가능
account는 getter를 이용해 내부 접근
문자열 변수 join에 account에 있는 원래의 비밀번호 넣음
다른 문자열 변수 check에 회원 탈퇴 페이지에 입력했던 확인용 비밀번호 넣고 두 변수가 가진 값 비교
부정부호(!)가 있어서 결과 바뀌며 비밀번호가 같으면 false가 되어 넘어감
비밀번호가 다르다면 true가 되어 조건문 내부 코드 작동하고 html로 이동해서 msg와 그 값인 false를 넘겨주게 됨
*/
//    // 삭제 방법 2
//    @RequestMapping("/deleteAccount")
//    public String deleteAccount(String id, String pw, HttpSession sess) {
//        String result = myPageDao.deleteAccount(id, pw); // dao 빨간색
//        if(result > 0) {
//            sess.invalidate();
//            return "redirect:/logout";
//        } else {
//            return "redirect:/";
//        }
//        return result;
//    }
//    // 삭제 방법 3
//    @RequestMapping("/deleteAccount")
//    public String deleteAccount(@SessionAttribute AccountVo avo) {
//        String result1 = avo.deleteAccount(avo.getID()); // deleteAccout 빨간색
//        String result2 = avo.deleteAccount(avo.getPW()); // deleteAccout 빨간색
//        if(result > 0) { // result 빨간색
//            return "redirect:/logout";
//        } else {
//            return "redirect:/";
//        }
//    }
//    // 삭제 방법 4
//    @GetMapping("/account/delete")
//    public String delete(@PathVariable Long id, Long pw) {
//        AccountVo.delete(id); // delete 빨간색
//        return "redirect:/account/";
//    }
}
