package com.nimo.jdbc.dao;

import com.nimo.jdbc.common.Common;
import com.nimo.jdbc.vo.AccountVo;

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

    public AccountVo showMypageMemInfo(String SessID) { // 오류시 SessID 수정해야됨
        String sql = "SELECT * FROM MEMBERS WHERE id = '" + SessID + "'";
        AccountVo mypageInfoResult = new AccountVo();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            rs.isFirst();
            String id = rs.getString("ID");
            String pw = rs.getString("PW");
            String phone = rs.getString("PHONE");
            String email = rs.getString("EMAIL");
            String nickName = rs.getString("NICKNAME");

            //SessID = new AccountVo(id,pw,phone,email,nickName);
            mypageInfoResult = new AccountVo(id, pw, phone, email, nickName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mypageInfoResult;
    }

    public void updateMypage(AccountVo accountVo) {
        String sql = "UPDATE MEMBERS SET PW  = ?, EMAIL = ?, NICKNAME = ?, PHONE = ? WHERE ID = ?"; // 참조키 변해도 되나?확인하기
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountVo.getPW());
            pstmt.setString(2, accountVo.getEMAIL());
            pstmt.setString(3, accountVo.getNICKNAME());
            pstmt.setString(4, accountVo.getPHONE());
            pstmt.setString(5, accountVo.getID());
            System.out.println("넘어오는 id : " + accountVo.getID());
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("update : " + rowsUpdated);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("sql 디버깅 변경후 닉네임: " + accountVo.getNICKNAME());

        Common.close(pstmt);
        Common.close(conn);
    }

}
