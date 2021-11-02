/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipp.isep.dei.esoft.pot.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Fábio Silva
 */
public class Transaction implements Serializable {
    
    private int id;
    private Date endDate;
    private int delay;
    private String descWork;
    private Payment payment;
    private Task task;
    private Freelancer freelancer;
    
    public Transaction (Task task) {
        setTask(task);
    } 
    
    public Transaction (int id, Payment payment, Task task){
        setId(id);
        setPayment(payment);
        setTask(task);
    }
    
    public Transaction (int id,Date endDate,int delay,String descWork, Freelancer freelancer, Task task){
        this.id = id;
        this.delay = delay;
        this.endDate = endDate;
        this.descWork = descWork;
        this.freelancer = freelancer;
        this.task = task;
        this.payment = new Payment();
    }
    
    public int getId() {
        return id;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public int getDelay() {
        return delay;
    }
    
    public String getDescWork() {
        return descWork;
    }

    public Payment getPayment() {
        return payment;
    }

    public Task getTask() {
        return task;
    }
    
    public Freelancer getFreelancer() {
        return freelancer;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
    
    public void setDescWork(String descWork) {
        this.descWork = descWork;
    }

    public void setPayment(Payment payment) {
        if (payment == null){
            throw new IllegalArgumentException ("Invalid Payment!");
        }
        this.payment = payment;
    }

    public void setTask(Task task) {
        if (task == null){
            throw new IllegalArgumentException("Invalid Task!");
        }
        this.task = task;
    }
    
    public void setFreelancer(Freelancer free) {
        if (freelancer == null){
            throw new IllegalArgumentException("Invalid Freelancer!");
        }
        this.freelancer = free;
    }

    //todo Added RM - > Get only Payments done
    public boolean getPaymentStatus() {
        return  this.payment.getPaidStatus();
    }

    public boolean hasPayment(){
        if(this.getPayment() != null && this.getPaymentStatus() == true){
            return true;
        }
        return false;
    }



    @Override
    public String toString() {
        return String.format("%d - %s -%s - %s", id, freelancer, payment, task);
    }

    public double amountToPaid() {
        double amount = 0;
        amount = this.task.getAssignDuration() * this.task.getCoustPerHour();
        
        if(this.freelancer.getExpertise().equalsIgnoreCase("Senior"))
            amount = amount * 2;
            
        return amount;
    }
    
}
