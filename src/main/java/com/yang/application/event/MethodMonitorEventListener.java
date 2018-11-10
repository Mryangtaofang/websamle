package com.yang.application.event;

import java.util.EventListener;

public interface MethodMonitorEventListener extends EventListener {

    public void onMethodBegin(MethodMonitorEvent event);

    public void onMethodEnd(MethodMonitorEvent event);
}
