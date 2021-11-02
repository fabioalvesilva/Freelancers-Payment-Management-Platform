package pt.ipp.isep.dei.esoft.pot.model.CurrencySystem;


import java.io.IOException;
import java.io.Serializable;

public class CurrencyConverterAdapter2 implements CurrencyConverter,Serializable {

    private CurrencyConverterApi1 api;

    //todo alterar String org para Organization org
    public CurrencyConverterAdapter2(){
        try
        {
            api = new CurrencyConverterApi1();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String convertCurrency(double valueInEur, String countryCodeToConvert) {

             return "Not supported Yet";

    }

    @Override
    public double convertCurrencyValue(double valueInEur, String countryCodeToConvert) {
        return 0;
    }

    @Override
    public String getCurrency(String countryCode) {
        return null;
    }


}
