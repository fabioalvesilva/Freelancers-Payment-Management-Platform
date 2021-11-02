/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import pt.ipp.isep.dei.esoft.autorizacao.AutorizationFacade;
import pt.ipp.isep.dei.esoft.pot.controller.POTAplication;
import pt.ipp.isep.dei.esoft.pot.model.EmailAPI.EmailSystem;

/**
 *
 * @author Fábio Silva
 */
public class OrganizationRegister  implements Serializable {
    
    private final AutorizationFacade m_oAutorization; 
    private final Set<Organization> m_OrganizationList;
    private final PasswordGenerator m_pwd;
//    final EmailSystem email  = POTAplication.getInstance().getPlatform().getEmailSytem();

    
    public static final String FILE_NAME = "OrganizationRegister.ltf";
    
    public OrganizationRegister(AutorizationFacade m_oAutorization){
        this.m_oAutorization = m_oAutorization;
        this.m_OrganizationList = new HashSet<>();
        this.m_pwd = new PasswordGenerator();
    }
    
    public OrganizationRegister(OrganizationRegister organizationRegister){
        this.m_oAutorization = organizationRegister.m_oAutorization;
        this.m_OrganizationList = organizationRegister.m_OrganizationList;
        this.m_pwd = new PasswordGenerator();
    }
    
    public OrganizationRegister read() {
        return read(FILE_NAME);
    }
    
    public OrganizationRegister read(String fileName) {
        return read(new File(fileName));
    }
    
    public OrganizationRegister read(File file) {
        OrganizationRegister organizationRegister;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                organizationRegister = (OrganizationRegister) in.readObject();
            } finally {
                in.close();
            }
            return organizationRegister;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("REad: " + ex);
            return new OrganizationRegister(m_oAutorization);
        }
    }
    
    public boolean save() {
        return save(FILE_NAME, this);
    }
    
    public boolean save(OrganizationRegister organizationRegister) {
        return save(FILE_NAME, organizationRegister);
    }
    
    public boolean save(String fileName, OrganizationRegister organizationRegister) {
        return save(new File(fileName), organizationRegister);
    }

    public boolean save(File file, OrganizationRegister organizationRegister) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                System.out.println(organizationRegister);
                out.writeObject(organizationRegister);
            } catch(Exception e) {
                System.out.println(e);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    public AutorizationFacade getAutorizationFacade() {
        return this.m_oAutorization;
    }
    
    public Organization newOrganization(String strOrgName, String strVAT, Collaborator theManager, Collaborator theCollaborator)
    {
        return new Organization(strOrgName,strVAT, theManager, theCollaborator);
    }

    public boolean organizationRegister(Organization theOrganization)
    {
        if (this.validateOrganization(theOrganization))
        {
            Collaborator theManager = theOrganization.getManager();
            String strMpwd = m_pwd.genPassword();
            Collaborator theCollaborator = theOrganization.getCollaborator();
            String strCpwd = m_pwd.genPassword();
            String strManagerName = theManager.getName();
            String strManagerEmail = theManager.getEmail();
            String strColabName = theCollaborator.getName();
            String strColabEmail = theCollaborator.getEmail();
            
            if (this.m_oAutorization.userRegisterWithRole(strManagerName,strManagerEmail, strMpwd, 
                    Constantes.ORGANIZATION_MANAGER_ROLE) && this.m_oAutorization.userRegisterWithRole(strColabName,strColabEmail, strCpwd, 
                    Constantes.ORGANIZATION_COLLABORATOR_ROLE) ){
                
                POTAplication.getInstance().getPlatform().getEmailSytem().sendEmail(strManagerEmail, "Password T4J", "A sua passord é: " + strMpwd);
                POTAplication.getInstance().getPlatform().getEmailSytem().sendEmail(strColabEmail, "Password T4J", "A sua passord é: " + strCpwd);
             
                return addOrganization(theOrganization);
            }
                
        }
        return false;
    }
    
    public boolean validateOrganization (Organization theOrganization)
    {
        boolean bRet = true;
        
        if (this.m_oAutorization.hasUser(theOrganization.getManager().getEmail()) || this.m_oAutorization.hasUser(theOrganization.getCollaborator().getEmail()))
            bRet = false;
        
        
      
        return bRet;
    }
    
    private boolean addOrganization(Organization theOrganization)
    {
        return m_OrganizationList.add(theOrganization);
    }
    
    private void sendPwd (String email, String pwd){
        
    }
    
    public Organization getOrganizationByUserEmail (String email){ 
        
        Organization orgTemp = null;
        
        for (Organization org : m_OrganizationList){
            if (org.getCollaborator().getEmail().equals(email) || org.getManager().getEmail().equals(email)){
                orgTemp = org;
            }
        }
        return orgTemp; 
    }
    
    public Set<Organization> getOrganizationList() {
        return this.m_OrganizationList;
    }
}
