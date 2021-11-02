 /*
 * To change this license header, choose License Headers in Project Properties.    
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.ui;

import java.util.Random;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//UC5 - Histogram to analyze the delays of each one and all the freelancers working to the organization
//UC9 - Histograms to analyze the delays and payments of each one and all the freelancers that exist in the system


// The histograms presented in this application should have three levels/intervals: ]-∞,μ-σ],
// ]μ-σ,μ+σ[ and [μ+σ, +∞[. Here μ represents the sample mean and σ is the standard deviation.



public class HistogramUI {
    
    
    int DATA_SIZE = 1000;
    int data[] = new int[DATA_SIZE];
    int group[] = new int[10];
    @FXML
    private TextField txtAverageDelay;
    @FXML
    private TextField txtStandardDeviation;
    @FXML
    private TableView<?> tableFreelancer;
    @FXML
    private TableColumn<?, ?> columnName;
    @FXML
    private TableColumn<?, ?> columnAverageDelay;
    @FXML
    private TableColumn<?, ?> columnStandardDeviation;
    
    
    public void start(Stage primaryStage) {
        
        prepareData();
        groupData();
        
        Label labelInfo = new Label();
        labelInfo.setText(
            "java.version: " + System.getProperty("java.version") + "\n" +
            "javafx.runtime.version: " + System.getProperty("javafx.runtime.version")
        );
        
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> barChart = 
            new BarChart<>(xAxis,yAxis);
        barChart.setCategoryGap(0);
        barChart.setBarGap(0);
        
        xAxis.setLabel("Range");       
        yAxis.setLabel("Population");
        
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Histogram");       
        series1.getData().add(new XYChart.Data("0-10", group[0]));
        series1.getData().add(new XYChart.Data("10-20", group[1]));
        series1.getData().add(new XYChart.Data("20-30", group[2]));
        series1.getData().add(new XYChart.Data("30-40", group[3]));
        series1.getData().add(new XYChart.Data("40-50", group[4])); 
        
        series1.getData().add(new XYChart.Data("50-60", group[5]));
        series1.getData().add(new XYChart.Data("60-70", group[6]));
        series1.getData().add(new XYChart.Data("70-80", group[7]));
        series1.getData().add(new XYChart.Data("80-90", group[8]));
        series1.getData().add(new XYChart.Data("90-100", group[9]));
        
        barChart.getData().addAll(series1);
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(labelInfo, barChart);
        
        StackPane root = new StackPane();
        root.getChildren().add(vBox);
        
        Scene scene = new Scene(root, 800, 450);
        
        primaryStage.setTitle("java-buddy.blogspot.com");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
//    
    //generate dummy random data
    private void prepareData(){

        Random random = new Random();
        for(int i=0; i<DATA_SIZE; i++){
            data[i] = random.nextInt(100);
        }
    }
    
    //count data population in groups
    private void groupData(){
        for(int i=0; i<10; i++){
            group[i]=0;
        }
        for(int i=0; i<DATA_SIZE; i++){
            if(data[i]<=10){
                group[0]++;
            }else if(data[i]<=20){
                group[1]++;
            }else if(data[i]<=30){
                group[2]++;
            }else if(data[i]<=40){
                group[3]++;
            }else if(data[i]<=50){
                group[4]++;
            }else if(data[i]<=60){
                group[5]++;
            }else if(data[i]<=70){
                group[6]++;
            }else if(data[i]<=80){
                group[7]++;
            }else if(data[i]<=90){
                group[8]++;
            }else if(data[i]<=100){
                group[9]++;
            }
        }
    }

   
}
