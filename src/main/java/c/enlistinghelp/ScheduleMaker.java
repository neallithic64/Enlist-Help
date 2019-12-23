package c.enlistinghelp;

import java.util.ArrayList;
import java.time.LocalTime;

/** Helper class to manage generation of weekly schedules. ArrayList {@code schedList} is
 * used to store these 
 * @author Neal
 */
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
	
	public void combi(ArrayList<ArrayList<String>> orig, ArrayList<String> result, int step, String curr) {
		if (step == orig.size())
			result.add(curr);
		else for (int i = 0; i < orig.get(step).size(); i++)
			combi(orig, result, step+1, curr+orig.get(step).get(i));
	}
	
	public void dispArrList(ArrayList<String> aList) {
		System.out.println("begin printing");
		for (int i = 0; i < aList.size(); i++)
			System.out.println(aList.get(i));
		System.out.println();
	}
	
	public ArrayList<WeekSchedule> generateMultipleScheds() {
		ArrayList<WeekSchedule> scheds = new ArrayList<>();
		return null;
	}
}
