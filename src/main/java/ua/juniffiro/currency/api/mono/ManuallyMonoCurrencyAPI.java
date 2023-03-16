package ua.juniffiro.currency.api.mono;

import ua.juniffiro.currency.api.utils.ThreadUtils;

import java.util.concurrent.TimeUnit;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 24/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class ManuallyMonoCurrencyAPI extends MonoCurrencyAPI {

    public ManuallyMonoCurrencyAPI(long updateDelay) {
        super(updateDelay);
    }

    @Override
    public void afterInit() {
        // First run
        handleUpdate();
    }

    @Override
    public void onUpdate() {
        handleUpdate();
    }

    /**
     * Local method for updating
     * data from API service.
     */
    private void handleUpdate() {
        long startDelay = TimeUnit.SECONDS.toMillis(getUpdateDelay());
        requestUpdate();
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
    }
}
