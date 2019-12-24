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
	
	/* original prototype, uses only Strings as base class (see following two methods
		combi() and dispArrList(). these two shouldn't be used nor called at all anywhere.
		just use this as reference.
	** algorithm: i think you should know what combi() does. basically, given some 2D
		collection, it combines the current object's contents to all items in a row,
		iterating through each item in each row, then adding them to the result
		collection once there're no more rows left. the resulting collection MUST
		contain every possible combination of the input 2D collection.
	only possible qualm with this recursive implementation is that this is rather
		memory-extensive. knowing how java is like, it's will most DEFINITELY run
		into memory exceptions. however, additional conditionals before adding to
		resulting collection MAY limit and thus lessen the load on the memory.
	HOPEFULLY. it's really wishful thinking, but idk i don't have anything else lmao
	*/	
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
	
	/** Generates all possible schedules made from the given class offerings and places
	 * them all into the ArrayList {@code schedList}. No return type, so need to use
	 * the respective getter after calling this method. Implementation will make use
	 * of recursion calls in order to generate combinations.
	 * @param courseOff Input 2D ArrayList containing all possible sections per course
	 * @param step yes
	 * @param curr Current WeekSchedule being constructed
	 */
	public void generateMultiSched(ArrayList<ArrayList<SectionInfo>> courseOff, int step, WeekSchedule curr) {
		if (step == courseOff.size())
			schedList.add(curr);
		else for (int i = 0; i < courseOff.get(step).size(); i++) {
			curr.addClass(courseOff.get(i).get(step)); // not sure if the indices are right lmao
			generateMultiSched(courseOff, step+1, curr);
		}
	}
}
