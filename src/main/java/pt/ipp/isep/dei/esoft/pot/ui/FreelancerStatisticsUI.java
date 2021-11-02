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
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static org.apache.commons.math3.util.Precision.round;
import pt.ipp.isep.dei.esoft.autorizacao.model.UserSession;
import pt.ipp.isep.dei.esoft.pot.model.Freelancer;
import pt.ipp.isep.dei.esoft.pot.model.FreelancerStatistics;
import pt.ipp.isep.dei.esoft.pot.model.Organization;
import pt.ipp.isep.dei.esoft.pot.model.OrganizationRegister;
import pt.ipp.isep.dei.esoft.pot.model.Platform;
import pt.ipp.isep.dei.esoft.pot.model.Statistics;
import pt.ipp.isep.dei.esoft.pot.model.Transaction;
import pt.ipp.isep.dei.esoft.pot.model.TransactionList;

public class FreelancerStatisticsUI implements Initializable {

    private MainSceneUI mainSceneUI;
    private UserSession m_oSession;
    private Platform plat;
    private OrganizationRegister orgRegister;
    private Statistics stats;
    private Set<Freelancer> listFreelancers;
    private Freelancer selectedFreelancer = null;

    ObservableList observableList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<FreelancerStatistics, String> columnName;
    @FXML
    private TableColumn<FreelancerStatistics, Double> columnAverageDelay;
    @FXML
    private TableColumn<FreelancerStatistics, Double> columnStandardDeviation;

    @FXML
    private TextField txtAverageDelay;
    @FXML
    private TextField txtStandardDeviation;
    @FXML
    private TableView<FreelancerStatistics> tableFreelancer;
    @FXML
    private BarChart<?, ?> brchtGeneral;
    @FXML
    private BarChart<?, ?> brchtFreelancer;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private TextField txtProbability;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.stats = new Statistics();
        this.txtProbability.setText(String.format("%.4f", round(stats.probability(), 2)));
        tableFreelancer.getSelectionModel().setCellSelectionEnabled(true);
        final ObservableList selectedCells = tableFreelancer.getSelectionModel().getSelectedCells();
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                int index = tablePosition.getRow();
                selectedFreelancer = getFreelancer(index);
            }
        });
    }

    public Freelancer getFreelancer(int index) {
        int count = 0;
        Freelancer freelancer = null;
        for (Freelancer free : listFreelancers) {
            if (count == index) {
                freelancer = free;
            }
            count++;
        }
        return freelancer;
    }

    public void associateParentUI(MainSceneUI mainSceneUI) {
        this.mainSceneUI = mainSceneUI;
    }

    public void getInfo() {
        Platform platform = this.mainSceneUI.getMainApp().getPlatform();
        UserSession userSession = this.mainSceneUI.getUserSession();
        this.orgRegister = platform.getOrganizationRegister();

        txtAverageDelay.setText(String.format("%.2f", stats.delayMeanAllTrans(this.orgRegister.getOrganizationList())));
        txtStandardDeviation.setText(String.format("%.2f", stats.delayStandardDeviationAllTrans(this.orgRegister.getOrganizationList())));
        this.listFreelancers = platform.getFreelancerRegister().getFreelancerList();

        columnName.setCellValueFactory(new PropertyValueFactory<FreelancerStatistics, String>("name"));
        columnAverageDelay.setCellValueFactory(new PropertyValueFactory<FreelancerStatistics, Double>("averageDelay"));
        columnStandardDeviation.setCellValueFactory(new PropertyValueFactory<FreelancerStatistics, Double>("standardDeviation"));
        tableFreelancer.setItems(listOfFreelancerPerformance());

        // Histogram general
        setBarChartGeneral();

        tableFreelancer.getSelectionModel().setCellSelectionEnabled(true);
        final ObservableList selectedCells = tableFreelancer.getSelectionModel().getSelectedCells();
        selectedCells.addListener(new ListChangeListener() {

            @Override
            public void onChanged(ListChangeListener.Change c) {
                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                int index = tablePosition.getRow();
                selectedFreelancer = getFreelancer(index);
                // Histogram Freelancer
                brchtFreelancer.getData().clear();
                setBarChartFreelancer();
            }
        });

    }

    private void setBarChartGeneral() {

        Double standardDeviation = stats.delayStandardDeviationAllTrans(this.orgRegister.getOrganizationList());
        Double delayMean = stats.delayMeanAllTrans(this.orgRegister.getOrganizationList());

        Double interval1 = delayMean - standardDeviation;
        Double interval2 = delayMean + standardDeviation;
        interval1 = round(interval1, 2);
        interval2 = round(interval2, 2);

        int countInt1 = 0;
        int countInt2 = 0;
        int countInt3 = 0;

        List<Transaction> transListTemp = listOfAllTransactions();

        for (Transaction trans : transListTemp) {
            if (trans.getDelay() <= interval1) {
                countInt1++;
            } else {
                if (trans.getDelay() >= interval2) {
                    countInt3++;
                } else {
                    countInt2++;
                }
            }
        }

        XYChart.Series set1 = new XYChart.Series();
        set1.getData().add(new XYChart.Data("]-∞; " + interval1 + "]", countInt1));
        set1.getData().add(new XYChart.Data("]" + interval1 + "; " + interval2 + "[", countInt2));
        set1.getData().add(new XYChart.Data("[" + interval2 + "; +∞[", countInt3));
        brchtGeneral.getData().add(set1);
    }

    private void setBarChartFreelancer() {

        List<Transaction> transListTemp = listOfAllTransactions();
        List<Transaction> transListFreelancerTemp = new ArrayList();

        for (Transaction trans : transListTemp) {
            if (trans.getFreelancer().getName().equals(selectedFreelancer.getName())) {
                transListFreelancerTemp.add(trans);
            }
        }

        Double standardDeviation = stats.delayStandardDeviation(transListFreelancerTemp);
        Double delayMean = stats.delayMean(transListFreelancerTemp);

        Double interval1 = delayMean - standardDeviation;
        Double interval2 = delayMean + standardDeviation;
        interval1 = round(interval1, 2);
        interval2 = round(interval2, 2);

        int countInt1 = 0;
        int countInt2 = 0;
        int countInt3 = 0;

        for (Transaction trans : transListTemp) {
            if (trans.getFreelancer().getName().equals(selectedFreelancer.getName())) {
                if (trans.getDelay() <= interval1) {
                    countInt1++;
                } else {
                    if (trans.getDelay() >= interval2) {
                        countInt3++;
                    } else {
                        countInt2++;
                    }
                }
            }
        }

        XYChart.Series set2 = new XYChart.Series();
        set2.getData().add(new XYChart.Data("]-∞; " + interval1 + "]", countInt1));
        set2.getData().add(new XYChart.Data("]" + interval1 + "; " + interval2 + "[", countInt2));
        set2.getData().add(new XYChart.Data("[" + interval2 + "; +∞[", countInt3));
        brchtFreelancer.getData().add(set2);

    }

    private ObservableList listOfFreelancerPerformance() {
        List<FreelancerStatistics> temp = new ArrayList<>();
        for (Freelancer free : this.listFreelancers) {
            String freelancerEmail = free.getEmail();
            List<TransactionList> listFreeTrans = new ArrayList<>();
            for (Organization org : orgRegister.getOrganizationList()) {
                listFreeTrans.add(org.getListTransactionByFreelancer(freelancerEmail));
            }

            Double standarDeviation = stats.delayStandardDeviationAll(listFreeTrans);

            Double delayMean = stats.delayMeanAll(listFreeTrans);
            temp.add(new FreelancerStatistics(free.getName(), delayMean, standarDeviation));
        }

        observableList.setAll(temp);
        return observableList;
    }

    private List<Transaction> listOfAllTransactions() {

        List<Transaction> temp = new ArrayList<>();

        for (Organization org : orgRegister.getOrganizationList()) {
            for (Transaction trans : org.getM_transactionList().getTransList()) {
                temp.add(trans);
            }
        }

        return temp;
    }

    @FXML
    private void tableFreelancerDoSort(SortEvent<FreelancerStatistics> event) {
    }

}
