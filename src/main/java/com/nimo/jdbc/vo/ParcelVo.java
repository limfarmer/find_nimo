package com.nimo.jdbc.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParcelVo {
    private int pno;
    private String title;
    private String content;
    private String image;
    private char  status; //(0:분양X / 1:분양O)
    private String members_id;
    private String nickname;

}
