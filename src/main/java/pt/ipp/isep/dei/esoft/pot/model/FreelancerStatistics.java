/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jpjes
 */
public class FreelancerStatistics {
    private final SimpleStringProperty   name;
    private final SimpleDoubleProperty  averageDelay;
    private final SimpleDoubleProperty  standardDeviation;

    public FreelancerStatistics(String name, double averageDelay, double standardDeviation) {
        this.name = new SimpleStringProperty(name);
        this.averageDelay = new SimpleDoubleProperty(averageDelay);
        this.standardDeviation = new SimpleDoubleProperty(standardDeviation);
    }
    
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getAverageDelay() {
        return averageDelay.get();
    }

    public void setAverageDelay(double averageDelay) {
        this.averageDelay.set(averageDelay);
    }

    public double getStandardDeviation() {
        return standardDeviation.get();
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation.set(standardDeviation);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }
    
    public SimpleDoubleProperty averageDelayProperty() {
        return averageDelay;
    }
    
    public SimpleDoubleProperty standardDeviationProperty() {
        return standardDeviation;
    }
}
