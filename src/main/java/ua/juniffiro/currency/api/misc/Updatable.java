package ua.juniffiro.currency.api.misc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 24/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class Updatable {

    public static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(2);

    public Updatable() {}

    /**
     * Run the task cyclically with a certain delay.
     * @param task
     *        Task to be performed.
     * @param secondsUpdateDelay
     *        Task period.
     */
    public static void runTask(Runnable task, long secondsUpdateDelay) {
        SCHEDULER.scheduleAtFixedRate(task, secondsUpdateDelay,
                secondsUpdateDelay, TimeUnit.SECONDS);
    }

    /**
     * Finish all tasks in N seconds.
     * @param time
     *        Time in seconds in long(L) format.
     */
    public static void finish(long time) {
        System.out.println("All tasks will be completed in " + time);
        try {
            if (!SCHEDULER.awaitTermination(time, TimeUnit.SECONDS)) {
                SCHEDULER.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
