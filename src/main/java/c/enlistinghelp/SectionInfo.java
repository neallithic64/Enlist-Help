package c.enlistinghelp;

import java.time.LocalTime;
import java.util.Arrays;

public class SectionInfo {
	private int classNmbr;
	private String courseCode;
	private String section;
	private char[] days;
	private LocalTime startTime;
	private LocalTime endTime;
	private String[] rooms;
	private int capacity;
	private int size;
	private String remarks;
	private String professor;
	
	public SectionInfo(String data) {
		String[] arrInfo = data.split(" ");
		rooms = new String[] {""};
		if (arrInfo.length >= 10) {
			classNmbr = Integer.parseInt(arrInfo[0]);
			courseCode = arrInfo[1];
			section = arrInfo[2];
			days = arrInfo[3].toCharArray();
			startTime = LocalTime.of(Integer.parseInt(arrInfo[4].substring(0, 2)), Integer.parseInt(arrInfo[4].substring(2)));
			endTime = LocalTime.of(Integer.parseInt(arrInfo[6].substring(0, 2)), Integer.parseInt(arrInfo[6].substring(2)));
			rooms[0] = arrInfo[7];
			capacity = Integer.parseInt(arrInfo[8]);
			size = Integer.parseInt(arrInfo[9]);
			professor = null;
		}
		
		if (arrInfo.length == 11) {
			remarks = arrInfo[10];
		} else {
			remarks = null;
		}
	}
	public SectionInfo(String data, String profData) {
		this(data);
		professor = profData;
	}
	
	public int getClassNmbr() {
		return classNmbr;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public String getSection() {
		return section;
	}
	public char[] getDays() {
		return days;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public String[] getRooms() {
		return rooms;
	}
	public int getCapacity() {
		return capacity;
	}
	public int getSize() {
		return size;
	}
	public String getRemarks() {
		return remarks;
	}
	public String getProfessor() {
		return professor;
	}
	
	public void setProfessor(String prof) {
		professor = prof;
	}
	
	public void fixRooms() {
		while (days.length > rooms.length)
			appendRooms(rooms[0]);
	}
	
	/* algo (basing from CCINFOM):
	- case 1: tempStr[0] (day) has already exists in last SectionInfo entry
		- concatenate new rooms
	- case 2: tempStr[0] (day) doesn't yet exist in last SectionInfo entry
		- add new day entry, as well as new room
	*/
	
	private boolean hasDDupe(char c) {
		for (int i = 0; i < days.length; i++)
			if (c == days[i])
				return true;
		return false;
	}
	
	public void appendDays(String newD) {
		if (newD.length() > 0) {
			for (int i = 0; i < newD.length(); i++)
				if (!hasDDupe(newD.charAt(i))) {
					char[] newDays = Arrays.copyOf(days, days.length + 1);
					newDays[days.length + i] = newD.charAt(i);
					days = newDays;
				}
		}
	}
	
	public void appendRooms(String newR) {
		if (days.length == rooms.length) {
			for (int i = 0; i < rooms.length; i++)
				rooms[i] += ", " + newR;
		} else {
			String[] newRooms = Arrays.copyOf(rooms, rooms.length+1);
			newRooms[rooms.length] = newR;
			rooms = newRooms;
		}
	}
	
	@Override
	public String toString() {
		String strData = "Class Info of #" + classNmbr + ":\n"
				+ "Course Code: " + courseCode + "\n"
				+ "Section: " + section + "\n";
		
		for (int i = 0; i < days.length; i++)
			strData += "Time: " + days[i] + " " + startTime.toString() + "-" + endTime.toString() + "\n"
						+ "\tRoom: " + rooms[i] + "\n";
		
		strData += "Class Size: " + size + "/" + capacity + "\n";
		if (professor != null)
			strData += "Professor: " + professor + "\n";
		if (remarks != null)
			strData += "Remarks: " + remarks + "\n";
		return strData;
	}
}
