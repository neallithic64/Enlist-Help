package c.enlistinghelp;

import java.io.BufferedReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

/** 
 * @author Neal
 */
public class CourseOfferingScraper {
	private ArrayList<ArrayList<SectionInfo>> courseOffers;
	
	public CourseOfferingScraper() {
		courseOffers = new ArrayList<>();
	}
	
	public ArrayList<ArrayList<SectionInfo>> getCourseOffers() {
		return courseOffers;
	}
	
	public void dispArr(String[] arrSamp) {
		for (String arrSamp1 : arrSamp)
			System.out.println(arrSamp1);
		System.out.println();
	}
	
	public void dispCOffers() {
		System.out.println("\n\n\n\t\tSTART DispCourOff:");
		for (int i = 0; i < courseOffers.size(); i++)
			if (courseOffers.get(i).size() > 0)
				for (int j = 0; j < courseOffers.get(i).size(); j++)
					System.out.println(courseOffers.get(i).get(j));
	}
	
	public void dispFlow(ArrayList<String[]> arrFlow) {
		System.out.println("\t\tDISPLAYING FLOWCHART COURSE CODES:");
		for (int i = 0; i < arrFlow.size(); i++) {
			System.out.println("\t\tCodes for Term " + (i+1) + ":");
			for (String get : arrFlow.get(i))
				System.out.println("\t" + get);
		}
	}
	
	/** Method to read some input text file for course codes of a single degree
	 * program's flowchart. Will transfer the text data into internal memory,
	 * sorted and grouped by term.
	 * @param inFile File stream for the flowchart data
	 * @return ArrayList of String[], each containing an array of course codes grouped by term
	 */
	public ArrayList<String[]> readCourseCode(BufferedReader inFile) {
		ArrayList<String[]> data = new ArrayList<>();
		String temp;
		try {
			int termCount = Integer.parseInt(inFile.readLine());
			for (int i = 0; i < termCount; i++) {
				int courseCount = Integer.parseInt(inFile.readLine());
				temp = "";
				for (int j = 0; j < courseCount; j++)
					temp += inFile.readLine() + " ";
				data.add(temp.split(" "));
			}
		} catch (NumberFormatException e) {
			System.out.println("NFE, flowchart.txt has wrong format");
		} catch (IOException e) {
			System.out.println("IOE, idk, you're probs at the end of the file.");
		}
		return data;
	}
	
	public boolean writeTo(String filename) {
		try {
			FileWriter fw = new FileWriter(filename, false);
			fw.append("\t\tSTART DispCourOff:\n\n");
			for (int i = 0; i < courseOffers.size(); i++)
				if (courseOffers.get(i).size() > 0) {
					fw.append("\t\tretrieving classes for " + courseOffers.get(i).get(0).getCourseCode() + "...\n");
					for (int j = 0; j < courseOffers.get(i).size(); j++)
						fw.append(courseOffers.get(i).get(j).toString() + "\n");
				}
			fw.close();
			return true;
		} catch (IOException e) {
			System.out.println("error writing to file!");
			return false;
		}
	}
	
	public boolean hasNull(SectionInfo si) {
		return si.getCourseCode() == null ||
				si.getSection() == null ||
				si.getDays() == null;
	}
	
	/** Parses and processes a source {@code List<String>} and converts the data
	 * into a {@code SectionInfo} object, then adds it into the ArrayList {@code
	 * courseOffers}.<br>
	 * Added a boolean variable to dictate when the continue parsing the input List. This
	 * gets toggled to false once the method reaches a row that contains a section starting
	 * with 'X' (or a Laguna section), exiting the whole for loop and terminating the
	 * method. Rationale behind this that info is volatile, so parsing is dangerous in
	 * the meantime.
	 * @param listRows 
	 */
	public void parseList(List<String> listRows) {
		String[] tempStr;
		SectionInfo tempSecIn;
		courseOffers.add(new ArrayList<>());
		int lastIndex;
		boolean contParse = true;
		
		for (int i = 1; i < listRows.size() && contParse; i++) {
			lastIndex = courseOffers.size()-1;
			if (!listRows.get(i).contains(",")) { // checking if NOT a prof name
				tempStr = listRows.get(i).split(" ");
				if (tempStr[2].charAt(0) == 'X') { // checking if NOT a laguna campus
					contParse = false;
				} else {
					tempSecIn = new SectionInfo(listRows.get(i));
					if (tempStr.length >= 10) { // new SectionInfo
						courseOffers.get(lastIndex).add(tempSecIn);
						courseOffers.get(lastIndex).get(courseOffers.get(lastIndex).size()-1).fixRooms();
					} else if (tempStr.length == 9) {
						System.out.println("missing parameter, skipping...");
					} else if (tempStr.length <= 5) { // append to last section!
						courseOffers.get(lastIndex).get(courseOffers.get(lastIndex).size()-1).appendDays(tempStr[0]);
						courseOffers.get(lastIndex).get(courseOffers.get(lastIndex).size()-1).appendRooms(tempStr[4]);
					}
				}
			} else {
				// prof name found! add to last class!
				courseOffers.get(lastIndex).get(courseOffers.get(lastIndex).size()-1).setProfessor(listRows.get(i));
			}
		}
	}
	
	public LocalTime formatTime(String sTime) {
		return LocalTime.of(Integer.parseInt(sTime.substring(0, 2)), Integer.parseInt(sTime.substring(2)));
	}
}
