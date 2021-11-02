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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipp.isep.dei.esoft.autorizacao.model.UserSession;
import pt.ipp.isep.dei.esoft.pot.model.Freelancer;
import pt.ipp.isep.dei.esoft.pot.model.FreelancerStatistics;
import pt.ipp.isep.dei.esoft.pot.model.Organization;
import pt.ipp.isep.dei.esoft.pot.model.OrganizationRegister;
import pt.ipp.isep.dei.esoft.pot.model.Platform;
import pt.ipp.isep.dei.esoft.pot.model.Statistics;
import pt.ipp.isep.dei.esoft.pot.model.TransactionList;

/**
 *
 * @author
 */
public class FreelancerPerformanceIndicatorsPaymentsUI implements Initializable {

    private MainSceneUI mainSceneUI;
    private UserSession m_oSession;
    private Organization org;
    private Statistics stats;
    private Set<Freelancer> listFreelancers;
    ObservableList observableList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<FreelancerStatistics, String> columnName;
    @FXML
    private TableColumn<FreelancerStatistics, Double> columnAveragePayment;
    @FXML
    private TableColumn<FreelancerStatistics, Double> columnStandardDeviation;

    @FXML
    private TextField txtAveragePayment;
    @FXML
    private TextField txtStandardDeviationPayment;
    @FXML
    private TableView<FreelancerStatistics> tableFreelancerPayment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void associateParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
    }

    public void getInfoPayments() {
        Platform platform = this.mainSceneUI.getMainApp().getPlatform();
        UserSession userSession = this.mainSceneUI.getUserSession();
        OrganizationRegister orgRegister = platform.getOrganizationRegister();

        this.org = orgRegister.getOrganizationByUserEmail(userSession.getEmailUtilizador());
        this.stats = new Statistics();

        txtAveragePayment.setText(String.format("%.2f", stats.paymentMean(org.getPaidTransactionList().getTransList())));
        txtStandardDeviationPayment.setText(String.format("%.2f", stats.paymentStandardDeviation(org.getPaidTransactionList().getTransList())));
        this.listFreelancers = platform.getFreelancerRegister().getFreelancerList();

        columnName.setCellValueFactory(new PropertyValueFactory<FreelancerStatistics, String>("name"));
        columnAveragePayment.setCellValueFactory(new PropertyValueFactory<FreelancerStatistics, Double>("averageDelay"));
        columnStandardDeviation.setCellValueFactory(new PropertyValueFactory<FreelancerStatistics, Double>("standardDeviation"));
        tableFreelancerPayment.setItems(listOfFreelancerPerformancePayments());
    }

    private ObservableList listOfFreelancerPerformancePayments() {
        List<FreelancerStatistics> temp = new ArrayList<>();
        for (Freelancer free : this.listFreelancers) {
            String freelancerEmail = free.getEmail();

            TransactionList freeTrans = org.getPaidListTransactionByFreelancer(freelancerEmail);
            if (freeTrans.getTransList().size() >= 1) {
                Double standarDeviation = stats.paymentStandardDeviation(freeTrans.getTransList());

                Double average = stats.paymentMean(freeTrans.getTransList());
                temp.add(new FreelancerStatistics(free.getName(), average, standarDeviation));
            }
        }

        observableList.setAll(temp);
        return observableList;
    }

    @FXML
    private void tableFreelancerDoSort(SortEvent<FreelancerStatistics> event) {
    }
}
