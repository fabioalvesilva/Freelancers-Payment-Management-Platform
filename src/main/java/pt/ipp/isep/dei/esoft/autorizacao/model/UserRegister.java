/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.autorizacao.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class UserRegister implements Serializable
{
    private Set<User> m_lstUsers = new HashSet<User>();
    
    
    public User newUser (String strName, String strEmail, String strPassword)
    {
        return new User(strName,strEmail,strPassword);
    }
    
    public boolean addUser(User user)
    {
        if (user != null)
            return this.m_lstUsers.add(user);
        return false;
    }
    
    public boolean removeUser(User user)
    {
        if (user != null)
            return this.m_lstUsers.remove(user);
        return false;
    }
    
    public User searchUser(String strId)
    {
        for(User user: this.m_lstUsers)
        {
            if(user.hasId(strId))
                return user;
        }
        return null;
    }
    
    public boolean hasUser(String strId)
    {
        User user = searchUser(strId);
        if (user != null)
            return this.m_lstUsers.contains(user);
        return false;
    }
    
    public boolean hasUser(User user)
    {
        return this.m_lstUsers.contains(user);
    }
}
