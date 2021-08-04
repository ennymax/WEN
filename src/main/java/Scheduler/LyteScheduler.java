package main.java.Scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class LyteScheduler {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void startScheduleTask() {

        final ScheduledFuture<?> taskHandle = scheduler.scheduleAtFixedRate(
                new Runnable() {
                    public void run() {
                        try {
                           // Runner.StartLYTE();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                }, 0, 9, TimeUnit.HOURS);
    }

    public static void main(String[] args) {
        LyteScheduler scheduler = new LyteScheduler();
        scheduler.startScheduleTask();
    }
}