package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;

public class Country implements Comparable<Country>, Serializable{

    private CountryUtils cCode;
    private String country;
    private String countryCode;

    public Country(String country) {
        this.country = country;
        cCode = new CountryUtils();
        countryCode = cCode.getCountryCodeByName(this.country);
    }

    public String getNameCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountry(String country) {
        this.country = country;
        setCountryCode();

    }

    private void setCountryCode() {
        countryCode = cCode.getCountryCodeByName(this.country);
    }

    @Override
    public boolean equals(Object o) {
        // Inspired in https://www.sitepoint.com/implement-javas-equals-method-correctly/

        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        // field comparison
        Country obj = (Country) o;
        return (getNameCountry().equals(obj.getNameCountry()) && getCountryCode().equals(obj.getCountryCode()));
    }

    @Override
    public int compareTo(Country that) {
        if (this.country.compareTo(that.country) < 0) {
            return -1;
        } else if (this.country.compareTo(that.country) > 0) {
            return 1;
        }
        return 0;
    }


}
