/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author jpjes
 */
public class RegisterTransaction implements Serializable {
    
    private final Set<Transaction> m_TransactionList;


    
    public RegisterTransaction() {
        this.m_TransactionList = new HashSet<>();
    }
    
    public Transaction newTransaction(Task ta) {
        return new Transaction(ta);
    }
    
    public void addFreelancer(Transaction tran,Freelancer freelancer) {
        tran.setFreelancer(freelancer);
    }
    
    public void addTransactionInfo(Transaction tran,Date endDate,int delay,String descWork) {
        tran.setEndDate(endDate);
        tran.setDelay(delay);
        tran.setDescWork(descWork);
    }
    
    public double calculateAmountToPaid(Transaction tran) {
        return 0;
    }
    
    public boolean registerTransaction(Transaction tran) {
        if(validateTransaction(tran))
           return addTransaction(tran);
        return false;
    }
    
    public boolean validateTransaction(Transaction tran) {
        return true;
    }
    
    public boolean addTransaction(Transaction tran) {
        return m_TransactionList.add(tran);
    }
    
        public Set<Transaction> getM_TransactionList() {
        return m_TransactionList;
    }
}
