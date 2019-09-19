package c.enlistinghelp;

import java.util.ArrayList;
import java.time.LocalTime;

public class ScheduleMaker {
	private ArrayList<WeekSchedule> schedList;
	
	public ScheduleMaker() {
		schedList = new ArrayList<>();
	}
	
	public ArrayList<WeekSchedule> getScheds() {
		return schedList;
	}
	
	public void readClasses() {
		
	}
	
	public WeekSchedule generateOneSched(ArrayList<ArrayList<SectionInfo>> inputClasses) {
		WeekSchedule sched = new WeekSchedule("!!!TEST!!!");
		
		for (int i = 0; i < inputClasses.size(); i++) {
			for (int j = 0; j < inputClasses.get(i).size(); j++) {
				sched.addClass(inputClasses.get(i).get(j));
			}
		}
		sched.sortByDay();
		return sched;
	}
	
	public ArrayList<WeekSchedule> generateMultipleScheds() {
		ArrayList<WeekSchedule> scheds = new ArrayList<>();
		return null;
	}
}
