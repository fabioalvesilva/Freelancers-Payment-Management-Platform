/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.autorizacao.model;

import java.io.Serializable;
import java.util.List;


public class UserSession implements Serializable
{
    private User m_theUser = null;
    
    private UserSession()
    {
    }
    
    public UserSession(User theUser)
    {
        if (theUser == null)
            throw new IllegalArgumentException("Argmuents can not be null or empty.");
        this.m_theUser = theUser;
    }
    
    public void doLogout()
    {
        this.m_theUser = null;
    }
    
    public boolean isLoggedIn()
    {
        return this.m_theUser != null;
    }
    
    public boolean isLoggedInComPapel(String strRole)
    {
        if (isLoggedIn())
        {
            return this.m_theUser.hasRole(strRole);
        }
        return false;
    }
    
    public String getNomeUtilizador()
    {
        if (isLoggedIn())
            this.m_theUser.getNome();
        return null;
    }
    
    public String getIdUtilizador()
    {
        if (isLoggedIn())
            this.m_theUser.getId();
        return null;
    }
    
    public String getEmailUtilizador()
    {
        return this.m_theUser.getEmail();
    }
    
    public UserRole getUserRole()
    {
        return this.m_theUser.getM_Role();
    }
}
