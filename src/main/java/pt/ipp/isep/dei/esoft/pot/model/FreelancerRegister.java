/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.validator.routines.EmailValidator;

/**
 *
 * @author
 */
public class FreelancerRegister implements Serializable {

    private final Set<Freelancer> m_FreelancerList;
    EmailValidator emailValidator = EmailValidator.getInstance();

    public FreelancerRegister() {

        this.m_FreelancerList = new HashSet<>();
    }

    public Freelancer newFreelancer(String name, String expertise, String email, int nif, String bankAccount, Address address) {

        String id = generateFreelancerId(name);

        return new Freelancer(id, name, expertise, email, nif, bankAccount, address);
    }

    private String generateFreelancerId(String name) {

        String firstChar = "";
        String secondChar = "";
        int counter = 0;
        int counterLetter = 0;
        String stringToSplit = name;
        String[] tempArray;
        String delimiter = " ";

        tempArray = stringToSplit.split(delimiter);

        for (int i = 0; i < tempArray.length; i++) {
            firstChar = tempArray[0].substring(0, 1).toUpperCase();
            secondChar = tempArray[1].substring(0, 1).toUpperCase();
        }
        String id = firstChar + secondChar;
        String idPrincipal = id;

        do {
            counter = 0;
            for (Freelancer freel : m_FreelancerList) {
                if (id.equals((freel.getId()))) {
                    counter = counter + 1;
                }
            }
            if (counter > 0) {
                id = idPrincipal + String.valueOf(counterLetter + 1);
                counterLetter++;
            }
        } while (counter != 0);

        return id;
    }

    public boolean validateFreelancer(Freelancer m_oFreelancer) {
        String email = m_oFreelancer.getEmail();
        int nif = m_oFreelancer.getNif();
        boolean compareNif = true;

        for (Freelancer freelancer : m_FreelancerList) {
            if (freelancer.getNif() == nif) {
                compareNif = false;
            }
        }
        return (emailValidator.isValid(email) && compareNif);
    }

    public boolean freelancerRegister(Freelancer m_oFreelancer) {
        if (this.validateFreelancer(m_oFreelancer)) {
            return addFreelancer(m_oFreelancer);
        }
        return false;
    }

    private boolean addFreelancer(Freelancer m_oFreelancer) {
        return m_FreelancerList.add(m_oFreelancer);
    }

    public Freelancer getFreelancerByNif(int nif) {

        Freelancer freelancerTemp = null;

        for (Freelancer free : m_FreelancerList) {
            if (free.getNif() == nif) {
                freelancerTemp = new Freelancer(free);
            }
        }
        return freelancerTemp;
    }

    public Set<Freelancer> getFreelancerList() {
        return m_FreelancerList;
    }

}
