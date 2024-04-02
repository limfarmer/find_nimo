package com.nimo.jdbc.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountVo {
    private String ID;
    private String PW;
    private int PHONE;
    private String EMAIL;
    private String NICKNAME;

}
