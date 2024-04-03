package com.nimo.jdbc.dao;

import com.nimo.jdbc.common.Common;
import com.nimo.jdbc.vo.AccountVo;
import com.nimo.jdbc.vo.MyPageVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class MyPageDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public AccountVo showMypageMemInfo(String SessID){ // 오류시 SessID 수정해야됨
        String sql ="SELECT * FROM MEMBER WHERE =" + SessID;
        AccountVo mypageInfoResult = new AccountVo();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            String id = rs.getString("ID");
            String pw = rs.getString("PW");
            int phone = rs.getInt("PHONE");
            String email = rs.getString("EMAIL");
            String nickName = rs.getString("NICKNAME");

            //SessID = new AccountVo(id,pw,phone,email,nickName);
            mypageInfoResult = new AccountVo(id,pw,phone,email,nickName);

        }catch (Exception e){
            e.printStackTrace();
        }
        return mypageInfoResult;
    }

//    public void accountUpdate(MyPageVo myPageVo) {
////        System.out.print("회원 정보 수정을 위해 ID를 입력해 주세요 : ");
////        String ID = sc.next();
////        System.out.print("새로운 비밀번호 : ");
////        String PW = sc.next();
////        System.out.print("새로운 전화번호 : ");
////        int phone = sc.nextInt();
////        System.out.print("새로운 이메일주소 : ");
////        String email = sc.next();
////        System.out.print("새로운 닉네임 : ");
////        String nickname = sc.next();
//
//        String sql = "UPDATE ACCOUNT SET PW = ?, PHONE = ?, EMAIL = ?, NICKNAME = ? WHERE ID = ?";
//
//        try {
//            conn = Common.getConnection();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, PW);
//            pstmt.setInt(2, phone);
//            pstmt.setString(3, email);
//            pstmt.setString(4, nickname);
//            pstmt.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Common.close(stmt);
//        Common.close(conn);
//    }

    public void accountDelete() {
        System.out.print("탈퇴하실 회원님의 아이디를 입력해 주세요 : ");
        String ID = sc.next();
        String sql = "DELETE FROM ACCOUNT WHERE ID = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql); // SQL 쿼리 실행할 때 사용
            pstmt.setString(1, ID);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }

    public void writeLog() {

    }
}
