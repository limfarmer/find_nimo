package com.nimo.jdbc.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 후기 게시판 vo
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewVo {
    private int rno;
    private String reviewTitle;
    private String reviewContent;
    private String reviewImage;
    private String reviewID;
    private int reviewPno;

    // 후기 게시판에 처음 들어왔을때 보이는 아이디와 제목만을 쏴주기 위한 생성자
    public ReviewVo(int rno, String reviewTitle, String reviewID){
        this.rno = rno;
        this.reviewTitle = reviewTitle;
        this.reviewID = reviewID;
    }
}
