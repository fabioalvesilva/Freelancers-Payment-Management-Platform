/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.autorizacao.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class UserRoleRegister implements Serializable
{
    private Set<UserRole> m_roleList = new HashSet<UserRole>();
    
    public UserRole newUserRole(String strRole)
    {
        return new UserRole(strRole);
    }
    
    public UserRole newUserRole(String strRole, String strDescription)
    {
        return new UserRole(strRole,strDescription);
    }
    
    public boolean addRole(UserRole role)
    {
        if (role != null)
            return this.m_roleList.add(role);
        return false;
    }
    
    public boolean removeRole(UserRole role)
    {
        if (role != null)
            return this.m_roleList.remove(role);
        return false;
    }
    
    public UserRole searchRole(String strRole)
    {
        for(UserRole p: this.m_roleList)
        {
            if(p.hasId(strRole))
                return p;
        }
        return null;
    }
    
    public boolean hasPapel(String strRole)
    {
        UserRole role = searchRole(strRole);
        if (role != null)
            return this.m_roleList.contains(role);
        return false;
    }
    
    public boolean hasRole(UserRole role)
    {
        return this.m_roleList.contains(role);
    }
}
