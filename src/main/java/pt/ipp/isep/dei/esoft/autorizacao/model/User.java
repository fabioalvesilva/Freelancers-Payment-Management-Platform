/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.autorizacao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class User implements Serializable
{
    private String m_strName;
    private String m_strEmail;
    private String m_strPassword; // Não deveria guardar a password em "plain text"
    private UserRole m_Role;
    
    public User(String strName, String strEmail, String strPassword)
    {
    
        if ( (strName == null) || (strEmail == null) || (strPassword == null) || (strName.isEmpty()) || (strEmail.isEmpty()) || (strPassword.isEmpty()))
            throw new IllegalArgumentException("Arguments can not be null or empty.");
        
        this.m_strName = strName;
        this.m_strEmail = strEmail;
        this.m_strPassword = strPassword;
        
    }
    
 
    
    public String getId()
    {
        return this.m_strEmail;
    }
    
    public String getNome()
    {
        return this.m_strName;
    }
    
    public String getEmail()
    {
        return this.m_strEmail;
    }
    
    public boolean hasId(String strId)
    {
        return this.m_strEmail.equals(strId);
    }
    
    public boolean hasPassword(String strPwd)
    {
        return this.m_strPassword.equals(strPwd);
    }
    
    
    public boolean addRole(UserRole role)
    {
        if (role != null){
            
            this.m_Role = new UserRole (role.getDescription());
            return true;
        }else{
            return false;
        }
    }

    public UserRole getM_Role() {
        return m_Role;
    }
    
    
//    public boolean removeRole(UserRole role)
//    {
//        if (role != null)
//            return this.m_Role.remove(role);
//        return false;
//    }
//    
    public boolean hasRole(UserRole role)
    {
        return this.m_Role.equals(role);
    }
    
    public boolean hasRole(String strRole)
    {
       if (this.m_Role.hasId(strRole)){
                return true;
        }else{
                return false;
            }
    }
//    
//    public List<UserRole> getRoles()
//    {
//        List<UserRole> list = new ArrayList<>();
//        for(UserRole role: this.m_Role)
//            list.add(role);
//        return list;
//    }

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
        User obj = (User) o;
        return Objects.equals(m_strEmail, obj.m_strEmail);
    }
    
    @Override
    public String toString()
    {
        return String.format("%s - %s", this.m_strName, this.m_strEmail);
    }
}
