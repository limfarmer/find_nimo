package com.nimo.jdbc.dao;
//회원가입Dao

import com.nimo.jdbc.common.Common;
import com.nimo.jdbc.vo.AccountVo;

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

    public List<AccountVo> memberSelect() {
        List<AccountVo> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM EMP";
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                String ID = rs.getString("ID");
                String PW = rs.getString("PW");
                int PHONE = rs.getInt("PHONE");
                String EMAIL = rs.getString("EMAIL");
                String NICKNAME = rs.getString("NICKNAME");

                AccountVo vo = new AccountVo(ID,PW,PHONE,EMAIL,NICKNAME);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void memberInsert(AccountVo accountVo) {
        String sql = "INSERT INTO MEMBERS(ID, PW, PHONE, EMAIL, NICKNAME) VALUES(?,?,?,?,?)";
        

    }
}