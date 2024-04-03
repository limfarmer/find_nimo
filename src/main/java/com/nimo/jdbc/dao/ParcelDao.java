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
            String query = "select m.nickname, p.pno, p.title, p.content, p.image, m.id  from PARCEL p join MEMBERS m on p.members_id = m.id where p.status=0";
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                int pno = rs.getInt("pno");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String image = rs.getString("image");
                if(image==null){
                    image = "이미지가 없습니다";
                }
                char status = 0;
                String  id = rs.getString("id");
                String nickname = rs.getString("nickname");

                ParcelVo pvo = new ParcelVo(pno,title,content,image,status,id,nickname);
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
    public void parcelInsert(ParcelVo pvo) {

        String sql = "insert into PARCEL (pno, title, content, image, status, members_id) values(PARCEL_PNO.nextval,?,?,?,?,?)";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pvo.getTitle());
            pstmt.setString(2, pvo.getContent());
            pstmt.setString(3, pvo.getImage());
            pstmt.setString(4, String.valueOf(0));
            pstmt.setString(5, pvo.getMembers_id());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }
}
