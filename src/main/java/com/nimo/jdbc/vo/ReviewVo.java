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
    private String reviewNickName;
}
