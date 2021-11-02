/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author paulomaio
 */
public class Address implements Serializable {

    private String m_strStreet;
    private String m_strPostalCode;
    private String m_strLocality;
    private String m_strCountry;
    private Country country;

    private static final String COD_POSTAL_OMISSION = "withoud post_code";
    private static final String LOCALITY_OMISSION = "withoud locality";

    public Address(String strLocal, String strCodPostal, String strLocalidade, String m_strCountry) {
        if ((strLocal == null) || (strCodPostal == null) || (strLocalidade == null) || (m_strCountry == null)
                || (strLocal.isEmpty()) || (strCodPostal.isEmpty()) || (strLocalidade.isEmpty()) || (m_strCountry.isEmpty())) {
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        }

        this.m_strStreet = strLocal;
        this.m_strPostalCode = strCodPostal;
        this.m_strLocality = strLocalidade;
        this.m_strCountry = m_strCountry;
        this.country = new Country(this.m_strCountry);
    }

    public Address(String strLocal, String m_strCountry) {
        if ((strLocal == null) || (m_strCountry == null)
                || (strLocal.isEmpty()) || (m_strCountry.isEmpty())) {
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        }

        this.m_strStreet = strLocal;
        this.m_strCountry = m_strCountry;
        this.m_strPostalCode = COD_POSTAL_OMISSION;
        this.m_strLocality = LOCALITY_OMISSION;
        this.country = new Country(this.m_strCountry);
    }

    public Address(Address address) {
        if ((address.m_strStreet == null) || (address.m_strPostalCode == null) || (address.m_strLocality == null)
                || (address.m_strCountry == null) || (address.m_strStreet.isEmpty()) || (address.m_strPostalCode.isEmpty())
                || (address.m_strLocality.isEmpty()) || (address.m_strCountry.isEmpty())) {
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        }

        this.m_strStreet = address.m_strStreet;
        this.m_strPostalCode = address.m_strPostalCode;
        this.m_strLocality = address.m_strLocality;
        this.m_strCountry = address.m_strCountry;
        this.country = new Country(this.m_strCountry);
    }

    public String getCountryCode(){
        return this.country.getCountryCode();
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hash(this.m_strStreet, this.m_strPostalCode, this.m_strLocality, this.m_strCountry);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        // Inspirado em https://www.sitepoint.com/implement-javas-equals-method-correctly/

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
        Address obj = (Address) o;
        return (Objects.equals(m_strStreet, obj.m_strStreet)
                && Objects.equals(m_strPostalCode, obj.m_strPostalCode)
                && Objects.equals(m_strLocality, obj.m_strLocality)
                && Objects.equals(m_strCountry, obj.m_strCountry));
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s - %s   ", this.m_strStreet, this.m_strPostalCode, this.m_strLocality, this.m_strCountry);
    }

}
