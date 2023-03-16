package ua.juniffiro.currency.api.core;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 28/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public enum Currency {

    /*
    Small utility for marking currency codes.
     */

    /**
     * Ukrainian hryvnia
     * Symbol: ₴
     * Code: UAH 980
     */
    UAH("₴", 980),

    /**
     * United Stated Dollar.
     * Symbol: $
     * Code: USD 840
     */
    USD("$", 840),

    /**
     * Euro.
     * Symbol: €
     * Code EUR 978
     */
    EUR("€", 978),

    /**
     * Polish zloty.
     * Symbol: zł
     * Code: PLN 985
     */
    PLN("zł", 985),

    /**
     * British pound.
     * (Great Britain Pounds)
     * Symbol: £
     * Code GBP 826
     */
    GBP("£", 826);

    private final String symbol;
    private final int code;

    Currency(String symbol, int code) {
        this.symbol = symbol;
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getCode() {
        return code;
    }
}
