package scheduler;

import console.Console;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    public static Integer current = 0;
    public static void startScheduler() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(0);
        Runnable run = () -> {
            if(!Console.ended) {
                if(current >= 60) {
                    current = 0;
                }
                current += 5;
                Handler.handleChecks(current);
                Handler.enabled = true;
                if(Handler.shouldDisable) {
                    executor.shutdown();
                }
            }
        };
        executor.scheduleAtFixedRate(run, 0, 5, TimeUnit.SECONDS);
    }
}
