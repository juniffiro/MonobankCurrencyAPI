package ua.juniffiro.currency.api.utils;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 03/03/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class ThreadUtils {

    /**
     * Put the thread to sleep for N time.
     * @param millis
     *        Sleep time in milliseconds.
     *        1 second = 1000 ms
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
