package ua.juniffiro.currency.api.mono;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ua.juniffiro.currency.api.core.ApiRequest;
import ua.juniffiro.currency.api.utils.JsonUtils;
import ua.juniffiro.currency.api.utils.ThreadUtils;
import ua.juniffiro.currency.api.core.CurrencyAPI;
import ua.juniffiro.currency.api.core.CurrencyData;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 24/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class MonoCurrencyAPI extends CurrencyAPI {

    /**
     * Keys to work with JSON and data.
     * Specify exactly with API service values.
     */
    enum Keys {

        CURRENCY_CODE_A("currencyCodeA"),
        CURRENCY_CODE_B("currencyCodeB"),
        RATE_BUY("rateBuy"),
        RATE_CROSS("rateCross"),
        RATE_SELL("rateSell");

        final String key;

        Keys(String key) {
            this.key = key;
        }
    }

    public MonoCurrencyAPI(long updateDelay) {
        this(false, updateDelay);
    }

    public MonoCurrencyAPI(boolean needUpdate, long updateDelay) {
        super(new ApiRequest("https://api.monobank.ua/bank/currency"), needUpdate, updateDelay);
    }

    @Override
    public void onUpdate() {
        System.out.println("Trying to update the API.");
        handleUpdate();
    }

    /**
     * Verify that the data is correct to
     * continue running the program and
     * working with JSON. From 'getData()'
     * we get the most actual information
     * from the API service as a string.
     * If it's OK, we get JSON from the string.
     *
     * @return Is the Data valid?
     */
    protected boolean isValidData() {
        String data = getData();
        return data.contains(Keys.CURRENCY_CODE_A.key)
                && data.contains(Keys.CURRENCY_CODE_B.key)
                && data.contains(Keys.RATE_BUY.key)
                && data.contains(Keys.RATE_CROSS.key)
                && data.contains(Keys.RATE_SELL.key);
    }

    @Override
    public void afterInit() {
        long startDelay = TimeUnit.SECONDS.toMillis(getUpdateDelay());
        while (!isValidData()) {
            System.out.println(getData());
            System.out.printf(String.format(
                    "The last attempt to update the API failed. " +
                            "Repeat in %d seconds%n", TimeUnit.MILLISECONDS
                            .toSeconds(startDelay)));
            ThreadUtils.sleep(startDelay);
            startDelay += 5000L;
            requestUpdate();
        }
        System.out.println("Data has been successfully updated.");
        handleJson();

        if (isNeedUpdate()) {
            System.out.printf(
                    String.format("The data will be updated in %d seconds.",
                            getUpdateDelay()));
            runUpdate();
        }
    }

    /**
     * Handling Data.
     * In the case of an unknown format is ignored.
     */
    protected void handleJson() {
        JsonElement je = JsonUtils.GSON.fromJson(getData(), JsonElement.class);
        if (je.isJsonArray()) {
            handleJsonArray(je.getAsJsonArray());
        } else {
            System.out.println("Unknown data type in the input. Handle skipped.");
        }
    }

    /**
     * Checks validity of JSON object
     * for further actions with currency data.
     *
     * @return Is the JSON is valid?
     */
    private boolean isValidJson(JsonObject jsonObject) {
        return jsonObject.has(Keys.CURRENCY_CODE_A.key)
                && jsonObject.has(Keys.CURRENCY_CODE_B.key)
                && jsonObject.has(Keys.RATE_BUY.key)
                && jsonObject.has(Keys.RATE_CROSS.key)
                && jsonObject.has(Keys.RATE_SELL.key);
    }

    /**
     * Handle JSON array.
     * If the validation succeeds, we
     * check if the data is in the cache.
     * If so, update the data.
     * No - create new object and cache it.
     *
     * @param jsonArray
     *        JSON array received from the service
     */
    private void handleJsonArray(JsonArray jsonArray) {
        Set<JsonObject> objects = JsonUtils.jsonObjectsFromArray(jsonArray);
        objects.stream().filter(this::isValidJson)
                .forEach(object -> {
                    int codeA = object.get(Keys.CURRENCY_CODE_A.key).getAsInt();
                    int codeB = object.get(Keys.CURRENCY_CODE_B.key).getAsInt();
                    double rateBuy = object.get(Keys.RATE_BUY.key).getAsDouble();
                    double rateCross = object.get(Keys.RATE_CROSS.key).getAsDouble();
                    double rateSell = object.get(Keys.RATE_SELL.key).getAsDouble();

                    Optional<CurrencyData> opt = getCurrency(codeA, codeB);
                    if (opt.isPresent()) {
                        updateCurrency(opt.get(), rateBuy, rateCross, rateSell);
                    } else {
                        addCurrency(codeA, codeB, rateBuy, rateCross, rateSell);
                    }
                });
    }

    /**
     * Local method for updating data from API service.
     */
    private void handleUpdate() {
        if (!isValidData()) {
            System.out.printf(String.format(
                    "[Updater] The last API update attempt failed." +
                            " Repeat in %%d seconds%%n"), getUpdateDelay());
            requestUpdate();
            return;
        }
        System.out.printf("[Updater] Data was successfully updated. Repeat in %d seconds%n",
                getUpdateDelay());
       handleJson();
    }

    @Override
    public void onFinish() {
        System.out.println("The service has finished its work.");
    }
}
