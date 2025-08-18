package com.yx.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Player {
    private int pid;
    private String pname;
    private int tid;
}