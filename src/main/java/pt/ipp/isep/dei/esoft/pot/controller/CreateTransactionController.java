/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.controller;

import java.util.Date;
import pt.ipp.isep.dei.esoft.pot.model.*;

/**
 *
 * @author jpjes
 */
public class CreateTransactionController {

    private Platform m_oPlataforma;
    private Transaction m_oTransaction;
    private RegisterTransaction m_oRegisterTransaction;

    public CreateTransactionController() {
        if (!POTAplication.getInstance().getAtualSession().isLoggedInComPapel(Constantes.ORGANIZATION_COLLABORATOR_ROLE)) {
            throw new IllegalStateException("Utilizador não Autorizado");
        }
        this.m_oPlataforma = POTAplication.getInstance().getPlatform();
        this.m_oRegisterTransaction = this.m_oPlataforma.getRegisterTransaction();
    }

    public void getListOfTask() {

    }

    public Transaction newTransaction(String task) {
        return this.m_oRegisterTransaction.newTransaction(new Task());
    }

//    public void addFreelancer(String freelancer) {
//        this.m_oRegisterTransaction.addFreelancer(m_oTransaction, new Freelancer());
//    }
    
    public void addTransactionInfo(Date endDate, int delay, String descWork) {
        this.m_oRegisterTransaction.addTransactionInfo(m_oTransaction, endDate, delay, descWork);
    }

    public boolean RegisterTransaction() {
        return this.m_oRegisterTransaction.registerTransaction(m_oTransaction);
    }
}
