package com.nimo.jdbc.dao;

import com.nimo.jdbc.common.Common;
import com.nimo.jdbc.vo.ParcelVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParcelDao {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public List<ParcelVo> parcelSelect() {
        List<ParcelVo> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM PARCEL";
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                int pno = rs.getInt("pno");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String image = rs.getString("image");
                if(image==null){
                    image = "이미지가 없습니다";
                }
                char status = rs.getString("status").charAt(0);
                String  id = rs.getString("members_id");

                ParcelVo pvo = new ParcelVo(pno,title,content,image,status,id);
                list.add(pvo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
