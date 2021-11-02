/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.pot.controller.CreateFreelancerController;
import pt.ipp.isep.dei.esoft.pot.model.Address;
import pt.ipp.isep.dei.esoft.pot.model.CountryUtils;
import pt.ipp.isep.dei.esoft.pot.model.FreelancerRegister;
import pt.ipp.isep.dei.esoft.pot.model.Platform;

/**
 * FXML Controller class
 *
 * @author
 */
public class CreateFreelancerUI implements Initializable {

    private MainSceneUI mainSceneUI;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnCreate;
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

    private CreateFreelancerController freelancerController;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        CountryUtils country = new CountryUtils();
        ObservableList<String> listCountries = country.getCoutryList();
        lstvwListCountries.setItems(listCountries);

    }

    public void associateParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
        freelancerController = new CreateFreelancerController();
    }

    @FXML
    private void btnDoCancel(ActionEvent event) {
        closeCreateFreelancerUI(event);
    }

    @FXML
    private void btnDoCreateFreelancer(ActionEvent event) {

        try {

            Platform platform = this.mainSceneUI.getMainApp().getPlatform();
            FreelancerRegister freelancerRegister = this.mainSceneUI.getMainApp().getPlatform().getFreelancerRegister();

            String country = lstvwListCountries.getSelectionModel().getSelectedItem().substring(0, 2).toUpperCase();

            Address address = new Address(txtStreet.getText(), txtPostalCode.getText(), txtLocality.getText(), country);

            String txtName = (txtFirstName.getText() + " " + txtLastName.getText());

            boolean add = freelancerController.newFreelancer(txtName, txtExpertise.getText(), txtEmail.getText(),
                    Integer.parseInt(txtNIF.getText()), txtIBAN.getText(), address);

            if (add) {
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, "Create Freelancer", "Success",
                        "Freelancer created successfully!").show();
                closeCreateFreelancerUI(event);
            } else {
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, "Create Freelancer", "Invalid Data",
                        "Please valid the data and add again!").show();
            }
        } catch (Exception e) {
        }
    }

    private void closeCreateFreelancerUI(ActionEvent event) {
        this.txtFirstName.clear();
        this.txtLastName.clear();
        this.txtExpertise.clear();
        this.txtEmail.clear();
        this.txtNIF.clear();
        this.txtIBAN.clear();
        this.txtStreet.clear();
        this.txtPostalCode.clear();
        this.txtLocality.clear();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

}
