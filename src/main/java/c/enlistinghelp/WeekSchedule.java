package c.enlistinghelp;

import java.util.ArrayList;
import java.time.LocalTime;

/** Models an entire weekly schedule, compromised of a collection of SectionInfo objects
 * and a name label for this specific schedule. Adding to the ArrayList of sections
 * include checking if the course code does not yet exist (using {@code checkUniqueAll()}
 * and checking if the times do not yet overlap (using {@code checkOverlap()}).
 * @author Neal
 */
public class WeekSchedule {
	private String name;
	private ArrayList<SectionInfo> classes;
	
	public WeekSchedule(String name) {
		this.name = name;
		classes = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	public ArrayList<SectionInfo> getClasses() {
		return classes;
	}
	
	private boolean checkUniqueAll(SectionInfo newClass) {
		if (classes.isEmpty())
			return true;
		else
			for (int i = 0; i < classes.size(); i++)
				if (newClass.getCourseCode().compareTo(classes.get(i).getCourseCode()) == 0)
					return false;
		return true;
	}
	private boolean checkOverlap(SectionInfo newClass) {
		if (classes.isEmpty())
			return true;
		else
			for (int i = 0; i < classes.size(); i++) {
				LocalTime loopSTime = classes.get(i).getStartTime(),
						loopETime = classes.get(i).getEndTime(),
						tempSTime = newClass.getStartTime(),
						tempETime = newClass.getEndTime();
				if ((loopSTime.isBefore(tempSTime) && loopETime.isAfter(tempSTime)) || (loopSTime.isBefore(tempETime) && loopETime.isAfter(tempETime)))
					return false;
			}
		return true;
	}
	
	public void addClass(SectionInfo newClass) {
		if (checkOverlap(newClass) && checkUniqueAll(newClass))
			classes.add(newClass);
		else System.out.println("input class is rejected!");
	}
	
	/** Additional auxiliary method to sort the sections by day, but will be left unfinished
	 * as this is not crucial in the making of schedules.<br>
	 * Recommended order: MW, TH, F, S, others
	 */
	public void sortByDay() {
		if (!classes.isEmpty()) {
			for (int i = 0; i < classes.size(); i++) {
				System.out.print("sorting ");
			}
		} else System.out.println("no input classes found!");
		System.out.println();
	}
	
	@Override
	public String toString() {
		String strData = "\nSchedule Report for " + name + ":\n";
		for (int i = 0; i < classes.size(); i++)
			strData += classes.get(i).toString() + "\n";
		strData += "\tSchedule Score: " + classes.size() + "\n";
		strData += "-----------------------------------\n";
		return strData;
	}
}
