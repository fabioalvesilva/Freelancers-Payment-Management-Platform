/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;

/**
 *
 * @author
 */
public class Freelancer implements Serializable {

    private String id;
    private String name;
    private String expertise;
    private String email;
    private int nif;
    private String bankAccount;
    private Address address;

    public Freelancer(String id, String name, String expertise, String email, int nif, String bankAccount, Address address) {
        setId(id);
        setName(name);
        setExpertise(expertise);
        setEmail(email);
        setNif(nif);
        setBankAccount(bankAccount);
        setAddress(address);
    }

    public Freelancer(String id, String name, String expertise, String email, int nif, String bankAccount,
            String m_strStreet, String m_strPostalCode, String m_strLocality, String m_strCountry) {
        setId(id);
        setName(name);
        setExpertise(expertise);
        setEmail(email);
        setNif(nif);
        setBankAccount(bankAccount);
        setAddress(new Address(m_strStreet, m_strPostalCode, m_strLocality, m_strCountry));
    }

    public Freelancer(Freelancer free) {
        setId(free.id);
        setName(free.name);
        setExpertise(free.expertise);
        setEmail(free.email);
        setNif(free.nif);
        setBankAccount(free.bankAccount);
        setAddress(free.address);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExpertise() {
        return expertise;
    }

    public String getEmail() {
        return email;
    }

    public int getNif() {
        return nif;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public Address getAddress() {
        return address;
    }

    public String getCountryCode() {
        return this.address.getCountryCode();
    }

    public final void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid ID!");
        }

        this.id = id;
    }

    public final void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Name!");
        }

        this.name = name;
    }

    public final void setExpertise(String expertise) {
        if (expertise == null || expertise.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Expertise!");
        }

        this.expertise = expertise;
    }

    public final void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Email!");
        }

        this.email = email;
    }

    public final void setNif(int nif) {
        if (nif < 100000000 || nif > 999999999) {
            throw new IllegalArgumentException("Invalid NIF!");
        }

        this.nif = nif;
    }

    public final void setBankAccount(String bankAccount) {
        if (bankAccount == null || bankAccount.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Bank Account!");
        }

        this.bankAccount = bankAccount;
    }

    public final void setAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Invalid Address!");
        }

        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s - %s - %d - %s - %s",
                id, name, expertise, email, nif, bankAccount, address);
    }

}
