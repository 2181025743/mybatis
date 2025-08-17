package com.yx.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DbProfile {
    private String driver;
    private String url;
    private String username;
    private String password;

    private Map<String, Mapper> mappers = new HashMap<>();

    public void setMappers(Map<String, Mapper> newMappers) {
        if (this.mappers == null) {
            this.mappers = new HashMap<>();
        }
        this.mappers.putAll(newMappers);
    }
}
