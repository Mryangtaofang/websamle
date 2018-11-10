package com.yang.application.event;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AbstractMethodMonitorEventListener implements MethodMonitorEventListener {


    @Override
    public void onMethodBegin(MethodMonitorEvent event) {
        event.setTimestamp(System.currentTimeMillis());
    }

    @Override
    public void onMethodEnd(MethodMonitorEvent event) {
        log.info(String.valueOf(System.currentTimeMillis() - event.getTimestamp()));
    }

}
