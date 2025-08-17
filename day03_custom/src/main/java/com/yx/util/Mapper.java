package com.yx.util;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Mapper {
    private String sqlStatement;
    private String resultType;
}