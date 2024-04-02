package com.nimo.jdbc.dao;


// 후기 게시판 dao

import com.nimo.jdbc.common.Common;
import com.nimo.jdbc.vo.ReviewVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // 후기 게시판 목록 출력 메소드
    public List<ReviewVo> getReviewBoardList(){
        String sql = "SELECT rno, members_id, title FROM REVIEW";
        List<ReviewVo> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                int rno = rs.getInt("rno");
                String membersId = rs.getString("members_id");
                String title = rs.getString("title");
                list.add(new ReviewVo(rno,membersId,title));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // 후기 상세 정보 출력 메소드
    public ReviewVo getReviewDetail(int no){
        String sql = "SELECT * FROM REVIEW WHERE parcel_pno =" + no;
        ReviewVo rv = null;
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            int rno = rs.getInt("rno");
            String title = rs.getString("title");
            String content = rs.getString("content");
            String image = rs.getString("image");
            String ID = rs.getString("members_id");
            int pno =rs. getInt("parcel_pno");

            rv = new ReviewVo(rno, title,content,image,ID, pno);

        }catch (Exception e){
            e.printStackTrace();
        }
        return rv;
    }

}
