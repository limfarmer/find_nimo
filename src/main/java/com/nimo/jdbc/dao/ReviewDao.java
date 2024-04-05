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
        String sql = "SELECT * FROM REVIEW WHERE rno = " + no;
        ReviewVo rv = null;
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            // rs.next는 무조건 선언해야되서 if문으로 감쌌음
            //rs.next(); rs.isFirst(); if문 없애고 이 두개의 함수 써도됨
            if(rs.next()){
                int rno = rs.getInt("rno");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String image = rs.getString("image");
                String ID = rs.getString("members_id");
                int pno =rs. getInt("parcel_pno");
                rv = new ReviewVo(rno, title,content,image,ID, pno);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return rv;
    }

    public void insertReview(ReviewVo rvo,String SessId) {
        String sql = "INSERT INTO REVIEW(rno, title, content, image, members_id, parcel_pno) " +
                "VALUES(REVIEW_RNO.nextval, ?, ?, ?, ?, ?)";
        try{
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,rvo.getReviewTitle());
            pstmt.setString(2,rvo.getReviewContent());
            pstmt.setString(3,rvo.getReviewImage());
            pstmt.setString(4,SessId);
            pstmt.setInt(5,rvo.getReviewPno());
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
