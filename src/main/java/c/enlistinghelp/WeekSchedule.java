package c.enlistinghelp;

import java.util.ArrayList;
import java.time.LocalTime;

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
	
	@Override
	public String toString() {
		String strData = "Schedule Report for " + name + ":\n";
		for (int i = 0; i < classes.size(); i++)
			strData += classes.get(i).toString();
		strData += "\n-----------------------------------\n";
		return strData;
	}
}
