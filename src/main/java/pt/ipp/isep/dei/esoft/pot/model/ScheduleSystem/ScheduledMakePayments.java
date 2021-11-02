package pt.ipp.isep.dei.esoft.pot.model.ScheduleSystem;

import java.io.Serializable;
import pt.ipp.isep.dei.esoft.pot.model.MakePayments;
import pt.ipp.isep.dei.esoft.pot.model.Organization;

import java.text.SimpleDateFormat;
///


public class ScheduledMakePayments implements Serializable {

    private MakePayments p;
    private String executionDate;
    ///
    private final Schedule schedule = new Schedule();
    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("dd MMM yyyy HH:mm:ss.SSS");
    private final int dayOfMonth, hourOfDay, minute, second;


    public ScheduledMakePayments(int dayOfMonth, int hourOfDay, int minute, int second, Organization org ){
        this.dayOfMonth = dayOfMonth;
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        this.second = second;
        p = new MakePayments(org);

    }

    public void start() {
        schedule.schedule(new SchedulerTask() {
            public void run() {
                p.makePayments();
            }

        }, new DailyIterator(dayOfMonth,hourOfDay, minute, second));
    }

}




////




