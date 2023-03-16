package ua.juniffiro.currency.api.currency.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.juniffiro.currency.api.misc.CurrencyCacheLoader;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 04/03/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class TestCurrencyCreator {

    @BeforeAll
    public static void startTests() {
        System.out.println("Currency Cache Loader test starts.");
    }

    @BeforeEach
    public void beforeEach() {
        Assertions.assertNotNull(CurrencyCacheLoader.CURRENCY_MAP);
    }

    @Test
    public void testCreateCurrency() {
        CurrencyCacheLoader.getByName("UAH").ifPresent(currency ->
                System.out.printf("UAH: %d%n", currency.getNumericCode()));

        CurrencyCacheLoader.getByName("USD").ifPresent(currency ->
                System.out.printf("USD: %d%n", currency.getNumericCode()));

        CurrencyCacheLoader.getByName("EUR").ifPresent(currency ->
                System.out.printf("EUR: %d%n", currency.getNumericCode()));

        CurrencyCacheLoader.getByName("PLN").ifPresent(currency ->
                System.out.printf("PLN: %d%n", currency.getNumericCode()));

        CurrencyCacheLoader.getByName("GBP").ifPresent(currency ->
                System.out.printf("GBP: %d%n", currency.getNumericCode()));
    }
}
