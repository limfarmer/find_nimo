package com.nimo.jdbc.dao;
//회원가입Dao

import com.nimo.jdbc.common.Common;
import com.nimo.jdbc.vo.AccountVo;

import java.sql.*;
import java.util.Scanner;

public class LoginDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    /*public AccountVo memberSelect(String oldId) {
        AccountVo vo = new AccountVo();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM MEMBERS where ID= '"+oldId+"'";
            rs = stmt.executeQuery(query);

            String ID = rs.getString("ID");
            String PW = rs.getString("PW");
            String PHONE = rs.getString("PHONE");
            String EMAIL = rs.getString("EMAIL");
            String NICKNAME = rs.getString("NICKNAME");

            vo = new AccountVo(ID, PW, PHONE, EMAIL, NICKNAME);

            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }*/

    public void memberInsert(AccountVo accountVo) {
        String sql = "insert into MEMBERS (ID, PW, EMAIL, NICKNAME, PHONE) VALUES (?,?,?,?,?)";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountVo.getID());
            pstmt.setString(2, accountVo.getPW());
            pstmt.setString(3, accountVo.getEMAIL());
            pstmt.setString(4, accountVo.getNICKNAME());
            pstmt.setString(5, accountVo.getPHONE());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    public boolean LoginCheck(String id, String pwd){
        boolean loginOk = false;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM MEMBERS where ID= '"+id+"' and PW='"+pwd+"'";
            rs = stmt.executeQuery(query);

            if(rs.next()) {
                loginOk = true;
            }else {
                loginOk = false;
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginOk;
    }
}
