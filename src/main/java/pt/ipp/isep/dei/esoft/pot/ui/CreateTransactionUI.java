/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.ui;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.pot.controller.CreateFreelancerController;
import pt.ipp.isep.dei.esoft.pot.model.Address;
import pt.ipp.isep.dei.esoft.pot.model.Category;
import pt.ipp.isep.dei.esoft.pot.model.CountryUtils;
import pt.ipp.isep.dei.esoft.pot.model.Freelancer;
import pt.ipp.isep.dei.esoft.pot.model.FreelancerRegister;
import pt.ipp.isep.dei.esoft.pot.model.Organization;
import pt.ipp.isep.dei.esoft.pot.model.Task;
import pt.ipp.isep.dei.esoft.pot.model.Transaction;
import pt.ipp.isep.dei.esoft.pot.ui.AlertaUI;
import pt.ipp.isep.dei.esoft.pot.ui.MainSceneUI;

/**
 * FXML Controller class
 *
 * @author jpjes
 */
public class CreateTransactionUI implements Initializable {
    private MainSceneUI mainSceneUI;
    private Set<Freelancer> listFreelancers; 
    private List<Task> listTasks;
    private Organization org;
    private Stage showListTransactionStage;
    private Transaction transaction;
    private boolean showTaskCombo = true;
    private boolean showFreelancerCombo = true;
    private CreateFreelancerController freelancerController;
    private Freelancer free = null; 
    private Task task = null;
    
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtTransactionID;
    @FXML
    private TextField txtDelay;
    @FXML
    private DatePicker txtEndDate;
    @FXML
    private TextArea txtWorkDescription;
    @FXML
    private ComboBox<Freelancer> comboFreelancer;
    @FXML
    private ComboBox<Task> comboTask;
    private GridPane gridCreate;
    @FXML
    private GridPane gridConfirm;
    @FXML
    private Button btnCancelConfirmation;
    @FXML
    private TextArea txtConfirmTextField;
    @FXML
    private FlowPane buttonPane;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnAddTask;
    @FXML
    private Button btnAddFreelancer;
    private GridPane gridFreelancerComboBox;
    private ScrollPane gridAddFreelancer;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtExpertise;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNIF;
    @FXML
    private TextField txtIBAN;
    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtPostalCode;
    @FXML
    private TextField txtLocality;
    @FXML
    private ListView<String> lstvwListCountries;
    private GridPane gridTaskComboBox;
    private ScrollPane gridAddTask;
    @FXML
    private TextField txtTaskId;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtAssignDuration;
    @FXML
    private TextField txtCoustPerHour;
    @FXML
    private TextField txtCategory;
    @FXML
    private GridPane gridCreateTask;
    @FXML
    private GridPane gridCreateFreelancer;
    @FXML
    private GridPane gridCreateTransaction;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        txtDelay.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, 
//                String newValue) {
//                if (newValue.matches("\\d*")) {
//                    int value = Integer.parseInt(newValue);
//                } else {
//                    txtDelay.setText(oldValue);
//                }
//            }
//        });
        CountryUtils country = new CountryUtils();
        ObservableList<String> listCountries = country.getCoutryList();
        lstvwListCountries.setItems(listCountries);

//        updateView(this.gridConfirm, this.gridCreateTransaction);
        this.buttonPane.setVisible(true);
        
        this.gridCreateTask.setVisible(false);
        this.gridCreateFreelancer.setVisible(false);
        this.gridCreateTransaction.setVisible(true);
    }    

    public void associateParentUI(MainSceneUI mainSceneUI) {
        updateView(this.gridConfirm, this.gridCreateTransaction);
        this.buttonPane.setVisible(true);
        this.mainSceneUI = mainSceneUI;
        freelancerController = new CreateFreelancerController();
        this.org = this.mainSceneUI.getMainApp().getPlatform().getOrganizationRegister().getOrganizationByUserEmail(this.mainSceneUI.getUserSession().getEmailUtilizador());
        fillComboBox();
    }
    
    public void fillComboBox() {
        this.listFreelancers = this.mainSceneUI.getMainApp().getPlatform().getFreelancerRegister().getFreelancerList();
         
        this.listTasks = org.getM_taskList().getListTasks();
        for(Freelancer free : this.listFreelancers){
            comboFreelancer.getItems().add(free);
        }
        for(Task task : this.listTasks){
            comboTask.getItems().add(task);
        }
    }
    
    @FXML
    private void btnDoAdd(ActionEvent event) {
        try{
            if(!this.showFreelancerCombo) {
                FreelancerRegister freelancerRegister = this.mainSceneUI.getMainApp().getPlatform().getFreelancerRegister();

                String country = lstvwListCountries.getSelectionModel().getSelectedItem().substring(0, 2).toUpperCase();

                Address address = new Address(txtStreet.getText(), txtPostalCode.getText(), txtLocality.getText(), country);

                String txtName = (txtFirstName.getText() + " " + txtLastName.getText());
                
                this.free = freelancerRegister.newFreelancer(txtName, txtExpertise.getText(), txtEmail.getText(),
                        Integer.parseInt(txtNIF.getText()), txtIBAN.getText(), address);
            } else {
                this.free = this.comboFreelancer.getValue();
            }
            if(!this.showTaskCombo) {
                Category category = new Category(txtCategory.getText());
                String id = txtTaskId.getText();
                String desc = txtDescription.getText();
                double duration = Double.parseDouble(txtAssignDuration.getText());
                double coust = Double.parseDouble(txtCoustPerHour.getText());
                this.task = org.getM_taskList().newTask(id, desc, duration, coust, category);
            } else {
                this.task = this.comboTask.getValue();
            }
            if(!this.txtTransactionID.getText().equals("") || !this.txtDelay.getText().equals("") || !this.txtWorkDescription.getText().equals("")
                   || this.txtEndDate.getValue() != null || this.free != null || this.task != null  ) {
                String[] dateSplit = this.txtEndDate.getValue().toString().split("-");
                Date endDate = new Date(Integer.parseInt(dateSplit[0]),Integer.parseInt(dateSplit[1]),Integer.parseInt(dateSplit[2]));
                Transaction transaction = org.getM_transactionList().createTransaction(Integer.parseInt(this.txtTransactionID.getText()), endDate, Integer.parseInt(this.txtDelay.getText()), this.txtWorkDescription.getText(), this.task, this.free);
                        
                if(transaction != null) {
                    this.transaction = transaction;
                    this.txtConfirmTextField.setText(String.format("The Freelancer: %s\nAmount to be paid: %.2f€\n", transaction.getFreelancer().getName(),transaction.amountToPaid()));
                    updateView(this.gridCreateTransaction, this.gridConfirm);
                    this.buttonPane.setVisible(false);
                }
            } else {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, "Create Transaction", "Invalid Data",
                        "Please valid the data and add again!").show();
            }
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, "Create Transaction", "Invalid Data",
                        "Please valid the data and add again!").show();
        }
    }
    
    private void updateView(GridPane remove, GridPane show) {
        remove.setVisible(false);
        show.setVisible(true);
    }
    
    @FXML
    private void btnDoCancel(ActionEvent event) {
        
        closeCreateTransactionUI(event);
    }

    @FXML
    private void chooseFreelancer(ActionEvent event) {
    }

    @FXML
    private void chooseTask(ActionEvent event) {
    }
    
    private void closeCreateTransactionUI(ActionEvent event) {
        this.txtTransactionID.clear();
        this.txtDelay.clear();
        this.txtWorkDescription.clear();
        this.comboFreelancer.getItems().clear();
        this.comboTask.getItems().clear();
        updateView(this.gridConfirm, this.gridCreateTransaction);
        this.buttonPane.setVisible(true);
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void btnDoCancelConfirmation(ActionEvent event) {
        closeCreateTransactionUI(event);
    }

    @FXML
    private void btnDoConfirm(ActionEvent event) {
        FreelancerRegister freelancerRegister = this.mainSceneUI.getMainApp().getPlatform().getFreelancerRegister();
        boolean addFreelancer = true;
        boolean addTask = true;
        if(!this.showTaskCombo)
            addFreelancer = freelancerRegister.freelancerRegister(this.free);
        if(!this.showFreelancerCombo)
            addTask = org.addTask(this.task);
             
        if(addFreelancer && addTask){
           org.addTransaction(this.transaction);
        }    
        AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, "Create Transaction", "",
                        "Create With Success!").show();
        closeCreateTransactionUI(event);
    }

    @FXML
    private void btnDoAddTask(ActionEvent event) {
        if(this.showTaskCombo) {
            this.comboTask.setVisible(false);
            this.gridCreateTask.setVisible(true);
            this.gridCreateFreelancer.setVisible(false);
            this.gridCreateTransaction.setVisible(false);
            this.btnBack.setDisable(false);
            this.btnAdd.setDisable(true);
            this.showTaskCombo = false;
        } else {
            this.btnAddFreelancer.setDisable(false);
            this.comboTask.setVisible(true);
//            this.gridTaskComboBox.setVisible(true);
//            this.gridAddTask.setVisible(false);
            this.gridCreateTask.setVisible(false);
            this.gridCreateFreelancer.setVisible(false);
            this.gridCreateTransaction.setVisible(true);
            this.btnBack.setDisable(true);
            this.btnAdd.setDisable(false);
            this.showTaskCombo = true;
        }

        
        
    }

    @FXML
    private void btnDoAddFreelancer(ActionEvent event) {
        if(this.showFreelancerCombo) {
            this.gridCreateTask.setVisible(false);
            this.gridCreateFreelancer.setVisible(true);
            this.gridCreateTransaction.setVisible(false);
            this.btnBack.setDisable(false);
            this.btnAdd.setDisable(true);
            this.comboFreelancer.setVisible(false);
            this.showFreelancerCombo = false;
        } else {
            this.gridCreateTask.setVisible(false);
            this.gridCreateFreelancer.setVisible(false);
            this.gridCreateTransaction.setVisible(true);
            this.btnBack.setDisable(true);
            this.btnAdd.setDisable(false);
            this.comboFreelancer.setVisible(true);
            this.showFreelancerCombo = true;
        }

        
        
    }

    @FXML
    private void btnReturn(ActionEvent event) {
        
        this.gridCreateTask.setVisible(false);
        this.gridCreateFreelancer.setVisible(false);
        this.gridCreateTransaction.setVisible(true);
        this.btnBack.setDisable(true);
        this.btnAdd.setDisable(false);
        
    }
}
