package pt.ipp.isep.dei.esoft.pot.model;

import pt.ipp.isep.dei.esoft.pot.model.ScheduleSystem.ScheduledMakePayments;
import pt.ipp.isep.dei.esoft.pot.model.ScheduleSystem.SchedulerNotifyFreelancerDelayed;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class Scheduler implements Serializable {

    public  Scheduler (Organization org, String executionDate) {

        int [] args = getDateToInt(executionDate);
        ScheduledMakePayments payment = new ScheduledMakePayments(args[0], args[1], args[2],args[3], org);
        payment.start();

    }

    public  Scheduler (String executionDate){
        int [] args = getDateToInt(executionDate);
        SchedulerNotifyFreelancerDelayed notifyFrelancerScheduler = new SchedulerNotifyFreelancerDelayed(args[0], args[1], args[2],args[3]);
        notifyFrelancerScheduler.start();
    }

    private int [] getDateToInt(String date){
        //date format :  String date = "2019-12-01 23:05";
        int[] dateAsInt = new int [4];
        String[] dateandHour = date.split(" ");
        String[] dateOnly = dateandHour[0].split("-");
        String[] hourMinute = dateandHour[1].split((":"));

        String day=cleanLeftZero(dateOnly[2]);
        String hour = cleanLeftZero(hourMinute[0]);
        String minute = cleanLeftZero(hourMinute[1]);
        dateAsInt[0] = Integer.parseInt(day);
        dateAsInt[1]= Integer.parseInt(hour);
        dateAsInt[2] = Integer.parseInt(minute);
        dateAsInt[3] = 0; //seconds as default
        return dateAsInt;
    }

    private static String cleanLeftZero(String s ){
        String str = s.replaceFirst("^0+(?!$)", "");
        return  str;
    }

}