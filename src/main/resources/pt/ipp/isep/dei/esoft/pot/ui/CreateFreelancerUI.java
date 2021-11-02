/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Joaquim
 */
public class CreateFreelancerUI implements Initializable {

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnCreate;
    @FXML
    private ScrollPane scrlCountryList;
    @FXML
    private TextField txtName;
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
    private TextField txtCountryCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnDoCancel(ActionEvent event) {
    }

    @FXML
    private void btnDoCreateFreelancer(ActionEvent event) {
    }
    
}
