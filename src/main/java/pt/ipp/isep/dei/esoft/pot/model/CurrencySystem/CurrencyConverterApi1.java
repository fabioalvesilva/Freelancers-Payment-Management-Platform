package pt.ipp.isep.dei.esoft.pot.model.CurrencySystem;

import java.io.*;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;

public class CurrencyConverterApi1 implements Serializable {
    private Double amount;
    private Locale locale;
    private Currency currency;
    private String currencySymbol;
    private String currencyCode;
    private HashMap<String, Double> currencyRates;



    public CurrencyConverterApi1() throws IOException {
        this.currencyRates = new HashMap<String, Double>();
        loadExchangeRates();


    }

    public String convertToCountryCurrency(double value, String countryCode){
        setLocale(countryCode);
        double rate = getCurrencyRate(getCurrencyCode(countryCode));
        double valueConverted = value * rate;
        return String.format("%.2f %s", valueConverted, getCurrencyCode(countryCode));

    }

    public double convertCurrencyValue(double value, String countryCode){
        setLocale(countryCode);
        double rate = getCurrencyRate(getCurrencyCode(countryCode));
        double valueConverted = value * rate;
        return valueConverted;
    }

    public String getCurrency(String countryCode){
        return getCurrencyCode(countryCode);
    }
    private void loadExchangeRates() throws IOException {

        //      URL url = getClass().getResource("CurrencyRates");
        File file = new File("src/main/java/pt/ipp/isep/dei/esoft/pot/model/CurrencySystem/CurrencyRates");

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null)
        {
            String[] parts = line.split(" ", 2);
            if (parts.length >= 2)
            {
                String key = parts[0];
                Double value = Double.valueOf(parts[1]);
                currencyRates.put(key, value);
            } else {
                System.out.println("ignoring line: " + line);
            }
        }
        reader.close();
    }

    private double getCurrencyRate(String currencyCode) {
        return currencyRates.get(currencyCode);
    }
    private String getCurrencyCode(String countryCode){
        return currencyCode = currency.getCurrencyCode();
    }

    private void setLocale(String countryCode){
        this.locale = new Locale("EN", countryCode);
        this.currency = Currency.getInstance(locale);
    }

}
