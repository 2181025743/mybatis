package com.yx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Employee {
    private Integer id;
    private String name;
    private String addr;
    private int age;
    private String job;
    private BigDecimal sal;
}
