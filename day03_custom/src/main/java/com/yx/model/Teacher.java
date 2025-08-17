package com.yx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor

public class Teacher {
    private int id;
    private String name;
    private String addr;
    private int age;
    private String job;
    private int sal;
}
