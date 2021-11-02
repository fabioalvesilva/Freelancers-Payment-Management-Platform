/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pt.ipp.isep.dei.esoft.autorizacao.AutorizationFacade;
import pt.ipp.isep.dei.esoft.pot.model.CurrencySystem.CurrencyConverter;
import pt.ipp.isep.dei.esoft.pot.model.EmailAPI.EmailSystem;

public class Platform implements Serializable{

    private String m_strDescription;

    
    private final AutorizationFacade m_oAutorization;
    private final OrganizationRegister m_OrganizationRegister;
    private final FreelancerRegister m_FreelancerRegister;
    private final Set<AreaAtividade> m_lstAreasAtividade;
    private final Set<CompetenciaTecnica> m_lstCompetencias;
    private final RegisterTransaction m_RegisterTransaction;

    private EmailSystem m_Email;
    private CurrencyConverter m_Currency;
    public Platform(String strDescription, EmailSystem email, CurrencyConverter currency) {
        if ((strDescription == null)
                || (strDescription.isEmpty())) {
            throw new IllegalArgumentException("Arguments are null or empty.");
        }

        this.m_strDescription = strDescription;
        OrganizationRegister org = new OrganizationRegister(new AutorizationFacade());
        this.m_OrganizationRegister = org.read();
        this.m_oAutorization = this.m_OrganizationRegister.getAutorizationFacade();
        this.m_FreelancerRegister = new FreelancerRegister();
        this.m_lstAreasAtividade = new HashSet<>();
        this.m_lstCompetencias = new HashSet<>();
        this.m_RegisterTransaction = new RegisterTransaction();
        this.m_Email = email;
        this.m_Currency = currency;

    }
    
    public Platform(Platform platform) {
        if ((platform.getM_strDescription()) == null) {
            throw new IllegalArgumentException("Arguments are null or empty.");
        }

        this.m_strDescription = platform.getM_strDescription();
        this.m_OrganizationRegister = platform.m_OrganizationRegister;
        this.m_oAutorization = platform.m_OrganizationRegister.getAutorizationFacade();
        this.m_FreelancerRegister = platform.m_FreelancerRegister;
        this.m_lstAreasAtividade = platform.m_lstAreasAtividade;
        this.m_lstCompetencias = platform.m_lstCompetencias;
        this.m_RegisterTransaction = platform.m_RegisterTransaction;
        this.m_Email = platform.m_Email;
        this.m_Currency = platform.m_Currency;

    }

    public String getM_strDescription() {
        return m_strDescription;
    }

    public void setM_strDescription(String m_strDescription) {
        this.m_strDescription = m_strDescription;
    }
    
    public AutorizationFacade getAutorizationFacade() {
        return this.m_oAutorization;
    }

    public OrganizationRegister getOrganizationRegister() {
        return this.m_OrganizationRegister;
    }

    public FreelancerRegister getFreelancerRegister() {
        return this.m_FreelancerRegister;
    }
    // Competências Tecnicas

    // <editor-fold defaultstate="collapsed">
    public CompetenciaTecnica getCompetenciaTecnicaById(String strId) {
        for (CompetenciaTecnica oCompTecnica : this.m_lstCompetencias) {
            if (oCompTecnica.hasId(strId)) {
                return oCompTecnica;
            }
        }

        return null;
    }

    public CompetenciaTecnica novaCompetenciaTecnica(String strId, String strDescricaoBreve, String strDescricaoCompleta, AreaAtividade oArea) {
        return new CompetenciaTecnica(strId, strDescricaoBreve, strDescricaoCompleta, oArea);
    }

    public boolean registaCompetenciaTecnica(CompetenciaTecnica oCompTecnica) {
        if (this.validaCompetenciaTecnica(oCompTecnica)) {
            return addCompetenciaTecnica(oCompTecnica);
        }
        return false;
    }

    private boolean addCompetenciaTecnica(CompetenciaTecnica oCompTecnica) {
        return m_lstCompetencias.add(oCompTecnica);
    }

    public boolean validaCompetenciaTecnica(CompetenciaTecnica oCompTecnica) {
        boolean bRet = true;

        // Escrever aqui o código de validação
        //
        return bRet;
    }
    // </editor-fold>

    // Areas de Atividade 
    // <editor-fold defaultstate="collapsed">
    public AreaAtividade getAreaAtividadeById(String strId) {
        for (AreaAtividade area : this.m_lstAreasAtividade) {
            if (area.hasId(strId)) {
                return area;
            }
        }

        return null;
    }

    public AreaAtividade novaAreaAtividade(String strCodigo, String strDescricaoBreve, String strDescricaoDetalhada) {
        return new AreaAtividade(strCodigo, strDescricaoBreve, strDescricaoDetalhada);
    }

    public boolean registaAreaAtividade(AreaAtividade oArea) {
        if (this.validaAreaAtividade(oArea)) {
            return addAreaAtividade(oArea);
        }
        return false;
    }

    private boolean addAreaAtividade(AreaAtividade oArea) {
        return m_lstAreasAtividade.add(oArea);
    }

    public boolean validaAreaAtividade(AreaAtividade oArea) {
        boolean bRet = true;

        // Escrever aqui o código de validação
        //
        return bRet;
    }

    public List<AreaAtividade> getAreasAtividade() {
        List<AreaAtividade> lc = new ArrayList<>();
        lc.addAll(this.m_lstAreasAtividade);
        return lc;
    }

    // </editor-fold>
    // Transactions
    public RegisterTransaction getRegisterTransaction() {
        return this.m_RegisterTransaction;
    }

    //RM
    public EmailSystem getEmailSytem() {
        return this.m_Email;
    }
    public CurrencyConverter getM_currency() {
        return this.m_Currency;
    }
}
