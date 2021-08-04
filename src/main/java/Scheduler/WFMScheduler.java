package Scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class WFMScheduler {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void startScheduleTask() {

        final ScheduledFuture<?> taskHandle = scheduler.scheduleAtFixedRate(
                new Runnable() {
                    public void run() {
                        try {

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                }, 0, 7, TimeUnit.HOURS);
    }

    public static void main(String[] args) {
        WFMScheduler scheduler = new WFMScheduler();
        scheduler.startScheduleTask();
    }
}