package pt.ipp.isep.dei.esoft.pot.model;


import pt.ipp.isep.dei.esoft.pot.controller.POTAplication;

import pt.ipp.isep.dei.esoft.pot.model.CurrencySystem.CurrencyConverter;
import pt.ipp.isep.dei.esoft.pot.model.EmailAPI.EmailSystem;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MakePayments implements Serializable {
    final pt.ipp.isep.dei.esoft.utils.UtilFileWriter writer = new pt.ipp.isep.dei.esoft.utils.UtilFileWriter();
    final static CurrencyConverter converter = POTAplication.getInstance().getPlatform().getM_currency();

    private Organization org;

    private InvoiceRegistry invoiceList;

    private boolean status ;

    private ArrayList<String> paymentRegistry ;

    private String  executionDate;




    public MakePayments(Organization org){

        this.org = org;
        invoiceList = new InvoiceRegistry();
        this.status = false;
        paymentRegistry = new ArrayList<>();
        executionDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());

    }
/*
transaction list
for each transaction addentrytoinvoice || create newInvoice
Send InvoicesByemail
Register Payment to file -> (Date of payment,frelancer email , amount paid eur and freelancer currency , task id)


 */


    public boolean makePayments(){
        //Executes all payments to be made

        System.out.println("make payments running");
        TransactionList transactionList = org.getUnpaidTransactionList(); //get unpaid list
        ArrayList<Transaction> unpaidTransactionList = (ArrayList<Transaction>) transactionList.getTransList();

        for (Transaction trans : unpaidTransactionList) {
            Freelancer fre = trans.getFreelancer();
            Task task = trans.getTask();

            //generate new Payment
            Payment payment = new Payment();
            payment.setId(trans.getId());
            payment.setValue(trans.amountToPaid());
            payment.setCurrency(new Currency(1.0));

            //add payment to current  transaction
            trans.setPayment(payment);



            //check if there is any invoice with freelancer email already if not create one
            if ( this.invoiceList.hasInvoiceWithFreelancer(fre.getEmail()) < 1) {
                invoiceList.addInvoice(new Invoice(fre));
            }
                //Add entry to the respective invoice according to freelancer email
                invoiceList.addEntryToInvoice(fre.getEmail(), task,payment);
                //add Payment to be registred in file
                registerPayment(fre, payment,task);

        }
        //Write Payment to file
        invoiceList.sendInvoicesByMail();
        writePaymentsToFile();
        return false;
    }


    public void writePaymentsToFile(){
    writer.writePayment(this.paymentRegistry);

    }


    public void registerPayment(Freelancer fre, Payment pay, Task t) {
        // Date, TaskID, TaskDescription, FreelancerID, FreelancerName, BankAccount, ValorEuros, ValorFreelancerCurrency"
        //(Date of payment,frelancer email , amount paid eur and freelancer currency , task id)

           // paymentRegistry.add(String.format("PaymentDate: %s; \t Freelancer: %s; \t Value paid: %d; \t Task: %s; \t Invoice: %d;",
             //       this.executionDate, fre.getEmail(), pay.getValue(), t.getId(), pay.getInvoice()));
        paymentRegistry.add(("Date:"+ pay.getPaidDate()+ " TaskID:"+t.getId()+ " TaskDescription:"+t.getDescription()+
                " FreelancerID:" +fre.getId()+ " FreelancerName"+ fre.getName() + " BankAccount:"+ fre.getBankAccount()+
                " ValueInEuros:" + pay.getValue()+
                " ValueInFreelancerCurrency:"  + converter.convertCurrency(pay.getValue(),fre.getCountryCode()) +";"));
        }




}
