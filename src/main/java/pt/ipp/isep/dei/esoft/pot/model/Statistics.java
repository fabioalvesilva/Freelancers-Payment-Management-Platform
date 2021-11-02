package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.lang.Object;
import java.util.Set;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static org.apache.commons.math3.util.Precision.round;

public class Statistics implements Serializable {

    /**
     * Method that estimates the delays's mean of the organization's
     * freelancers.
     *
     * @param transactionList, the list of trasactions of the organization
     * @return the freelancer's mean of the delay
     */
    public double delayMean(List<Transaction> transactionList) {
        double sum = 0;
        for (Transaction trans : transactionList) {
            int delay = trans.getDelay();
            sum = sum + (double) delay;
        }
        if (!transactionList.isEmpty()) {
            return round(sum / transactionList.size(), 2);
        }
        return 0;
    }

    /**
     * Method that estimates the standard deviation of delays of a list of
     * transactions.
     *
     * @param transactionList, the list of stands
     * @return the standard deviation
     */
    public double delayStandardDeviation(List<Transaction> transactionList) {
        double mean = delayMean(transactionList);
        double sum = 0;
        for (Transaction trans : transactionList) {
            int delay = trans.getDelay();
            sum = sum + Math.pow(Math.abs((double) delay - mean), 2);
        }
        if (!transactionList.isEmpty()) {
            return round(Math.sqrt(sum / transactionList.size()), 2);
        }
        return 0;
    }

    public Double delayMeanAll(List<TransactionList> listFreeTrans) {
        double sum = 0;
        for (TransactionList trans : listFreeTrans) {
            sum += delayMean(trans.getTransList());
        }
        return round(sum, 2);
    }

    public Double delayStandardDeviationAll(List<TransactionList> listFreeTrans) {
        double sum = 0;
        for (TransactionList trans : listFreeTrans) {
            sum += delayStandardDeviation(trans.getTransList());
        }
        return round(sum, 2);
    }

    public Double delayMeanAllTrans(Set<Organization> organizationList) {
        double sum = 0;
        for (Organization org : organizationList) {
            sum += delayMean(org.getM_transactionList().getTransList());
        }
        return round(sum, 2);
    }

    public Double delayStandardDeviationAllTrans(Set<Organization> organizationList) {
        double sum = 0;
        for (Organization org : organizationList) {
            sum += delayStandardDeviation(org.getM_transactionList().getTransList());
        }
        return round(sum, 2);
    }

    /**
     * Method that estimates the payment's mean of the organization's
     * freelancers.
     *
     * @param transactionList, the list of trasactions of the organization
     * @return the freelancer's mean of the delay
     */
    public double paymentMean(List<Transaction> transactionList) {
        double sum = 0;
        for (Transaction trans : transactionList) {
            double payment = trans.getPayment().getValue();
            sum = sum + payment;
        }
        if (!transactionList.isEmpty()) {
            return round(sum / transactionList.size(), 2);
        }
        return 0;
    }

    /**
     * Method that estimates the standard deviation of delays of a list of
     * transactions.
     *
     * @param transactionList, the list of transaction list
     * @return the standard deviation
     */
    public double paymentStandardDeviation(List<Transaction> transactionList) {
        double mean = paymentMean(transactionList);
        double sum = 0;
        for (Transaction trans : transactionList) {
            double payment = trans.getPayment().getValue();
            sum = sum + Math.pow(Math.abs(payment - mean), 2);
        }
        if (!transactionList.isEmpty()) {
            return round(Math.sqrt(sum / transactionList.size()), 2);
        }
        return 0f;
    }

    public Double paymentStandardDeviationAll(List<TransactionList> listFreeTrans) {
        double sum = 0;
        for (TransactionList trans : listFreeTrans) {
            sum += paymentStandardDeviation(trans.getTransList());
        }
        return round(sum, 2);
    }

    public Double paymentMeanAll(List<TransactionList> listFreeTrans) {
        double sum = 0;
        for (TransactionList trans : listFreeTrans) {
            sum += paymentMean(trans.getTransList());
        }
        return round(sum, 2);
    }

    public double paymentMeanAllTrans(Set<Organization> organizationList) {
        double sum = 0;
        for (Organization org : organizationList) {
            sum += paymentMean(org.getM_transactionList().getTransList());
        }
        return round(sum, 2);
    }

    public double paymentStandardDeviationAllTrans(Set<Organization> organizationList) {
        double sum = 0;
        for (Organization org : organizationList) {
            sum += paymentStandardDeviation(org.getM_transactionList().getTransList());
        }
        return round(sum, 2);
    }

//--------------------------------------------------------------------------------------------      
//UC9 - Probability
    public double probability() {
        double mean = 2;
        double standardDeviation = 1.5;

        NormalDistribution normalD = new NormalDistribution(mean, standardDeviation);

        double prob = (1 - normalD.cumulativeProbability(3.0));

        return round(prob, 2);
    }

//--------------------------------------------------------------------------------------------      
//UC9 - Histogram
}
