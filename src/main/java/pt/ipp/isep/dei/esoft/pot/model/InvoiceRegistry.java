package pt.ipp.isep.dei.esoft.pot.model;

import java.util.ArrayList;

import pt.ipp.isep.dei.esoft.pot.controller.POTAplication;
import pt.ipp.isep.dei.esoft.pot.model.EmailAPI.EmailSystem;
import pt.ipp.isep.dei.esoft.utils.UtilFileWriter;

public class InvoiceRegistry {
    
    final EmailSystem email  = POTAplication.getInstance().getPlatform().getEmailSytem();

    private ArrayList<Invoice> invoices;

    public InvoiceRegistry() {
        this.invoices = new ArrayList<Invoice>();
    }

    public boolean addInvoice(Invoice newInvoice){
        if (newInvoice != null){
            return this.invoices.add(newInvoice);
        }
        return false;

    }

    public int hasInvoiceWithFreelancer (String emailFreelancer){
        if (this.invoices.size()<1)
            return 0;
        for (Invoice in: this.invoices) {
            if (in.getInvoiceContact().equals(emailFreelancer)){
                return in.getId();
            }
        }
        return 0;
    }
    public boolean addEntryToInvoice(String emailFreelancer,Task task , Payment payment){
        for (Invoice in: this.invoices) {
            if (in.getInvoiceContact().equals(emailFreelancer)){
                if(payment != null && task != null ){
                    in.addNewEntry(task ,payment);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean sendInvoicesByMail(){
         int nInvoicesSent=0;

        for (Invoice in: this.invoices) {
            email.sendEmail(in.getInvoiceContact(),in.getInvoiceTitle(), in.toInvoiceToEmailFormat());
            nInvoicesSent++;
        }
        if (nInvoicesSent == invoices.size()){
            return true;
        }
        return false;
    }



}
