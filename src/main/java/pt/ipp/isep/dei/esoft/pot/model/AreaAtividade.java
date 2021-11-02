/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author paulomaio
 */
public class AreaAtividade implements Serializable
{
    private String m_strCodigo;
    private String m_strDescricaoBreve;
    private String m_strDescricaoDetalhada;
            
    
    public AreaAtividade(String strCodigo, String strDescricaoBreve, String strDescricaoDetalhada)
    {
        if ( (strCodigo == null) || (strDescricaoBreve == null) || (strDescricaoDetalhada == null) ||
                (strCodigo.isEmpty())|| (strDescricaoBreve.isEmpty())|| (strDescricaoDetalhada.isEmpty()))
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        
        this.m_strCodigo = strCodigo;
        this.m_strDescricaoBreve = strDescricaoBreve;
        this.m_strDescricaoDetalhada = strDescricaoDetalhada;
    }
    
    public boolean hasId(String strId)
    {
        return this.m_strCodigo.equalsIgnoreCase(strId);
    }
    
    public String getCodigo()
    {
        return this.m_strCodigo;
    }
   
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.m_strCodigo);
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
        AreaAtividade obj = (AreaAtividade) o;
        return (Objects.equals(m_strCodigo, obj.m_strCodigo));
    }
    
    @Override
    public String toString()
    {
        return String.format("%s - %s - %s ", this.m_strCodigo, this.m_strDescricaoBreve, this.m_strDescricaoDetalhada);
    }

}
