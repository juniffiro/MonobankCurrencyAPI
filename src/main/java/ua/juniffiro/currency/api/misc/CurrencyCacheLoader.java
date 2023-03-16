package ua.juniffiro.currency.api.misc;

import java.util.Currency;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 24/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class CurrencyCacheLoader {

    /*
    Cache of currency codes.
     */
    public static final Map<Integer, Currency> CURRENCY_MAP;

    static {
        CURRENCY_MAP = Currency.getAvailableCurrencies()
                .stream()
                .collect(Collectors.toMap(Currency::getNumericCode,
                        Function.identity(), (a1, a2) -> a1));
    }

    /**
     * Get the java.util.Currency
     * by currency int code.
     *
     * @param code
     *        Currency int code.
     *        Example: USD - 840
     *
     * @return java.util.Currency from cache.
     */
    public static Currency getByCode(int code) {
        return CURRENCY_MAP.get(code);
    }

    /**
     * Get java.util.Currency
     * by abbreviated code.
     * Example: USD, EUR.
     * <p>
     * Does not throw an NPE, but may
     * return Optional.empty();
     * <p>
     * @param code
     *        Currency code name.
     *
     * @return java.util.Currency from cache.
     */
    public static Optional<Currency> getByName(String code) {
        return CURRENCY_MAP.values()
                .stream()
                .filter(cur -> cur.getCurrencyCode().equals(code))
                .findFirst();
    }
}
