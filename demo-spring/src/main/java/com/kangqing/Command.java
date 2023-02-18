package com.kangqing;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author kangqing
 * @since 2023/2/7 07:37
 */
@Component("myCommand")
public class Command {

    private Map map;
    
    public void setState(Map commandState) {
        this.map = commandState;
    }

    public Object execute() {
        System.out.println("-----------");
        return map;
    }
}
