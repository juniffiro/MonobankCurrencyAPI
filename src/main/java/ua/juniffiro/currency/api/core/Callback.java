package ua.juniffiro.currency.api.core;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 24/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public interface Callback<T> {

    /**
     * Handle the data from the service here.
     *
     * @param data
     *        Certain data for handling
     */
    void handle(T data);
}
