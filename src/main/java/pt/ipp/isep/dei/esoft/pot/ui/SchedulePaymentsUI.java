/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.ui;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;
import pt.ipp.isep.dei.esoft.autorizacao.model.UserSession;
import pt.ipp.isep.dei.esoft.pot.model.Organization;
import pt.ipp.isep.dei.esoft.pot.model.OrganizationRegister;
import pt.ipp.isep.dei.esoft.pot.model.Platform;
import pt.ipp.isep.dei.esoft.pot.model.Scheduler;
import pt.ipp.isep.dei.esoft.pot.model.Task;
import pt.ipp.isep.dei.esoft.pot.model.TaskList;

/**
 * FXML Controller class
 *
 * @author Fábio Silva
 */
public class SchedulePaymentsUI implements Initializable {
    
    private MainSceneUI mainSceneUI;
    private UserSession m_oSession;
    private LocalDate dateNow;
    private List<Task> listUnpaidTask;
    private List<String> stringSet = new ArrayList<>();;
    ObservableList observableList = FXCollections.observableArrayList();

    @FXML
    private Button btnSchedulePayments;
    @FXML
    private Button btnSeeUnpaidTasks;
    @FXML
    private DatePicker dateSelector;
    @FXML
    private ListView<String> listView;
    @FXML
    private TextField txtTimeSelectorHour;
    @FXML
    private TextField txtTimeSelectorMinute;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dateNow = LocalDate.now();
    }

    public void associateParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
    }
    

    @FXML
    private void btnSchedulePayment(ActionEvent event) {
        
        try {
            
        Platform platform = this.mainSceneUI.getMainApp().getPlatform();
        UserSession userSession = this.mainSceneUI.getUserSession();
        OrganizationRegister orgRegister = platform.getOrganizationRegister();
       
        Organization org = orgRegister.getOrganizationByUserEmail(userSession.getEmailUtilizador());
        
        LocalDate date = dateSelector.getValue();


        
        if (date.isBefore(dateNow) || date.getDayOfMonth() > 28){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Date!");
            alert.setContentText("The date introduced must be after than " + dateNow.toString() + " and before 28th day." + "\nPlease introduce a new date.");
            
            alert.showAndWait();
        }else{
            
            if (!txtTimeSelectorHour.getText().isEmpty() || !txtTimeSelectorMinute.getText().isEmpty()){
                if (Integer.parseInt(txtTimeSelectorHour.getText()) >= 0 && Integer.parseInt(txtTimeSelectorHour.getText()) < 24 && Integer.parseInt(txtTimeSelectorMinute.getText()) >= 0 && Integer.parseInt(txtTimeSelectorMinute.getText()) < 60) {

                    //Scheduler scheduler = new Scheduler (org, date.toString());

                        String dateTime = date.toString();
                        dateTime = dateTime + " " +txtTimeSelectorHour.getText().trim() + ":"+txtTimeSelectorMinute.getText().trim();
                    //System.out.println("Datetime |" + dateTime + "|");
                        Scheduler scheduler = new Scheduler (org, dateTime);
                        
                        String pmAm = "AM";
                        
                        if (Integer.parseInt(txtTimeSelectorHour.getText()) > 11){
                            pmAm = "PM";
                        }
                    AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, "Payment Schedule", "Success",
                                    "All the payments are going to be processed on " + date.toString() + " at " + txtTimeSelectorHour.getText() + ":" + txtTimeSelectorMinute.getText() + pmAm + ".").show();
                    closeSchedulePaymentsUI(event);
                }else {
                    AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, "Payment Schedule", "Invalid time!",
                                    "The hour or minute introduced is invalid. Please try again.").show();
                }
            }else{
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, "Payment Schedule", "Invalid time!",
                                    "The hour and minute cannot be empty. Please try again.").show();
            }
            
            
            
        }
        
        
           
        }
       catch (Exception e) {
        }
    }

    @FXML
    private void btnUnpaidTasks(ActionEvent event) {
        
       try {
           
        Platform platform = this.mainSceneUI.getMainApp().getPlatform();
        UserSession userSession = this.mainSceneUI.getUserSession();
        OrganizationRegister orgRegister = platform.getOrganizationRegister();
       
        Organization org = orgRegister.getOrganizationByUserEmail(userSession.getEmailUtilizador());
        
        this.listUnpaidTask = org.getUnpaidTaskList().getListTasks();
       
        if (listUnpaidTask != null || listUnpaidTask.isEmpty()){
            for(Task t : listUnpaidTask) {
            String s = String.format("%s", t.toString());
            stringSet.add(s);
        }
       
        observableList.setAll(stringSet);
        listView.setItems(observableList);
        btnSchedulePayments.setDisable(false);
        
        }else{
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("No results!");
            alert.setContentText("There is no tasks to be paid.");
            alert.showAndWait();
        }
           
        
        }
       catch (Exception e) {
        }
        
    }
    
    private void closeSchedulePaymentsUI(ActionEvent event) {
        this.stringSet.clear();
        txtTimeSelectorHour.clear();
        txtTimeSelectorMinute.clear();
        
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
}
