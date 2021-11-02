/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.ui.console;

import pt.ipp.isep.dei.esoft.pot.controller.CreateFreelancerController;
import pt.ipp.isep.dei.esoft.pot.model.Address;
import pt.ipp.isep.dei.esoft.pot.model.CountryUtils;
import pt.ipp.isep.dei.esoft.pot.ui.console.utils.Utils;

/**
 *
 * @author
 */
public class CreateFreelancerUI {

    private CreateFreelancerController m_controller;

    public CreateFreelancerUI() {
        m_controller = new CreateFreelancerController();
    }

    public void run() {
        System.out.println("\nCreate Freelancer");

        if (insertData()) {
            showData();

            if (Utils.confirm("Confirm the data entered? (S/N)")) {
                if (m_controller.freelancerRegister()) {
                    System.out.println("Registration successful.");
                } else {
                    System.out.println("It wasn't possible to complete the registration successfully.");
                }
            }
        } else {
            System.out.println("An error has occurred. Operation canceled.");
        }
    }

    private boolean insertData() {
        String strFreeName = Utils.readLineFromConsole("Name and surname: ");
        String strFreeExpertise = Utils.readLineFromConsole("Expertise: ");
        String strFreeEmail = Utils.readLineFromConsole("Email: ");
        int strFreeNif = Utils.readIntegerFromConsole("NIF: ");
        String strFreeBankAccount = Utils.readLineFromConsole("IBAN: ");
        System.out.print("Adress: ");
        String m_strLocal = Utils.readLineFromConsole("Street: ");
        String m_strCodPostal = Utils.readLineFromConsole("Postal Code: ");
        String m_strLocalidade = Utils.readLineFromConsole("Locality: ");
        CountryUtils country = new CountryUtils();
        country.listCountries();
        String m_strCountry = Utils.readLineFromConsole("Country Code: ");
        m_strCountry = country.getCountryNameByCode(m_strCountry);
        Address address = new Address(m_strLocal, m_strCodPostal, m_strLocalidade, m_strCountry);

        return m_controller.newFreelancer(strFreeName, strFreeExpertise, strFreeEmail, strFreeNif, strFreeBankAccount, address);
    }

    private void showData() {
        System.out.println("\n Information to record:\n" + m_controller.getFreelancerString());
    }
}
