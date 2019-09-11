package c.enlistinghelp;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class CourseOfferingScraper {
	private ArrayList<ArrayList<SectionInfo>> courseOffers;
	
	public CourseOfferingScraper() {
		courseOffers = new ArrayList<>();
	}
	
	public void dispArr(String[] arrSamp) {
		for (String arrSamp1 : arrSamp)
			System.out.println(arrSamp1);
		System.out.println();
	}
	
	public void dispCOffers() {
		System.out.println("\n\n\n\t\tSTART DispCourOff:");
		for (int i = 0; i < courseOffers.size(); i++) {
			System.out.println("\t\tretrieving classes for " + courseOffers.get(i).get(0).getCourseCode() + "...\n");
			for (int j = 0; j < courseOffers.get(i).size(); j++)
				System.out.println(courseOffers.get(i).get(j));	
		}
	}
	
	public boolean writeTo(String filename) {
		try {
			FileWriter fw = new FileWriter(filename, false);
			fw.append("\t\tSTART DispCourOff:\n\n");
			for (int i = 0; i < courseOffers.size(); i++) {
				fw.append("\t\tretrieving classes for " + courseOffers.get(i).get(0).getCourseCode() + "...\n");
				for (int j = 0; j < courseOffers.get(i).size(); j++)
					fw.append(courseOffers.get(i).get(j).toString() + "\n");	
			}
			fw.append("\t\tEND OF TABLE\n");
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
	
	/*
	
	algo for extracting String data into a SectionInfo class, for passing to constructor
	- get the List<String> output from the crawler (already done)
	- each element under List<String> is a row in the table of the table from the course offerings
	- take each List element (row) and the next one
		- (CHECK IF NEXT ROW STARTS WITH A NUMBER, ELSE IT'S PROBS A PROF NAME)
			- for this, maybe do try-catch, catching NumberFormatException
	- then use String[] = String.split(" ") to get each item, converting them into each field of the SectionInfo
	- then pass them all into the constructor
	
	*/
	
	public void parseList(List<String> listRows) {
		int dump;
		String s1, s2;
		String[] arrProf, arrClass;
		SectionInfo dumperino;
		courseOffers.add(new ArrayList<>());
		for (int i = 1; i < listRows.size(); i++) {
			try {
				s1 = listRows.get(i+1);
				s2 = listRows.get(i);
			} catch (IndexOutOfBoundsException e) {
				s1 = "STOP";
				s2 = "STOP";
			}
			arrProf = s1.split(" ");
			arrClass = s2.split(" ");
			
			if (arrClass[0].compareTo("STOP") != 0) {
				if (arrClass[2].charAt(0) != 'X') {
					try {
						dump = Integer.parseInt(arrProf[0]);
						dumperino = new SectionInfo(listRows.get(i));
							// ^ fix patterns, you're passing a prof name when you're at the last table row
							// idea: make a method that checks what fields are NULL
						if (!hasNull(dumperino))
							courseOffers.get(courseOffers.size()-1).add(dumperino);
					} catch (NumberFormatException eLevel1) {
						dumperino = new SectionInfo(listRows.get(i), listRows.get(i+1));
						courseOffers.get(courseOffers.size()-1).add(dumperino);
						i++;
					} catch (IndexOutOfBoundsException eLevel1) {
						try {
							dump = Integer.parseInt(arrProf[0]);
							dumperino = new SectionInfo(listRows.get(i));
							courseOffers.get(courseOffers.size()-1).add(dumperino);
						} catch (NumberFormatException eLevel2) {
							System.out.println("END OF TABLE");
						}
					}
//					System.out.println(courseOffers.get(courseOffers.size()-1));
				}
			} else {
				System.out.println("\t\t\tEND!");
			}
		}
	}
	
	public LocalTime formatTime(String sTime) {
		return LocalTime.of(Integer.parseInt(sTime.substring(0, 2)), Integer.parseInt(sTime.substring(2)));
	}
}
