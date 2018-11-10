package com.yang.application.event;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MethodMonitorEventPublisher {

    private List<MethodMonitorEventListener> listeners = new ArrayList<>();


    public void methodMonitor() throws InterruptedException {
        MethodMonitorEvent event = new MethodMonitorEvent(this);
        publishEvent("begin",event);

        TimeUnit.SECONDS.sleep(5);

        publishEvent("end",event);
    }

    private void publishEvent(@NotBlank final String status, final MethodMonitorEvent event){
        //避免事件发布后监听器被移除了
        new ArrayList<>(listeners).forEach(listener -> {
            if("begin".equals(status))
                listener.onMethodBegin(event);
            else
                listener.onMethodEnd(event);
        });
    }

    public void addListener(MethodMonitorEventListener listener){
        listeners.add(listener);
    }

    public void removeListener(MethodMonitorEventListener listener){
        listeners.remove(listener);
    }


    public static void  main(String[] args) throws InterruptedException {
        MethodMonitorEventPublisher publisher = new MethodMonitorEventPublisher();

        publisher.addListener(new AbstractMethodMonitorEventListener());

        publisher.methodMonitor();
    }
}
