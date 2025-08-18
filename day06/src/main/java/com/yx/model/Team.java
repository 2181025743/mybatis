package com.yx.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Team {
    private int tid;
    private String tname;
    private List<Player> players;
}