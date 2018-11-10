package com.yang.application.event;

import lombok.Getter;
import lombok.Setter;

import java.util.EventObject;


public class MethodMonitorEvent extends EventObject {

    //时间戳，用于记录方法开始执行的时间
    @Setter @Getter
    private long timestamp;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MethodMonitorEvent(Object source) {
        super(source);
    }
}
