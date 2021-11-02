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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.autorizacao.model.UserSession;
import pt.ipp.isep.dei.esoft.pot.controller.CreateTaskController;
import pt.ipp.isep.dei.esoft.pot.model.Category;
import pt.ipp.isep.dei.esoft.pot.model.Organization;
import pt.ipp.isep.dei.esoft.pot.model.OrganizationRegister;
import pt.ipp.isep.dei.esoft.pot.model.Platform;

/**
 * FXML Controller class
 *
 * @author
 */
public class CreateTaskUI implements Initializable {

    @FXML
    private Button btnTask;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtAssignDuration;
    @FXML
    private TextField txtCoustPerHour;
    private MainSceneUI mainSceneUI;
    @FXML
    private TextField txtCategory;
    @FXML
    private TextField txtId;

    private CreateTaskController taskController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void associateParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
        taskController = new CreateTaskController();
    }

    @FXML
    private void btnDoCreateTask(ActionEvent event) {

        Platform platform = this.mainSceneUI.getMainApp().getPlatform();
        UserSession userSession = this.mainSceneUI.getUserSession();
        OrganizationRegister orgRegister = platform.getOrganizationRegister();
        Organization org = orgRegister.getOrganizationByUserEmail(userSession.getEmailUtilizador());
        Category category = new Category(txtCategory.getText());

        String id = txtId.getText();
        String desc = txtDescription.getText();
        double duration = Double.parseDouble(txtAssignDuration.getText());
        double coust = Double.parseDouble(txtCoustPerHour.getText());

        if (taskController.newTask(id, desc, duration, coust, category)) {

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, "Create Task", "Success",
                    "Task created successfully!").show();
            closeCreateFreelancerUI(event);
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, "Task Register", "Invalid Task",
                    "Please valid the data and add again!").show();
        }
    }

    @FXML
    private void btnDoCancel(ActionEvent event) {
        closeCreateFreelancerUI(event);
    }

    private void closeCreateFreelancerUI(ActionEvent event) {
        this.txtId.clear();
        this.txtDescription.clear();
        this.txtAssignDuration.clear();
        this.txtCoustPerHour.clear();
        this.txtCategory.clear();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

}
