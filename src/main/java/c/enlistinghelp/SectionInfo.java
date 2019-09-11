package c.enlistinghelp;

import java.time.LocalTime;

public class SectionInfo {
	private int classNmbr;
	private String courseCode;
	private String section;
	private String days;
	private LocalTime startTime;
	private LocalTime endTime;
	private String room;
	private int capacity;
	private int size;
	private String remarks;
	private String professor;
	
	public SectionInfo(int classNmbr, String courseCode, String section, String days, LocalTime startTime, LocalTime endTime, String room, int capacity, int size, String remarks, String professor) {
		this.classNmbr = classNmbr;
		this.courseCode = courseCode;
		this.section = section;
		this.days = days;
		this.startTime = startTime;
		this.endTime = endTime;
		this.room = room;
		this.capacity = capacity;
		this.size = size;
		this.remarks = remarks;
		this.professor = professor;
	}
	public SectionInfo(int classNmbr, String courseCode, String section, String days, LocalTime startTime, LocalTime endTime, String room, int capacity, int size, String professor) {
		this.classNmbr = classNmbr;
		this.courseCode = courseCode;
		this.section = section;
		this.days = days;
		this.startTime = startTime;
		this.endTime = endTime;
		this.room = room;
		this.capacity = capacity;
		this.size = size;
		this.remarks = null;
		this.professor = professor;
	}
	public SectionInfo(int classNmbr, String courseCode, String section, String days, LocalTime startTime, LocalTime endTime, String room, int capacity, int size) {
		this.classNmbr = classNmbr;
		this.courseCode = courseCode;
		this.section = section;
		this.days = days;
		this.startTime = startTime;
		this.endTime = endTime;
		this.room = room;
		this.capacity = capacity;
		this.size = size;
		this.remarks = null;
		this.professor = null;
	}
	public SectionInfo(String data) {
		String[] arrInfo = data.split(" ");
		if (arrInfo.length >= 10) {
			classNmbr = Integer.parseInt(arrInfo[0]);
			courseCode = arrInfo[1];
			section = arrInfo[2];
			days = arrInfo[3];
			startTime = LocalTime.of(Integer.parseInt(arrInfo[4].substring(0, 2)), Integer.parseInt(arrInfo[4].substring(2)));
			endTime = LocalTime.of(Integer.parseInt(arrInfo[6].substring(0, 2)), Integer.parseInt(arrInfo[6].substring(2)));
			room = arrInfo[7];
			capacity = Integer.parseInt(arrInfo[8]);
			size = Integer.parseInt(arrInfo[9]);
			professor = null;
		} else {
			System.out.println("arrInfo length" + arrInfo.length + "containing: " + data);
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
	public String getDays() {
		return days;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public String getRoom() {
		return room;
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
	
	@Override
	public String toString() {
		String strData = "Class Info of #" + classNmbr + ":\n"
				+ "Course Code: " + courseCode + "\n"
				+ "Section: " + section + "\n"
				+ "Time: " + days + " " + startTime.toString() + "-" + endTime.toString() + "\n"
				+ "Room: " + room + "\n"
				+ "Class Size: " + size + "/" + capacity + "\n";
		if (professor != null)
			strData += "Professor: " + professor + "\n";
		if (remarks != null)
			strData += "Remarks: " + remarks + "\n";
		return strData;
	}
}
