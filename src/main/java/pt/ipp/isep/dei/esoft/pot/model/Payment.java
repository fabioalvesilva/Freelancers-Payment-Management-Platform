/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Fábio Silva
 */
public class
Payment implements Serializable {
    
    private int id;
    private int invoiceId;
    private double value;
    private Currency currency;
    private String paidDate;

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    private Calendar calendar = Calendar.getInstance();
    
    public Payment (int id, int invoice, double value, Currency currency){
        
        setId(id);
        setInvoiceId(invoice);
        setValue(value);
        setCurrency(currency);
        this.paidDate = "";
        this.calendar = Calendar.getInstance();
        
    }

    Payment() {
        this.paidDate = "";
    }

    public void setId(int id) {
        this.id = id;
    }

    /*
    public void setInvoice( invoice) {
        if(invoice == null){
            throw new IllegalArgumentException("Invalid Invoice!");
        }
        this.invoice = invoice;
    }

     */

    public void setValue(double value) {
        this.value = value;
    }

    public void setCurrency(Currency currency) {
        if(currency == null){
            throw new IllegalArgumentException("Invalid Currency!");
        }
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public int getInvoice() {
        return invoiceId;
    }

    public double getValue() {
        return value;
    }


    public Currency getCurrency() {
        return currency;
    }


    public void setInvoiceId(int invoice) {
        this.invoiceId = invoice;
        this.setPaidDate();
;
    }
    private void setPaidDate(){
        Calendar calendar = Calendar.getInstance();
        this.paidDate =  formatter.format(calendar.getTime());
    }

    public String getPaidDate() {
        return paidDate;
    }

    //todo RM added to make Payments Process
    public boolean getPaidStatus(){
        return  !(this.paidDate.isEmpty());
    }
    public boolean isPaidThisYear(){

        String thisYear = sdf.format(new Date());
        return this.getPaidDate().equals(thisYear);

    }
}
