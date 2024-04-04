package com.nimo.jdbc.vo;

import lombok.*;
@Getter // 값 얻어오는 메서드
@Setter // 값 저장하는 메서드
@AllArgsConstructor // 모든 필드를 순서대로 인자(파라미터)로 받는 생성자 자동 생성. 필드 순서 바꾸면 적용 안 됨 -> @Builder로 바꾸거나 꼭 순서대로 할 것
@NoArgsConstructor // 파라미터가 없는 기본 생성자 자동 생성
public class FamilyVo {
    private int fno;
    private int pno;
    private String ID;
}