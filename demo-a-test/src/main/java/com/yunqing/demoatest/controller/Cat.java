package com.yunqing.demoatest.controller;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
public class Cat {
    private Integer id;
    private String name;

    public Cat(String name) {
        this.name = name;
    }
}
