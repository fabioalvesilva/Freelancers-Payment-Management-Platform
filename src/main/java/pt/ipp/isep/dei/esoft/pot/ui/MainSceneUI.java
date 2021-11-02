/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.ui;

import pt.ipp.isep.dei.esoft.pot.controller.LoadTransactionController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.autorizacao.model.UserSession;
import pt.ipp.isep.dei.esoft.pot.controller.POTAplication;
import pt.ipp.isep.dei.esoft.pot.model.Constantes;
import pt.ipp.isep.dei.esoft.pot.model.Organization;
import pt.ipp.isep.dei.esoft.pot.model.OrganizationRegister;
import pt.ipp.isep.dei.esoft.pot.model.Platform;
import pt.ipp.isep.dei.esoft.pot.model.Transaction;
import pt.ipp.isep.dei.esoft.pot.ui.RegisterOrganizationUI;
import pt.ipp.isep.dei.esoft.pot.ui.SchedulePaymentsUI;

/**
 * FXML Controller class
 *
 * @author jpjes
 */
public class MainSceneUI implements Initializable {

    private MainApp mainApp;
    private UserSession m_oSession;
    private Stage registerOrganizationStage;
    private Stage schedulePaymentStage;
    private Stage createFreelancerStage;
    private Stage createTaskStage;
    private Stage createTransactionStage;
    private Stage showListTransactionStage;
    private Stage freelancerPerformanceIndicatorsScene;
    private Stage notifyFreelancer;
    private Stage freelancerPerformanceIndicatorsPaymentsScene;
    private Stage freelancerStatisticsScene;
    private Stage freelancerStatisticPaymentScene;

    @FXML
    private Label lblNameAplication;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtEmailLogin;
    @FXML
    private PasswordField txtPasswordLogin;
    @FXML
    private GridPane gridLogin;
    @FXML
    private GridPane gridAdministratorMenu;
    @FXML
    private GridPane gridCollaboratorMenu;
    @FXML
    private Button btnRegisterOrganization;
    @FXML
    private Button btnRegisterTask;
    @FXML
    private Button btnRegisterFreelancer;
    @FXML
    private Button btnRegisterTransaction;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnFreelancerPerformance;
    @FXML
    private Button btnLogout1;
    @FXML
    private Button btnLoadTransactions;
    @FXML
    private Button btnPerformanceIndicators;
    @FXML
    private GridPane gridManagerMenu;
    @FXML
    private Button btnLogout11;
    @FXML
    private Button btnSchedulerPayment;
    @FXML
    private ImageView logoApp;
    @FXML
    private Button btnNotifyFreelancers;
    @FXML
    private Button btnPerformanceIndicatorsPayments;
    @FXML
    private Button btnFreelancerPerformancePayments;
    @FXML
    private Button btnPaymentsPerformance;
    @FXML
    private Button btnDelaysPerformance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image logo = new Image("/img/logoApp.png");
        this.gridAdministratorMenu.setVisible(false);
        this.gridCollaboratorMenu.setVisible(false);
        this.gridManagerMenu.setVisible(false);
        this.gridLogin.setVisible(true);
        this.lblNameAplication.setText("LOGIN");
        this.logoApp.setImage(logo);
        this.lblNameAplication.setText("Login");

    }

    public void associateParentUI(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public MainApp getMainApp() {
        return this.mainApp;
    }

    public UserSession getUserSession() {
        return this.m_oSession;
    }

    @FXML
    private void btnDoLogin(ActionEvent event) {
        if (!this.txtEmailLogin.getText().equals("") && !this.txtPasswordLogin.equals("")) {
            this.m_oSession = this.mainApp.getPlatform().getAutorizationFacade().doLogin(this.txtEmailLogin.getText(), this.txtPasswordLogin.getText());
            if (this.m_oSession == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Login!");
                alert.setContentText("Please try again.");

                alert.showAndWait();
            } else if (this.m_oSession.isLoggedInComPapel(Constantes.ADMINISTRATOR_ROLE)) {

                this.gridAdministratorMenu.setVisible(true);
                this.gridCollaboratorMenu.setVisible(false);
                this.gridManagerMenu.setVisible(false);
                this.gridLogin.setVisible(false);
                this.lblNameAplication.setText("Administrator Menu");

            } else if (this.m_oSession.isLoggedInComPapel(Constantes.ORGANIZATION_COLLABORATOR_ROLE)) {

                this.gridLogin.setVisible(false);
                this.gridAdministratorMenu.setVisible(false);
                this.gridCollaboratorMenu.setVisible(true);
                this.gridManagerMenu.setVisible(false);
                this.lblNameAplication.setText("Collaborator Menu");

            } else if (this.m_oSession.isLoggedInComPapel(Constantes.ORGANIZATION_MANAGER_ROLE)) {

                this.gridLogin.setVisible(false);
                this.gridAdministratorMenu.setVisible(false);
                this.gridCollaboratorMenu.setVisible(false);
                this.gridManagerMenu.setVisible(true);
                this.lblNameAplication.setText("Manager Menu");
            }
        }
    }

    private void updateView(GridPane remove, GridPane show) {
        remove.setVisible(false);
        show.setVisible(true);
    }

    @FXML
    private void btnDoRegisterOrganization(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterOrganizationScene.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        registerOrganizationStage = new Stage();
        registerOrganizationStage.initModality(Modality.APPLICATION_MODAL);
        registerOrganizationStage.setTitle("Organization Register");
        registerOrganizationStage.setResizable(false);
        registerOrganizationStage.setScene(scene);

        RegisterOrganizationUI registerOrganizationUI = loader.getController();
        registerOrganizationUI.associateParentUI(this);
        registerOrganizationStage.show();

    }

    @FXML
    private void btnDoRegisterTask(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CreateTaskScene.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        createTaskStage = new Stage();
        createTaskStage.initModality(Modality.APPLICATION_MODAL);
        createTaskStage.setTitle("Create Task");
        createTaskStage.setResizable(false);
        createTaskStage.setScene(scene);

        CreateTaskUI createTaskUI = loader.getController();
        createTaskUI.associateParentUI(this);
        createTaskStage.show();
    }

    @FXML
    private void btnDoRegisterFreelancer(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CreateFreelancerScene.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        createFreelancerStage = new Stage();
        createFreelancerStage.initModality(Modality.APPLICATION_MODAL);
        createFreelancerStage.setTitle("Create Freelancer");
        createFreelancerStage.setResizable(false);
        createFreelancerStage.setScene(scene);

        CreateFreelancerUI CreateFreelancerUI = loader.getController();
        CreateFreelancerUI.associateParentUI(this);
        createFreelancerStage.show();

    }

    @FXML
    private void btnDoRegisterTransaction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CreateTransactionScene.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        createTransactionStage = new Stage();

        createTransactionStage.initModality(Modality.APPLICATION_MODAL);
        createTransactionStage.setTitle("Register Transaction");
        createTransactionStage.setResizable(false);
        createTransactionStage.setScene(scene);

        CreateTransactionUI createTransactionUI = loader.getController();
        createTransactionUI.associateParentUI(this);
        createTransactionStage.show();
    }

    @FXML
    private void btnDoLogout(ActionEvent event) {

        this.mainApp.getPlatform().getAutorizationFacade().doLogout();
        this.m_oSession = null;
        this.txtEmailLogin.clear();
        this.txtPasswordLogin.clear();
        this.gridAdministratorMenu.setVisible(false);
        this.gridCollaboratorMenu.setVisible(false);
        this.gridManagerMenu.setVisible(false);
        this.gridLogin.setVisible(true);
    }

    @FXML
    private void btnFreelancerPerformance(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FreelancerStatisticDelay.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        freelancerStatisticsScene = new Stage();

        freelancerStatisticsScene.initModality(Modality.APPLICATION_MODAL);
        freelancerStatisticsScene.setTitle("Freelancer Performance Indicators");
        freelancerStatisticsScene.setResizable(false);
        freelancerStatisticsScene.setScene(scene);

        FreelancerStatisticsUI freelancerStatisticsUI = loader.getController();
        freelancerStatisticsUI.associateParentUI(this);
        freelancerStatisticsUI.getInfo();
        freelancerStatisticsScene.show();
    }

    @FXML
    private void btnPerformanceIndicators(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FreelancerPerformanceIndicatorsScene.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        freelancerPerformanceIndicatorsScene = new Stage();

        freelancerPerformanceIndicatorsScene.initModality(Modality.APPLICATION_MODAL);
        freelancerPerformanceIndicatorsScene.setTitle("Freelancer Performance Indicators");
        freelancerPerformanceIndicatorsScene.setResizable(false);
        freelancerPerformanceIndicatorsScene.setScene(scene);

        FreelancerPerformanceIndicatorsUI freelancerPerformanceIndicatorsUI = loader.getController();
        freelancerPerformanceIndicatorsUI.associateParentUI(this);
        freelancerPerformanceIndicatorsUI.getInfo();
        freelancerPerformanceIndicatorsScene.show();

    }

    @FXML
    private void btnSchedulerPayment(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SchedulePaymentScene.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        schedulePaymentStage = new Stage();

        schedulePaymentStage.initModality(Modality.APPLICATION_MODAL);
        schedulePaymentStage.setTitle("Schedule Payment");
        schedulePaymentStage.setResizable(false);
        schedulePaymentStage.setScene(scene);

        SchedulePaymentsUI schedulerPaymentUI = loader.getController();
        schedulerPaymentUI.associateParentUI(this);
        schedulePaymentStage.show();

    }

    @FXML
    private void btnDoLoadTransactions(ActionEvent event) throws IOException, ParseException {
        FileChooser flChooser = LoadTransactionController.createFileChooserTransaction();
        File file = flChooser.showOpenDialog(btnLoadTransactions.getScene().getWindow());

        if (file != null) {
            Platform platform = mainApp.getPlatform();
            OrganizationRegister orgRegister = platform.getOrganizationRegister();
            Organization org = orgRegister.getOrganizationByUserEmail(this.m_oSession.getEmailUtilizador());
            List<Transaction> result = org.getM_transactionList().read(file);

            if (result.size() > 0) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ShowListTransactionScene.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                showListTransactionStage = new Stage();
                showListTransactionStage.initModality(Modality.APPLICATION_MODAL);
                showListTransactionStage.setTitle("Create Task Transaction");
                showListTransactionStage.setResizable(false);
                showListTransactionStage.setScene(scene);

                ShowListTransactionUI showListTransactionUI = loader.getController();
                showListTransactionUI.associateParentUI(this);
                showListTransactionUI.setListTransaction(result);
                showListTransactionStage.show();
            } else {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITLE, "Import file with transactions",
                        "No transactions imported!").show();
            }
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITLE, "Import file with transactions",
                    "You didn't choose any file!").show();
        }

    }

    @FXML
    private void btnNotifyFreelancers(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NotifyFreelancer.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        notifyFreelancer = new Stage();

        notifyFreelancer.initModality(Modality.APPLICATION_MODAL);
        notifyFreelancer.setTitle("Notify Freelancer");
        notifyFreelancer.setResizable(false);
        notifyFreelancer.setScene(scene);

        NotifyFreelancerUI notifyFreelancerUI = loader.getController();
        notifyFreelancerUI.associateParentUI(this);
        notifyFreelancer.show();

    }

    @FXML
    private void btnDoPerformanceIndicatorsPayments(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FreelancerPerformanceIndicatorsPayments.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        freelancerPerformanceIndicatorsPaymentsScene = new Stage();

        freelancerPerformanceIndicatorsPaymentsScene.initModality(Modality.APPLICATION_MODAL);
        freelancerPerformanceIndicatorsPaymentsScene.setTitle("Freelancer Performance Indicators");
        freelancerPerformanceIndicatorsPaymentsScene.setResizable(false);
        freelancerPerformanceIndicatorsPaymentsScene.setScene(scene);

        FreelancerPerformanceIndicatorsPaymentsUI freelancerPerformanceIndicatorsPaymentsUI = loader.getController();
        freelancerPerformanceIndicatorsPaymentsUI.associateParentUI(this);
        freelancerPerformanceIndicatorsPaymentsUI.getInfoPayments();
        freelancerPerformanceIndicatorsPaymentsScene.show();
    }

    @FXML
    private void btnDoFreelancerPerformancePayments(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FreelancerStatisticPayment.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        freelancerStatisticPaymentScene = new Stage();

        freelancerStatisticPaymentScene.initModality(Modality.APPLICATION_MODAL);
        freelancerStatisticPaymentScene.setTitle("Freelancer Performance Indicators");
        freelancerStatisticPaymentScene.setResizable(false);
        freelancerStatisticPaymentScene.setScene(scene);

        FreelancerStatisticsPaymentsUI freelancerStatisticsPaymentsUI = loader.getController();
        freelancerStatisticsPaymentsUI.associateParentUI(this);
        freelancerStatisticsPaymentsUI.getInfo();
        freelancerStatisticPaymentScene.show();
    }


}
