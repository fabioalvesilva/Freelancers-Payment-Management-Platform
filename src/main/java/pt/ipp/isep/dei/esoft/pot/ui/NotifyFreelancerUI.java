/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.ui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import pt.ipp.isep.dei.esoft.autorizacao.model.UserSession;
import pt.ipp.isep.dei.esoft.pot.model.NotifyFreelancerDelayed;
import pt.ipp.isep.dei.esoft.pot.model.Organization;
import pt.ipp.isep.dei.esoft.pot.model.OrganizationRegister;
import pt.ipp.isep.dei.esoft.pot.model.Platform;
import pt.ipp.isep.dei.esoft.pot.model.Scheduler;

/**
 * FXML Controller class
 *
 * @author Fábio Silva
 */
public class NotifyFreelancerUI implements Initializable {

    
    private MainSceneUI mainSceneUI;
    private UserSession m_oSession;
    private LocalDate dateNow;

    @FXML
    private Button btnSchedule;
    @FXML
    private Button btnNotfyNow;
    @FXML
    private DatePicker dataSelector;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dateNow = LocalDate.now();
    }    
    
    public void associateParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
    }

    @FXML
    private void btnDoSchedule(ActionEvent event) {
        
        try {
            
        Platform platform = this.mainSceneUI.getMainApp().getPlatform();
        UserSession userSession = this.mainSceneUI.getUserSession();
        OrganizationRegister orgRegister = platform.getOrganizationRegister();
      //  NotifyFreelancerDelayed not = new NotifyFreelancerDelayed();


        LocalDate date = dataSelector.getValue();
        
        if (date.isBefore(dateNow) || date.getDayOfMonth() > 28){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Date!");
            alert.setContentText("The date introduced must be after than " + dateNow.toString() + " and before 28th day." + "\nPlease introduce a new date.");
            
            alert.showAndWait();
        }else{
            
            Scheduler scheduler = new Scheduler (date.toString());
            
            
            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, "Notification Scheduler", "Success",
                                    "A notification will be sent on " + date.toString()).show();
           
        }
        
        
           
        }
       catch (Exception e) {
        }
        
    }

    @FXML
    private void btnDoNotifyNow(ActionEvent event) {
        
       try {
           NotifyFreelancerDelayed notifyNow = new NotifyFreelancerDelayed();
           boolean notify = notifyNow.executeNotification();
           
           if (notify){
               AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, "Notification Scheduler", "Success",
                                    "A notification sent!");
           }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Something happened!");
            alert.setContentText("Please try again.");
            
            alert.showAndWait();
           }
       }
       catch (Exception e){
           
       }
        
    }
    
   
    
}
