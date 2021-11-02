package pt.ipp.isep.dei.esoft.pot.model;

import pt.ipp.isep.dei.esoft.pot.controller.POTAplication;
import pt.ipp.isep.dei.esoft.pot.model.EmailAPI.EmailSystem;

import java.util.*;

import static jdk.nashorn.internal.objects.NativeMath.round;

public class NotifyFreelancerDelayed {
    final EmailSystem email  = POTAplication.getInstance().getPlatform().getEmailSytem();

/*
. Moreover, in the last day of every year
the system should automatically send an e-mail to all the freelancers
 who have:
    a mean task delay time (during the current year) > 3 hours &&
    have a percentage of delays (during the current year) that is higherthan the overall percentage of delays.
 */
    private Map<Freelancer, int []> freelancerDelays;
    private  int totalTransactionsPlat;
    private int totalTransctionsWithDelaysPLat;

    private POTAplication m_oApp;
    private Platform m_oPlatform;
    private FreelancerRegister m_FreelancerRegister;
    private OrganizationRegister m_Orgr;



    //Class Freelancer registry na palatforma

    final static String SUBJECT = "Execution task delays above average" ;
    final static double MEAN =3;

    public NotifyFreelancerDelayed() {
        m_oApp = POTAplication.getInstance();
        m_oPlatform = m_oApp.getPlatform();
        m_FreelancerRegister = m_oPlatform.getFreelancerRegister();
        m_Orgr = m_oPlatform.getOrganizationRegister();

        freelancerDelays = new HashMap<>();
        this.totalTransactionsPlat = 0;
        this.totalTransctionsWithDelaysPLat = 0;
    }

    public boolean executeNotification(){
        this.fillFreelancerDelays();

        this.sendNotifications();
        return true;

    }

    private void fillFreelancerDelays(){

         Set<Organization> orgList = m_Orgr.getOrganizationList();
         Set<Freelancer> freRegistredList = m_FreelancerRegister.getFreelancerList();
        for (Freelancer f :freRegistredList) {
            freelancerDelays.put(f, new int[4]);
        }
        for (Organization o :orgList) {
            List<Transaction> tList =  o.getM_transactionList().getTransList();
            for (Transaction t : tList) {
                if(t != null){
                    totalTransactionsPlat++; //add a transaction to PLataform transactions counter
                    Freelancer f = t.getFreelancer();//get transaction freelancer
                    int d = t.getDelay(); // get transaction delay//current Transaction selay
                    //if(t.hasPayment()) //required if notification is to be sent only to made transactions
                    if (d > 0) {totalTransctionsWithDelaysPLat++;} // increment the value of platform tasks with delays;
                        int[] fVOld = freelancerDelays.get(f);
                        freelancerDelays.put(f,incrementFValues(d,fVOld)); //replace values for a given key
                }

                }
            }
        }

    private boolean sendNotifications(){
        ///calculate percentage of delays PLat delays
        double percentageDelaysPlat=calculatePercentages(this.totalTransctionsWithDelaysPLat, this.totalTransactionsPlat);
        for (Map.Entry<Freelancer, int[]> entry : freelancerDelays.entrySet()) {
            Freelancer k = entry.getKey();
            int[] v = entry.getValue();
           double percentageDelaysFre =  calculatePercentages(v[2],v[3]);
            if (calculateMean(v[0],v[1]) && (percentageDelaysFre > percentageDelaysPlat))
             {
                sendEmail(k.getEmail(),percentageDelaysFre);
            }
        }
        return  true;
    }


    private int[] incrementFValues (int valueDelaysFre ,  int[]vals){
        int[] newVals = new int[4];
            newVals[3] = vals[3] +1; // totaloftasks of the freelancer
        if (valueDelaysFre >0 ){
            newVals[0] = vals[0] + valueDelaysFre;
            newVals[1] =  vals[1]+1; //counterDelaysFre
            newVals[2] =  vals[2]+1; //NumberOfTasksWithDelays
        }
        return newVals;
    }
    private boolean sendEmail(String email, Double bodyValue){
        String body = String.format("Your mean task delay is: %.3f  which is above average of typical delay!", bodyValue);
        this.email.sendEmail(email,SUBJECT,body);
        return true;
    }

private static boolean calculateMean(int totalValueDelaysFre, int counterDelaysFre){
     double dtotalValueDelaysFre = totalValueDelaysFre;
     double dcounterDelaysFre = counterDelaysFre;
        if ((dtotalValueDelaysFre/dcounterDelaysFre)>MEAN){
            return true;
        }
        return false;
    }

    private  double calculatePercentages(int numberOfTransactionsWithDelays, int TotalTransactions){
        double dnumberOfTransactionsWithDelays = numberOfTransactionsWithDelays;
        double dTotalTransactions = TotalTransactions;
        double dpercent = (dnumberOfTransactionsWithDelays/dTotalTransactions)*100;
        double percent= (numberOfTransactionsWithDelays/dTotalTransactions)*100;
        return dpercent;
    }

}
