package com.example.Calc.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Calculator {
    @Id @GeneratedValue
    private Long id;
    private String calcString;
    private String calcResult;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private CalcStatus status;   // USE, DELETE 구분
}
