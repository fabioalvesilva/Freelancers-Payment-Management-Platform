/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import pt.ipp.isep.dei.esoft.pot.model.Address;
import pt.ipp.isep.dei.esoft.pot.model.Constantes;
import pt.ipp.isep.dei.esoft.pot.model.Freelancer;
import pt.ipp.isep.dei.esoft.pot.model.FreelancerRegister;
import pt.ipp.isep.dei.esoft.pot.model.Platform;
import pt.ipp.isep.dei.esoft.pot.ui.console.utils.Utils;

/**
 *
 * @author
 */
public class CreateFreelancerController {

    private POTAplication m_oApp;
    private Platform m_oPlatform;
    private FreelancerRegister m_FreelancerRegister;
    private Freelancer m_oFreelancer;

    public CreateFreelancerController() {
        if (!POTAplication.getInstance().getAtualSession().isLoggedInComPapel(Constantes.ORGANIZATION_COLLABORATOR_ROLE)) {
            throw new IllegalStateException("Unregistered user");
        }
        this.m_oApp = POTAplication.getInstance();
        this.m_oPlatform = m_oApp.getPlatform();
        this.m_FreelancerRegister = this.m_oPlatform.getFreelancerRegister();
    }

    public boolean newFreelancer(String name, String expertise, String email, int nif, String bankAccount, Address address) {
        try {
            this.m_oFreelancer = this.m_FreelancerRegister.newFreelancer(name, expertise, email, nif, bankAccount, address);
            return this.m_FreelancerRegister.freelancerRegister(this.m_oFreelancer);

        } catch (RuntimeException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            this.m_oFreelancer = null;
            return false;
        }
    }

    public boolean freelancerRegister() {
        return this.m_FreelancerRegister.freelancerRegister(this.m_oFreelancer);
    }

    public String getFreelancerString() {
        return this.m_oFreelancer.toString();
    }

}
