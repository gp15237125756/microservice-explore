package com.rbsn.schedule.gateway.doo;

import java.io.Serializable;

/**
 * Created by Jiang on 2018/8/27.
 */
public class TestDO implements Serializable{

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
