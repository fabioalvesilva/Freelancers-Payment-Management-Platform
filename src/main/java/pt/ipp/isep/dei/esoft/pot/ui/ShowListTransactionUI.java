/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import pt.ipp.isep.dei.esoft.pot.model.Address;
import pt.ipp.isep.dei.esoft.pot.model.Freelancer;
import pt.ipp.isep.dei.esoft.pot.model.FreelancerRegister;
import pt.ipp.isep.dei.esoft.pot.model.Organization;
import pt.ipp.isep.dei.esoft.pot.model.OrganizationRegister;
import pt.ipp.isep.dei.esoft.pot.model.Platform;
import pt.ipp.isep.dei.esoft.pot.model.Task;
import pt.ipp.isep.dei.esoft.pot.model.Transaction;

/**
 * FXML Controller class
 *
 * @author jpjes
 */
public class ShowListTransactionUI implements Initializable {
    private MainSceneUI mainSceneUI;
    private List<Transaction> listTransaction;
    private List<String> stringSet = new ArrayList<>();;
    ObservableList observableList = FXCollections.observableArrayList();
    
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnCancel;
    @FXML
    private ListView lstTransaction;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    public void associateParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
    }
    
    public void setListTransaction(List<Transaction> listTransaction) {
        this.listTransaction = listTransaction;
        updateListView();
    }

    public void updateListView() {
        for(Transaction t : listTransaction) {
            String s = String.format("O Freelancer: %s\nAmount to be paid: %.2f€\n", t.getFreelancer().getName(),t.amountToPaid());
            stringSet.add(s);
        }
        observableList.setAll(stringSet);
        lstTransaction.setItems(observableList);
        lstTransaction.setCellFactory(new Callback<ListView<String>, javafx.scene.control.ListCell<String>>()
        {
            @Override
            public ListCell<String> call(ListView<String> listView)
            {
                return new ListViewCell();
            }
        });
    }
    
    @FXML
    private void btnDoConfirm(ActionEvent event) {
        OrganizationRegister orgRegister = this.mainSceneUI.getMainApp().getPlatform().getOrganizationRegister();
        Organization org = orgRegister.getOrganizationByUserEmail(this.mainSceneUI.getUserSession().getEmailUtilizador());
//        boolean result = org.getM_transactionList().registerTransaction(this.listTransaction);
        FreelancerRegister freelancerRegister = this.mainSceneUI.getMainApp().getPlatform().getFreelancerRegister();
        List<Transaction> savedTransactions = new ArrayList<>();
        for(Transaction t : listTransaction) {
            boolean addFreelancer = freelancerRegister.freelancerRegister(t.getFreelancer());
            boolean addTask = org.addTask(t.getTask());
            if(addTask){
                boolean add = org.addTransaction(t);
                if(add){
                    savedTransactions.add(t);
                }
            }
        }
        for(Transaction t : savedTransactions){
            org.getM_transactionList().setTransaction(t);
        }
        closeShowListTransactionUI(event);
    }

    @FXML
    private void btnDoCancel(ActionEvent event) {
        closeShowListTransactionUI(event);
    }

    private void closeShowListTransactionUI(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    void setListTransaction(Transaction transaction) {
        this.listTransaction.add(transaction);
        updateListView();
    }
    
}
