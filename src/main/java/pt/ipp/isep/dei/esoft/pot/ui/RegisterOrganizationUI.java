/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.pot.model.Collaborator;
import pt.ipp.isep.dei.esoft.pot.model.Organization;
import pt.ipp.isep.dei.esoft.pot.model.Platform;
import pt.ipp.isep.dei.esoft.pot.model.Transaction;
import static pt.ipp.isep.dei.esoft.pot.ui.MainApp.APLICATION_TITLE;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * FXML Controller class
 *
 * @author jpjes
 */
public class RegisterOrganizationUI implements Initializable {
    private MainSceneUI mainSceneUI;
    EmailValidator emailValidator;
    
    @FXML
    private Button btnAddOrganization;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtOrganizationName;
    @FXML
    private TextField txtVAT;
    @FXML
    private TextField txtManagerName;
    @FXML
    private TextField txtManagerEmail;
    @FXML
    private TextField txtCollaboratorName;
    @FXML
    private TextField txtCollaboratorEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void associateParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
	emailValidator = EmailValidator.getInstance();
    }

    @FXML
    private void btnDoAddOrganization(ActionEvent event) {
        try {
            if (emailValidator.isValid(txtManagerEmail.getText()) &&  emailValidator.isValid(txtCollaboratorEmail.getText())){
                
            
            Platform platform = this.mainSceneUI.getMainApp().getPlatform();
            Collaborator theManager = Organization.newCollaborator(txtManagerName.getText(), txtManagerEmail.getText());
            Collaborator theColab = Organization.newCollaborator(txtCollaboratorName.getText(), txtCollaboratorEmail.getText());  
            boolean add = platform.getOrganizationRegister().organizationRegister(new Organization(txtOrganizationName.getText(),txtVAT.getText(),theManager,theColab));
            if(add) {
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, "Organization Register", "Success",
                                    "Organization created with sucess!").show();
                closeRegisterOrganizationUI(event);
            } else {
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, "Organization Register", "Invalid Data",
                                    "Please valid the data and add again!").show();
            }
            }else{
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, "Organization Register", "Email Invalid.",
                                    "Please introduce a valid Email.").show();
            }
         
        } catch (Exception e) {
        }
    }

    @FXML
    private void btnDoCancel(ActionEvent event) {
        closeRegisterOrganizationUI(event);
    }
    
    private void closeRegisterOrganizationUI(ActionEvent event) {
        this.txtOrganizationName.clear();
        this.txtVAT.clear();
        this.txtManagerName.clear();
        this.txtManagerEmail.clear();
        this.txtCollaboratorName.clear();
        this.txtCollaboratorEmail.clear();
        
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
}
