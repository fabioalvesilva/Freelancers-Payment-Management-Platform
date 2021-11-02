/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class Organization implements Serializable {

    private String m_strName;
    private String m_strVAT;
    private Collaborator m_theManager;
    private Collaborator m_theCollaborator;
    private Set<Collaborator> m_collaboratorList = new HashSet<Collaborator>();
    private TaskList m_taskList = new TaskList();
    private TransactionList m_transactionList = new TransactionList();
    private Set<Freelancer> m_freelancerList = new HashSet<Freelancer>();

    public Organization(String strOrgName, String strVAT, Collaborator theManager, Collaborator theCollaborator) {
        if ((strOrgName == null) || (strVAT == null) || (theManager == null) || (theCollaborator == null)
                || (strOrgName.isEmpty()) || (strVAT.isEmpty())) {
            throw new IllegalArgumentException("Arguments can not be empty or null.");
        }

        this.m_strName = strOrgName;
        this.m_strVAT = strVAT;
        this.m_theManager = theManager;
        this.m_theCollaborator = theCollaborator;
        this.m_collaboratorList.add(theManager);
        this.m_collaboratorList.add(theCollaborator);

    }

    public Organization(Organization newOrg) {
        if ((newOrg.m_strName == null) || (newOrg.m_strVAT == null) || (newOrg.m_theManager == null) || (newOrg.m_theCollaborator == null)
                || (newOrg.m_strName.isEmpty()) || (newOrg.m_strVAT.isEmpty())) {
            throw new IllegalArgumentException("Arguments can not be empty or null.");
        }

        this.m_strName = newOrg.m_strName;
        this.m_strVAT = newOrg.m_strVAT;
        this.m_theManager = new Collaborator(newOrg.getManager());
        this.m_theCollaborator = new Collaborator(newOrg.getCollaborator());
        this.m_collaboratorList.add(newOrg.getManager());
        this.m_collaboratorList.add(newOrg.getCollaborator());

    }

    public void setM_strName(String m_strName) {
        this.m_strName = m_strName;
    }

    public void setM_strVAT(String m_strVAT) {
        this.m_strVAT = m_strVAT;
    }

    public void setM_theManager(Collaborator m_theManager) {
        if (m_theManager == null) {
            throw new IllegalArgumentException("Invalid Manager!");
        }
        this.m_theManager = m_theManager;
    }

    public void setM_theCollaborator(Collaborator m_theCollaborator) {
        if (m_theCollaborator == null) {
            throw new IllegalArgumentException("Invalid Collaborator!");
        }
        this.m_theCollaborator = m_theCollaborator;
    }

    public void setM_collaboratorList(Set<Collaborator> m_collaboratorList) {
        this.m_collaboratorList = m_collaboratorList;
    }

    public void setM_taskList(TaskList m_taskList) {
        this.m_taskList = m_taskList;
    }

    public String getM_strName() {
        return m_strName;
    }

    public String getM_strVAT() {
        return m_strVAT;
    }

    public Set<Collaborator> getM_collaboratorList() {
        return m_collaboratorList;
    }

    public TaskList getM_taskList() {
        return m_taskList;
    }

    public TransactionList getM_transactionList() {
        return m_transactionList;
    }

    public Collaborator getCollaborator() {
        return this.m_theCollaborator;
    }

    public Collaborator getManager() {
        return this.m_theManager;
    }

    public Set<Freelancer> getM_freelancerList() {
        return m_freelancerList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.m_strVAT);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        // Inspirado em https://www.sitepoint.com/implement-javas-equals-method-correctly/

        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        // field comparison
        Organization obj = (Organization) o;
        return (Objects.equals(m_strVAT, obj.m_strVAT));
    }

    @Override
    public String toString() {
        String str = String.format("%s - %s - %s - %s", this.m_strName, this.m_strVAT, this.m_theManager.toString(), this.m_theCollaborator.toString());
        return str;
    }

    public static Collaborator newCollaborator(String strName, String strEmail) {
        return new Collaborator(strName, strEmail);
    }

    //todo RM added method to process paymentstransaction
    public TransactionList getUnpaidTransactionList() {

        TransactionList unpaidList = new TransactionList();
        ArrayList<Transaction> unpaidTrasanctions = new ArrayList<>();
        for (Transaction trans : m_transactionList.getTransList()) {
            if (!trans.hasPayment()) {
                unpaidTrasanctions.add(trans);
            }
        }
        unpaidList.setTransList(unpaidTrasanctions);
        return unpaidList;
    }

    public TaskList getUnpaidTaskList() {

        TaskList unpaidList = new TaskList();

        for (Transaction trans : m_transactionList.getTransList()) {
            if (trans.getPayment() == null || trans.getPaymentStatus() == false) {
                Task taskTemp = new Task(trans.getTask());
                unpaidList.addTask(taskTemp);
            }
        }

        return unpaidList;
    }

    public TransactionList getPaidTransactionList() {

        TransactionList paidList = new TransactionList();

        for (Transaction trans : m_transactionList.getTransList()) {
            if (trans.getPayment() != null) {
                paidList.addTransaction(trans);
//                paidList.addTransaction(trans.getId(), trans.getPayment(), trans.getTask());
            }
        }

        return paidList;
    }

    public TransactionList getPaidTransactionListByFreelancer() {

        TransactionList paidList = new TransactionList();

        for (Transaction trans : m_transactionList.getTransList()) {
            if (trans.getPayment() != null) {
                paidList.addTransaction(trans);
//                paidList.addTransaction(trans.getId(), trans.getPayment(), trans.getTask());
            }
        }

        return paidList;
    }

    public TransactionList getListTransactionByFreelancer(String email) {

        TransactionList transTemp = new TransactionList();

        for (Transaction trans : m_transactionList.getTransList()) {
            if (trans != null  && trans.getFreelancer().getEmail().equals(email)) {
                transTemp.addTransaction(trans);
//                transTemp.addTransaction(trans.getId(), trans.getPayment(), trans.getTask());
            }
        }

        return transTemp;
    }

    public TransactionList getPaidListTransactionByFreelancer(String email) {

        TransactionList transTemp = new TransactionList();

        for (Transaction trans : m_transactionList.getTransList()) {
            if (trans != null  && trans.getPayment() != null && trans.getFreelancer().getEmail().equals(email)) {
                if(trans.getPayment().getPaidStatus())
                    transTemp.addTransaction(trans);
//                    transTemp.addTransaction(trans.getId(), trans.getPayment(), trans.getTask());
            }
        }

        return transTemp;
    }

    public boolean addTask(Task task) {

        return m_taskList.registTask(task);
    }

    public boolean addTransaction(Transaction transaction) {

        return m_transactionList.addTransaction(transaction);
    }

}
