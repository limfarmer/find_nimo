package com.nimo.jdbc.dao;
//회원가입Dao

import com.nimo.jdbc.common.Common;
import com.nimo.jdbc.vo.AccountVo;
import org.yaml.snakeyaml.events.Event;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public AccountVo memberSelect(String oldId) {
        AccountVo vo = new AccountVo();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM MEMBERS where ID= '"+oldId+"'";
            rs = stmt.executeQuery(query);

            String ID = rs.getString("ID");
            String PW = rs.getString("PW");
            int PHONE = rs.getInt("PHONE");
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
    }

    public void memberInsert(AccountVo accountVo) {
        String sql = "INSERT INTO MEMBERS(ID, PW, PHONE, EMAIL, NICKNAME) VALUES(?,?,?,?,?)";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountVo.getID());
            pstmt.setString(2, accountVo.getPW());
            pstmt.setInt(3, accountVo.getPHONE());
            pstmt.setString(4, accountVo.getEMAIL());
            pstmt.setString(5, accountVo.getNICKNAME());
            pstmt.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }

    }
