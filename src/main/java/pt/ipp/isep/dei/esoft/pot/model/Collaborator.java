/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;
import java.util.Objects;

public class Collaborator implements Serializable
{
    private String m_strName;
    private String m_strEmail;
            
    
    public Collaborator(String strName, String strEmail)
    {
        if ( (strName == null) || (strEmail == null) ||
                (strName.isEmpty())|| (strEmail.isEmpty()))
            throw new IllegalArgumentException("The arguments can not be null or empty.");
        
        this.m_strName = strName;
        this.m_strEmail = strEmail;
    }
    
    public Collaborator(Collaborator newColab)
    {
        if ( (newColab.m_strName == null) || (newColab.m_strEmail == null) ||
                (newColab.m_strName.isEmpty())|| (newColab.m_strEmail.isEmpty()))
            throw new IllegalArgumentException("The arguments can not be null or empty.");
        
        this.m_strName = newColab.m_strName;
        this.m_strEmail = newColab.m_strEmail;
    }
    
    public boolean hasId(String strId)
    {
        return this.m_strEmail.equalsIgnoreCase(strId);
    }
    
    public String getName()
    {
        return this.m_strName;
    }
    
    public String getEmail()
    {
        return this.m_strEmail;
    }
   
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.m_strEmail);
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
        Collaborator obj = (Collaborator) o;
        return (Objects.equals(m_strEmail, obj.m_strEmail));
    }
    
    @Override
    public String toString()
    {
        return String.format("%s - %s", this.m_strName, this.m_strEmail);
    }
}
