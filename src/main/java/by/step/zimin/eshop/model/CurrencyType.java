package by.step.zimin.eshop.model;



public enum CurrencyType {
    DOLLAR("usd"),
    EURO("eur"),
    BELRUBLE("byn"),
    RUBLE("rub");

    private String currency;
    private   CurrencyType(String currency) {
        this.currency=currency;
    }

    public String getCurrency(){
        return currency;
    }

}
