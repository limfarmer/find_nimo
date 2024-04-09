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
    public List<ReviewVo> getReviewBoardList() {
        String sql = "SELECT r.rno, r.members_id, r.title,r.content, r.image, m.NICKNAME  FROM REVIEW r, MEMBERS m WHERE m.ID = r.MEMBERS_ID";
        List<ReviewVo> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int rno = rs.getInt("rno");
                String membersId = rs.getString("members_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String image = rs.getString("image");
                String nickName = rs.getString("nickname");
                ReviewVo reviewVo = new ReviewVo();
                reviewVo.setRno(rno);
                reviewVo.setReviewID(membersId);
                reviewVo.setReviewTitle(title);
                reviewVo.setReviewContent(content);
                reviewVo.setReviewImage(image);
                reviewVo.setReviewNickName(nickName);
                list.add(reviewVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 후기 상세 정보 출력 메소드
    public ReviewVo getReviewDetail(int no) {
        String sql = "SELECT * FROM REVIEW WHERE rno = " + no;
        ReviewVo rv = new ReviewVo();
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            // rs.next는 무조건 선언해야되서 if문으로 감쌌음
            //rs.next(); rs.isFirst(); if문 없애고 이 두개의 함수 써도됨
            if (rs.next()) {
                int rno = rs.getInt("rno");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String image = rs.getString("image");
                if (image == null) {
                    image = "../image/nimo.jpg"; // 짱구 or 니모(이미지가 없다는) 이미지 경로 넣어야됨
                }
                String ID = rs.getString("members_id");
                int pno = rs.getInt("parcel_pno");
                rv.setRno(rno);
                rv.setReviewTitle(title);
                rv.setReviewContent(content);
                rv.setReviewImage(image);
                rv.setReviewID(ID);
                rv.setReviewPno(pno);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rv;
    }

    // 후기 등록 메소드(화면에서 버튼은 마이페이지에 있음)
    public void insertReview(ReviewVo rvo, String SessId) {
        String sql = "INSERT INTO REVIEW(rno, title, content, image, members_id, parcel_pno) VALUES(REVIEW_RNO.nextval, ?, ?, ?, ?, ?)";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rvo.getReviewTitle());
            pstmt.setString(2, rvo.getReviewContent());
            pstmt.setString(3, rvo.getReviewImage());
            pstmt.setString(4, SessId);
            pstmt.setInt(5, rvo.getReviewPno());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 마이페이지 -> 내가 작성한리뷰 리스트
    public List<ReviewVo> getMyReview(String id) {
        String sql = "SELECT * FROM REVIEW WHERE MEMBERS_ID = '" + id + "'";
        List<ReviewVo> list = new ArrayList<>();

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int rno = rs.getInt("rno");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String image = rs.getString("image");
                if (image == null) {
                    image = "../image/nimo.jpg";
                }
                String ID = rs.getString("members_id");
                int pno = rs.getInt("parcel_pno");
                ReviewVo rv = new ReviewVo();
                rv.setRno(rno);
                rv.setReviewTitle(title);
                rv.setReviewContent(content);
                rv.setReviewImage(image);
                rv.setReviewID(ID);
                rv.setReviewPno(pno);
                list.add(rv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
