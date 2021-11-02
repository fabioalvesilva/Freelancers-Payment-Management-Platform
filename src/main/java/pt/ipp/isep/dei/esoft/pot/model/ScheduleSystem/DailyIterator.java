
package pt.ipp.isep.dei.esoft.pot.model.ScheduleSystem;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


public class DailyIterator implements ScheduleIterator, Serializable{
    private final int dayOfMonth, hourOfDay, minute, second;
    private final Calendar calendar = Calendar.getInstance();

    public DailyIterator(int dayOfMonth, int hourOfDay, int minute, int second) {
        this(dayOfMonth, hourOfDay, minute, second, new Date());
    }

    public DailyIterator(int dayOfMonth, int hourOfDay, int minute, int second, Date date) {
        this.dayOfMonth = dayOfMonth;
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        this.second = second;
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);
        if (!calendar.getTime().before(date)) {
            calendar.add(Calendar.DATE, -1);
        }
    }

    public Date next() {
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

}