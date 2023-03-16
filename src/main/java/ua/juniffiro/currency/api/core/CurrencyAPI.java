package ua.juniffiro.currency.api.core;

import ua.juniffiro.currency.api.misc.CurrencyCacheLoader;
import ua.juniffiro.currency.api.misc.Updatable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 24/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public abstract class CurrencyAPI {

    private final ApiRequest apiRequest;
    private final List<CurrencyData> currencyList; // Exchange rate cache
    private String data;
    private final boolean needUpdate;
    private final long updateDelay; // Default update period
    private boolean runned;
    private boolean initialized;

    public CurrencyAPI(ApiRequest apiRequest) {
        this(apiRequest, false, 300L);
    }

    public CurrencyAPI(ApiRequest apiRequest, long updateDelay) {
        this(apiRequest, false, updateDelay);
    }

    public CurrencyAPI(ApiRequest apiRequest, boolean needUpdate, long updateDelay) {
        System.out.println("Trying to initialize a new API.");

        this.apiRequest = apiRequest;
        this.currencyList = new ArrayList<>();

        if (updateDelay < 30) throw new RuntimeException(
                "You cannot set the refresh rate to less than 30 seconds.");
        this.updateDelay = updateDelay;

        this.needUpdate = needUpdate;

        firstSetup();
    }

    /**
     * Runs the data update program.
     * Called only in descendant classes.
     */
    protected void runUpdate() {
        if (runned) {
            System.out.println("The update is already running.");
            return;
        }
        Updatable.runTask(this::onUpdate, updateDelay);
        runned = true;
    }

    /**
     * Data update period.
     */
    public long getUpdateDelay() {
        return updateDelay;
    }

    /**
     * Does the data need to be updated.
     */
    public boolean isNeedUpdate() {
        return needUpdate;
    }

    /**
     * Request new data in the API service.
     * Updates Data.
     */
    protected synchronized void requestUpdate() {
            try {
                apiRequest.send(d -> data = d);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    /**
     * Called during API initialization at startup.
     * It is used to fill the base values
     * in the currency cache.
     * <p>
     * {@code @synchronized}
     */
    private void firstSetup() {
        synchronized (this) {
            try {
                apiRequest.send(d -> {
                    data = d;
                    afterInit();
                    initialized = true;
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Check if the API is initialized.
     */
    public boolean isInitialized() {
        return initialized;
    }

    /**
     * Called if initialization is successful.
     */
    public abstract void afterInit();

    /**
     * Called every N seconds, if the data update
     * function from the API service is enabled.
     * By default, it is updated every 30 seconds.
     */
    public void onUpdate() {}

    /**
     * Called when the API is shutdown.
     */
    protected void onFinish() {}

    /**
     * Data from API service.
     * <p>
     * Stored as a simple string, from
     * which you can get JSON or other data format.
     *
     * @return Data from API service.
     */
    public String getData() {
        return data;
    }

    /**
     * Get currency data.
     * Format: Course A to B
     * Does not throw an NPE, but may return Optional.empty();
     *
     * @param currencyCodeA
     *        (A) First currency. For example USD.
     * @param currencyCodeB
     *        (B) Second currency. For example EUR.
     *
     * @return Optional(Currency).
     */
    public Optional<CurrencyData> getCurrency(int currencyCodeA, int currencyCodeB) {
        return currencyList.stream()
                .filter(cur -> cur.getCurrencyCodeA() == currencyCodeA)
                .filter(cur -> cur.getCurrencyCodeB() == currencyCodeB)
                .findFirst();
    }

    /**
     * Add information about the new currency to the cache.
     * <p>
     * Format: Course A to B
     * @param currencyCodeA
     *        (A) First currency.
     * @param currencyCodeB
     *        (B) Second currency.
     * @param rateBuy
     * @param rateCross
     * @param rateSell
     */
    public void addCurrency(int currencyCodeA, int currencyCodeB, double rateBuy, double rateCross,
                            double rateSell) {
        currencyList.add(new CurrencyData(
                CurrencyCacheLoader.getByCode(currencyCodeA)
                        .getCurrencyCode(), currencyCodeA, currencyCodeB,
                rateBuy, rateCross, rateSell));
    }

    /**
     * Update the current values of the exchange rate.
     * @param c
     *        Currency data.
     * @param rateBuy
     * @param rateCross
     * @param rateSell
     */
    public void updateCurrency(CurrencyData c, double rateBuy, double rateCross, double rateSell) {
        c.setRateBuy(rateBuy);
        c.setRateCross(rateCross);
        c.setRateSell(rateSell);
    }

    /**
     * Shutdown service.
     *
     * @param time The time allotted for completing
     *             all operations. After that - forced
     *             shutdown of the program.
     */
    public void finish(long time) {
        if (runned) Updatable.finish(time);
        onFinish();
        currencyList.clear();
    }

    /**
     * Used for API requests via HTTP.
     */
    protected ApiRequest getApiRequest() {
        return apiRequest;
    }
}
