package pt.ipp.isep.dei.esoft.pot.model.CurrencySystem;

public interface CurrencyConverter {
     String convertCurrency(double valueInEur, String countryCodeToConvert );
     double convertCurrencyValue(double valueInEur, String countryCodeToConvert);
     String getCurrency(String countryCode);
}
