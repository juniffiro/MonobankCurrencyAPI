package ua.juniffiro.currency.api.currency.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ua.juniffiro.currency.api.core.Currency;
import ua.juniffiro.currency.api.mono.ManuallyMonoCurrencyAPI;
import ua.juniffiro.currency.api.mono.MonoCurrencyAPI;
import ua.juniffiro.currency.api.core.CurrencyData;
import ua.juniffiro.currency.api.core.CurrencyAPI;

import java.util.Optional;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 04/03/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class MonoCurrencyTest {

    @Test
    public void testLiveCurrencyAPI() {
        CurrencyAPI currencyAPI = new ManuallyMonoCurrencyAPI(60L);
        Assertions.assertTrue(currencyAPI.isInitialized());

        Optional<CurrencyData> eurToUah = currencyAPI.getCurrency(978, 980);
        eurToUah.ifPresent(currencyData ->
                System.out.printf("EUR to UAH exchange rate %s%n", currencyData));

       // ThreadUtils.sleep(6000L);

        currencyAPI.onUpdate();

        Optional<CurrencyData> usdToEur = currencyAPI.getCurrency(Currency.EUR.getCode(),
                Currency.USD.getCode());
        usdToEur.ifPresent(currencyData ->
                System.out.printf("EUR to USD exchange rate %s%n", currencyData));
    }

    @Test
    public void testCurrencyAPI() {
        CurrencyAPI currencyAPI = new MonoCurrencyAPI(30L);

        Assertions.assertTrue(currencyAPI.isInitialized());

        Optional<CurrencyData> eurToUah = currencyAPI.getCurrency(978, 980);
        eurToUah.ifPresent(currencyData ->
                System.out.printf("EUR to UAH exchange rate %s%n", currencyData));
    }

    @Test
    @Disabled
    public void testUpdatableCurrencyAPI() {
        CurrencyAPI currencyAPI = new MonoCurrencyAPI(true, 30L);

        Assertions.assertTrue(currencyAPI.isInitialized());

        Optional<CurrencyData> eurToUah = currencyAPI.getCurrency(978, 980);
        eurToUah.ifPresent(currencyData ->
                System.out.printf("EUR to UAH exchange rate %s%n", currencyData));

        currencyAPI.finish(60L);
    }
}
