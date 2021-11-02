//Class Copied From repository: https://github.com/TakahikoKawasaki/CountryCode
// Author: Takahiko Kawasaki, Neo Visionaries Inc.
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;
import java.util.*;
import java.util.Currency;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CountryUtils implements Serializable {

    String[] countryCodes;
    Locale locale;
    Map<String, String> countries = new HashMap<>();

    public CountryUtils() {
        countryCodes = Locale.getISOCountries();
        //locale = new Locale("en", country);
        countries = new HashMap<>();
        fillCountriesList();

    }

    private void fillCountriesList() {
        for (String iso : Locale.getISOCountries()) {
            locale = new Locale("", iso);
            if (locale.getDisplayCountry(Locale.ENGLISH).equals("United States")){
                //instead of United States put USA -> correction from csv file
                countries.put("USA", iso);
            }else{
                countries.put(locale.getDisplayCountry(Locale.ENGLISH), iso);
            }
        }
    }


    public String getCountryNameByCode(String code) {
        for (Map.Entry<String, String> entry : countries.entrySet()) {
            if (entry.getValue().equals(code)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public String getCountryCodeByName(String name) {
        return countries.get(name);
    }

    public void listCountries() {
        for (Map.Entry<String, String> entry : countries.entrySet()) {
//            System.out.println(entry.getValue() + " - " + entry.getKey());
        }
    }

    public ObservableList<String> getCoutryList() {

        List<String> listCountriesTemp = new ArrayList();

        for (Map.Entry<String, String> entry : countries.entrySet()) {
            listCountriesTemp.add(entry.getValue() + " - " + entry.getKey());
        }

        ObservableList<String> listCountries = FXCollections.observableArrayList(listCountriesTemp);

        java.util.Collections.sort(listCountries);

        return listCountries;
    }

}
/*

    String[] isoCountries = Locale.getISOCountries();
    for (String country : isoCountries) {
        Locale locale = new Locale("en", country);
        String iso = locale.getISO3Country();

        String code = locale.getCountry();

        String name = locale.getDisplayCountry();

        System.out.println(iso + " " + code + " " + name);
    }

 */
