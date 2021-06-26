package com.ad.callermock;

import com.ad.handler.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ExpiryCheckScheduler scheduler class, calls expiryCleanup on fixed rate schedule
 */
@Component
public class ExpiryCheckScheduler {

    @Autowired
    private Cleanup expiryCleanup;

    public void checkAndSetExpiry(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                expiryCleanup.clean();
            }
        };
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 1000L, 30*1000L);
    }
}
