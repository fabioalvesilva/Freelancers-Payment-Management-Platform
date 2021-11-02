/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.autorizacao;

import java.io.Serializable;
import pt.ipp.isep.dei.esoft.autorizacao.model.UserRole;
import pt.ipp.isep.dei.esoft.autorizacao.model.UserRoleRegister;
import pt.ipp.isep.dei.esoft.autorizacao.model.UserRegister;
import pt.ipp.isep.dei.esoft.autorizacao.model.UserSession;
import pt.ipp.isep.dei.esoft.autorizacao.model.User;

/**
 *
 * @author paulomaio
 */
public class AutorizationFacade implements Serializable
{
    private UserSession m_oSession = null;
    
    private final UserRoleRegister m_roles = new UserRoleRegister();
    private final UserRegister m_users = new UserRegister();
    
    public boolean userRoleRegister(String strRole)
    {
        UserRole role = this.m_roles.newUserRole(strRole);
        return this.m_roles.addRole(role);
    }
    
    public boolean userRoleRegister(String strRole, String strDescription)
    {
        UserRole role = this.m_roles.newUserRole(strRole,strDescription);
        return this.m_roles.addRole(role);
    }
    
    public boolean userRegister (String strName, String strEmail, String strPassword)
    {
        User user = this.m_users.newUser(strName,strEmail,strPassword);
        return this.m_users.addUser(user);
    }
    
    public boolean userRegisterWithRole (String strName, String strEmail, String strPassword, String strRole)
    {
        UserRole role = this.m_roles.searchRole(strRole);
        User user = this.m_users.newUser(strName,strEmail,strPassword);
        user.addRole(role);
        return this.m_users.addUser(user);
    }
    
    public boolean userRegisterWithRoles (String strName, String strEmail, String strPassword, String[] roles)
    {
        User user = this.m_users.newUser(strName,strEmail,strPassword);
        
        for (String strRole: roles)
        {
            UserRole role = this.m_roles.searchRole(strRole);
//           
            user.addRole(role);
        }
        
        return this.m_users.addUser(user);
    }
    
    public boolean hasUser(String strId)
    {
        return this.m_users.hasUser(strId);
    }
    
    
    public UserSession doLogin(String strId, String strPwd)
    {
        User user = this.m_users.searchUser(strId);
        if (user != null)
        {
            if (user.hasPassword(strPwd)){
                this.m_oSession = new UserSession(user);
            }
        }
        return getAtualSession();
    }
    
    public UserSession getAtualSession()
    {
        return this.m_oSession;
    }
    
    public void doLogout()
    {
        if (this.m_oSession != null)
            this.m_oSession.doLogout();
        this.m_oSession = null;
    }
}
