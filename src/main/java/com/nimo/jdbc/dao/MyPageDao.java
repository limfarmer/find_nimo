package com.nimo.jdbc.dao;

import com.nimo.jdbc.common.Common;
import com.nimo.jdbc.vo.AccountVo;
import com.nimo.jdbc.vo.ParcelVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyPageDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public AccountVo showMypageMemInfo(String SessID) { // 오류 시 SessID 수정해야 됨
        String sql = "SELECT * FROM MEMBERS WHERE id = '" + SessID + "'"; // SessID 주변을 둘러싼 따옴표는 "와 ' 중 하나만?
        AccountVo mypageInfoResult = new AccountVo();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            rs.isFirst();
            String pw = rs.getString("PW");
            String phone = rs.getString("PHONE");
            String email = rs.getString("EMAIL");
            String nickName = rs.getString("NICKNAME");

            // SessID = new AccountVo(id, pw, phone, email, nickName);
            mypageInfoResult = new AccountVo(SessID, pw, phone, email, nickName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs); // 닫았습니다
        Common.close(stmt);
        Common.close(conn);

        return mypageInfoResult;
    }

    public void updateMypage(AccountVo accountVo, String ID) {
        String sql = "UPDATE MEMBERS SET PW  = ?, EMAIL = ?, NICKNAME = ?, PHONE = ? WHERE ID = ?"; // 참조키 변해도 되나? 확인하기
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountVo.getPW());
            pstmt.setString(2, accountVo.getEMAIL());
            pstmt.setString(3, accountVo.getNICKNAME());
            pstmt.setString(4, accountVo.getPHONE());
            pstmt.setString(5, ID);
            System.out.println("넘어오는 id : " + ID);
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("update : " + rowsUpdated);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("sql 디버깅 변경 후 닉네임: " + accountVo.getNICKNAME());

        Common.close(pstmt);
        Common.close(conn);
    }
    // 내가 분양 누른 게시판
    public List<ParcelVo> showMyParcelInfo(String SessID) {
        List<ParcelVo> plist = new ArrayList<>();
        String sql = "select p.title, p.content, p.pno, p.image, p.status, p.members_id, m.nickname from FAMILY f join PARCEL p on f.parcel_pno = p.pno join MEMBERS m on p.members_id = m.id where f.members_id= '"+SessID+"'";
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                int pno = rs.getInt("pno");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String image = rs.getString("image");
                if (image == null) {
                    image = "../image/white.jpg";
                }
                char status = 0;
                String members_id = rs.getString("members_id"); // members_id로 되어있었는데 sql문은 members테이블의 아이디를 불러오는거였어서 id로 바꿨습니다. by 임씨
                String nickname = rs.getString("nickname");
                ParcelVo myParcelInfoResult = new ParcelVo(pno, title, content, image, status, members_id, nickname);
                plist.add(myParcelInfoResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);

        return plist;
    }

    public void deleteMem(String idid) {
        String sql = "DELETE FROM MEMBERS WHERE ID = '" + idid + "'";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }
}