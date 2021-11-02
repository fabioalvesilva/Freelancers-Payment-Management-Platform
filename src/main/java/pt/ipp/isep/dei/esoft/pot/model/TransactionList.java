/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.File;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import pt.ipp.isep.dei.esoft.utils.Utils;

/**
 *
 * @author Fábio Silva
 */
public class TransactionList implements Serializable {
    
    private List<Transaction> transList;
    
    public TransactionList (){
        transList = new ArrayList<>();
    }
    
    public boolean addTransaction(int id, Payment payment, Task task) {
        if (payment == null || task == null){
            throw new IllegalArgumentException("Payment or Task are null!");
        }
        
        Transaction transaction = new Transaction (id, payment, task);
        return transList.contains(transaction) ? false
                : transList.add(transaction);
    }
    
    public boolean addTransaction(Transaction transaction) {
        return transList.contains(transaction) ? false
                : transList.add(transaction);
    }
    
    public Transaction createTransaction(int id,Date endDate,int delay,String desckWork,Task task,Freelancer freelancer) {
        return new Transaction (id,endDate,delay,desckWork, freelancer,task);
    }

    public void setTransList(List<Transaction> transList) {
        this.transList = transList;
    }
    
    public List<Transaction> getTransList() {
        return transList;
    }
    
    public List<Transaction> read(File file) throws ParseException {
        Scanner sc = Utils.getFile(file);
        List<Transaction> tempList = new ArrayList<Transaction>();
        if (sc.hasNextLine()) {
            // Ignorar primeira linha
            sc.nextLine();

            //Preencher a matriz
            while (sc.hasNextLine()) {
                String readLine = sc.nextLine();
                String[] leituraSplit = readLine.split(Constantes.CARACTER_SPLIT);
                Task task = new Task (leituraSplit[1], leituraSplit[2], Double.parseDouble(leituraSplit[3]),
                Double.parseDouble(leituraSplit[4]), new Category(leituraSplit[5]));
                Address address = new Address(leituraSplit[15],leituraSplit[16]);
                Freelancer freelancer = new Freelancer(leituraSplit[9],leituraSplit[10],leituraSplit[11],leituraSplit[12],Integer.parseInt(leituraSplit[13]),leituraSplit[14],address);
                String[] dateSplit = leituraSplit[6].split("/");
                Transaction transaction = new Transaction(Integer.parseInt(leituraSplit[0]),new Date(Integer.parseInt(dateSplit[2]),Integer.parseInt(dateSplit[1]),Integer.parseInt(dateSplit[0])),Integer.parseInt(leituraSplit[7]),leituraSplit[8],freelancer,task);
                tempList.add(transaction);
            }
        }
        sc.close();
        return tempList; 
    }

    public void setTransaction(Transaction transaction) {
        if(!transList.contains(transaction))
            transList.add(transaction);
    }
}
