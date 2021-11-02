package pt.ipp.isep.dei.esoft.pot.model.CurrencySystem;


import pt.ipp.isep.dei.esoft.pot.model.CountryUtils;
import pt.ipp.isep.dei.esoft.pot.model.MakePayments;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TimerTask;

public class CurrencyConverterAdapter1  implements CurrencyConverter,Serializable {

    private CurrencyConverterApi1 api;

    //todo alterar String org para Organization org
    public CurrencyConverterAdapter1(){
        try
        {
            api = new CurrencyConverterApi1();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String convertCurrency(double valueInEur, String countryCodeToConvert) {

             return api.convertToCountryCurrency(valueInEur , countryCodeToConvert);

    }

    @Override
    public double convertCurrencyValue(double valueInEur, String countryCodeToConvert) {
        return api.convertCurrencyValue(valueInEur, countryCodeToConvert);
    }

    @Override
    public String getCurrency(String countryCode) {

        return api.getCurrency(countryCode);
    }


}
