package com.nimo.jdbc.vo;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPageVo {
    private int pno;
    private int rno;
    private String title;
    private String content;
    private String image;
    private char status;
    private String ID;
    private String nickname;
    private String PW;
    private int phone;
    private String email;
    private ParcelVo parcelVo;
    private ReviewVo reviewVo;

    // getter & setter 생성할 예정
}
