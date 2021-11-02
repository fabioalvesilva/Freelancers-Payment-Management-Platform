/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import pt.ipp.isep.dei.esoft.pot.model.Collaborator;
import pt.ipp.isep.dei.esoft.pot.model.Organization;
import pt.ipp.isep.dei.esoft.pot.model.Platform;
import pt.ipp.isep.dei.esoft.pot.model.OrganizationRegister;
import pt.ipp.isep.dei.esoft.pot.ui.console.utils.Utils;

/**
 *
 * @author paulomaio
 */
public class OrganizationRegisterController
{
    private POTAplication m_oApp;
    private Platform m_oPlatform;
    private OrganizationRegister m_OrganizationRegister;
    private Organization m_oOrganization;
    
    public OrganizationRegisterController()
    {
        this.m_oApp = POTAplication.getInstance();
        this.m_oPlatform = m_oApp.getPlatform();
        this.m_OrganizationRegister = this.m_oPlatform.getOrganizationRegister();
    }
    
    
    public boolean newOrganization(String strOrgName, String strVAT,
            String strManagerName, String strManagerEmail, String strColabName, String strColabEmail)
    {
        try
        {
            
            Collaborator theManager = Organization.newCollaborator(strManagerName, strManagerEmail);
            Collaborator theColab = Organization.newCollaborator(strColabName, strColabEmail);  
            this.m_oOrganization = this.m_OrganizationRegister.newOrganization(strOrgName, strVAT, theManager, theColab);
            return this.m_OrganizationRegister.validateOrganization(this.m_oOrganization);
        }
        catch(RuntimeException ex)
        {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            this.m_oOrganization = null;
            return false;
        }
    }
    
    
    
    public boolean organizationRegister()
    {
        return this.m_OrganizationRegister.organizationRegister(this.m_oOrganization);
    }

    public String getOrganizationString()
    {
        return this.m_oOrganization.toString();
    }
}
