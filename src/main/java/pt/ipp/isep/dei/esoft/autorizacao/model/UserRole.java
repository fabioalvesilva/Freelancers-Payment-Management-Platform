/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.autorizacao.model;

import java.io.Serializable;
import java.util.Objects;


public class UserRole implements Serializable
{
    private String m_strRole;
    private String m_strDescription;
    
    public UserRole(String strRole)
    {
        if ( (strRole == null) || (strRole.isEmpty()))
            throw new IllegalArgumentException("The arguments can not be null or empty.");
        
        this.m_strRole = strRole;
        this.m_strDescription = strRole;
    }
    
    public UserRole(String strRole, String strDescription)
    {
        if ( (strRole == null) || (strDescription == null) || (strRole.isEmpty())|| (strDescription.isEmpty()))
            throw new IllegalArgumentException("The arguments can not be null or empty.");
        
        this.m_strRole = strRole;
        this.m_strDescription = strDescription;
    }
    
    public String getRole()
    {
        return this.m_strRole;
    }
    
    public String getDescription()
    {
        return this.m_strDescription;
    }

    public boolean hasId(String strId)
    {
        return this.m_strRole.equals(strId);
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.m_strRole);
        return hash;
    }
    
    @Override
    public boolean equals(Object o) {
        // Inspirado em https://www.sitepoint.com/implement-javas-equals-method-correctly/
        
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        // field comparison
        UserRole obj = (UserRole) o;
        return Objects.equals(m_strRole, obj.m_strRole);
    }
    
    @Override
    public String toString()
    {
        return String.format("%s - %s", this.m_strRole, this.m_strDescription);
    }
}
