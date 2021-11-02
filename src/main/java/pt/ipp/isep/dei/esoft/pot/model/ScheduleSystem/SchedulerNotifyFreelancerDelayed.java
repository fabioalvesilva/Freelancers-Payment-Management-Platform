package pt.ipp.isep.dei.esoft.pot.model.ScheduleSystem;

import java.io.Serializable;
import pt.ipp.isep.dei.esoft.pot.model.NotifyFreelancerDelayed;

import java.text.SimpleDateFormat;

public class SchedulerNotifyFreelancerDelayed implements Serializable {

private String executionDate;
private NotifyFreelancerDelayed notification;
///
///
    private final Schedule schedule = new Schedule();
    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("dd MMM yyyy HH:mm:ss.SSS");
    private final int dayOfMonth, hourOfDay, minute, second;
    private NotifyFreelancerDelayed notificate;

    public SchedulerNotifyFreelancerDelayed(int dayOfMonth, int hourOfDay, int minute, int second) {
        this.executionDate = executionDate;
        this.dayOfMonth = dayOfMonth;
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        this.second = second;
        this.notificate = new NotifyFreelancerDelayed();

    }


    public void start() {
        schedule.schedule(new SchedulerTask() {
            public void run() {
                notificate.executeNotification();
            }

        }, new DailyIterator(dayOfMonth,hourOfDay, minute, second));
    }

    public void notifyFreelancer(){
        this.notificate.executeNotification();
    }

}
