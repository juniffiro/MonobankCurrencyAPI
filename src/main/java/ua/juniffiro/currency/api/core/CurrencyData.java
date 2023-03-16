package ua.juniffiro.currency.api.core;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 24/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class CurrencyData {

    private String currencyName;
    private int currencyCodeA;
    private int currencyCodeB;
    private double rateBuy;
    private double rateCross;
    private double rateSell;

    public CurrencyData(String currencyName, int currencyCodeA, int currencyCodeB, double rateBuy, double rateCross, double rateSell) {
        this.currencyName = currencyName;
        this.currencyCodeA = currencyCodeA;
        this.currencyCodeB = currencyCodeB;
        this.rateBuy = rateBuy;
        this.rateCross = rateCross;
        this.rateSell = rateSell;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public int getCurrencyCodeA() {
        return currencyCodeA;
    }

    public void setCurrencyCodeA(int currencyCodeA) {
        this.currencyCodeA = currencyCodeA;
    }

    public int getCurrencyCodeB() {
        return currencyCodeB;
    }

    public void setCurrencyCodeB(int currencyCodeB) {
        this.currencyCodeB = currencyCodeB;
    }

    public double getRateBuy() {
        return rateBuy;
    }

    public void setRateBuy(double rateBuy) {
        this.rateBuy = rateBuy;
    }

    public double getRateCross() {
        return rateCross;
    }

    public void setRateCross(double rateCross) {
        this.rateCross = rateCross;
    }

    public double getRateSell() {
        return rateSell;
    }

    public void setRateSell(double rateSell) {
        this.rateSell = rateSell;
    }

    @Override
    public String toString() {
        return "CurrencyData{" +
                "currencyName='" + currencyName + '\'' +
                ", currencyCodeA=" + currencyCodeA +
                ", currencyCodeB=" + currencyCodeB +
                ", rateBuy=" + rateBuy +
                ", rateCross=" + rateCross +
                ", rateSell=" + rateSell +
                '}';
    }
}
