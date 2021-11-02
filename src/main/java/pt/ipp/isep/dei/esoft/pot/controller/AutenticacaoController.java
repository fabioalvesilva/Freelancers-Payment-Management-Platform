/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.controller;

import java.util.List;
import pt.ipp.isep.dei.esoft.autorizacao.model.UserRole;

/**
 *
 * @author paulomaio
 */
public class AutenticacaoController
{
    private POTAplication m_oApp;
    
    public AutenticacaoController()
    {
        this.m_oApp = POTAplication.getInstance();
    }
    
    public boolean doLogin(String strId, String strPwd)
    {
        return this.m_oApp.doLogin(strId, strPwd);
    }
    
    public UserRole getUserRole()
    {
        if (this.m_oApp.getAtualSession().isLoggedIn())
        {
            return this.m_oApp.getAtualSession().getUserRole();
        }
        return null;
    }

    public void doLogout()
    {
        this.m_oApp.doLogout();
    }
}
