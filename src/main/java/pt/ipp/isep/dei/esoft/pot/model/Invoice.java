/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import pt.ipp.isep.dei.esoft.pot.controller.POTAplication;
import pt.ipp.isep.dei.esoft.pot.model.CurrencySystem.CurrencyConverter;

/**
 *
 * @author Fábio Silva
 */
public class Invoice implements Serializable {
    public String getInvoiceBody;

    /*
        inv.getInvocieContact(), inv.getInvoiceTitle(), inv.getInvoiceBody)
         */
    //Map with task ID and Payment

    private Map<Task, Payment> listOfTasks;
    private double totalToBePaid;
    private Freelancer fre;
    private int id;
    private static int counter;
    private String date;
    final CurrencyConverter converter = POTAplication.getInstance().getPlatform().getM_currency();
    // private CurrencyConverter m_Currency = AplicacaoPOT.getInstance().getPlataforma();


    public Invoice( Freelancer fre) {
        this.fre = fre;
        this.listOfTasks =  new HashMap<>();
        this.totalToBePaid = 0;
        incrementCounter();
        this.id = counter;

        this.date = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());;
    }

    private void setTotalToBePaid(){
        //Updates amount to be paid
        double total = 0;
        for (Map.Entry<Task, Payment> entry : listOfTasks.entrySet()) {
            total = total + getAmountToBePaidByTask(entry.getValue());
        }
        this.totalToBePaid = total;
    }


    public double getAmountToBePaidByTask(Payment  p) {
        return p.getValue();
    }

    public void addNewEntry(Task t, Payment p){
        this.listOfTasks.put(t,p);
        p.setInvoiceId(this.getId());
        setTotalToBePaid();
    }

    public int getId(){
        return this.id;
    }

    public static void incrementCounter(){
        counter = counter + 1;

    }

    @Override
    public String toString() {
        return "Invoice{" +
                "listOfTasks=" + listOfTasks +
                ", totalToBePaid=" + totalToBePaid +
                ", fre=" + fre +
                ", id=" + id +
                ", date='" + date + '\'' +
                '}';
    }


    public String  toInvoiceToEmailFormat() {
       String  email;
        //EmailBody Construction
        List<String> entries = new ArrayList<>();

        for (Map.Entry<Task, Payment> entry : listOfTasks.entrySet()) {

            String taskid= entry.getKey().getId();
            String amount = String.valueOf(entry.getValue().getValue());
          //  entries.add(String.format("%s \t %d \t %s ", entry.getKey().getId(), entry.getValue().getValue(), converter.convertCurrency(entry.getValue().getValue(),this.fre.getCountryCode())));
             entries.add(""+entry.getKey().getId() +""+entry.getValue().getValue()  +"" + converter.convertCurrency(entry.getValue().getValue(),this.fre.getCountryCode()));
        }
        email = entries.toString();
        email = email + "\n" + "Total: " + this.totalToBePaid + "" + converter.convertCurrency(this.totalToBePaid,this.fre.getCountryCode());
        //String s = email.toString();
        return email;
    }

    public String getInvoiceContact() {
        return this.fre.getEmail();

    }

    public String getInvoiceTitle() {
        return "Invoice : " + getId();
    }
}
