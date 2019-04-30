import java.util.Calendar;
import java.util.GregorianCalendar;

public class Adapter {
	private Calendar calender = new GregorianCalendar();
	
	public int getHour() {
		return calender.get(Calendar.HOUR_OF_DAY);
	}
	
	public int getMinute() {
		return calender.get(Calendar.MINUTE);
	}
	
	public int getSecond() {
		return calender.get(Calendar.SECOND);
	}
}
