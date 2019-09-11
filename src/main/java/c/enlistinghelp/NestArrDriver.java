package c.enlistinghelp;

import java.util.*;

public class NestArrDriver {
	public static void main(String[] args) {
		ArrayList<ArrayList<String>> arrArrString = new ArrayList<>();
		
		
		ArrayList<String> list = new ArrayList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		Iterator iterator = list.iterator();
		System.out.println("List elements : ");
		while (iterator.hasNext())
			System.out.println(iterator.next() + " ");
		System.out.println("\n\n\n");
		
		
		String lipSum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
				+ "Nullam et diam nunc. Nulla facilisi. Aenean quis nisl ut dui"
				+ "sodales malesuada. Aliquam finibus elit sed neque rutrum egestas."
				+ "Integer felis magna, fermentum id sodales dignissim, hendrerit"
				+ "eu ligula.\nAenean mauris ex, finibus sed turpis ac, aliquet dictum"
				+ "rci. Vestibulum venenatis purus vestibulum, viverra augue sed,"
				+ "congue dolor.\nVestibulum ante ipsum primis in faucibus orci"
				+ "uctus et ultrices posuere cubilia Curae; Aenean quis velit ipsum."
				+ "ras massa enim, egestas quis mollis suscipit, rutrum nec nulla."
				+ "uspendisse in nisl risus.\nIn eget metus quis lacus porta feugiat."
				+ "Morbi at odio eros. Sed tempus elit est, vitae convallis nunc"
				+ "fringilla non. Nam fermentum dolor id turpis pulvinar, id porttitor"
				+ "nisl euismod.\nQuisque a arcu odio. Nunc a porta nulla. Quisque"
				+ "a metus consectetur, egestas lacus eu, volutpat sapien. Aliquam"
				+ "malesuada sollicitudin ipsum, in hendrerit orci condimentum in."
				+ "am ac mollis massa.\nPraesent sit amet lectus sed augue mollis"
				+ "ehicula eu et nunc. Morbi et nisi a tortor hendrerit lacinia."
				+ "Integer non imperdiet urna. Duis vestibulum faucibus orci quis"
				+ "convallis. Maecenas ornare nunc vel augue pharetra lobortis."
				+ "Integer in iaculis lectus.\nQuisque nisl erat, euismod in justo"
				+ "in, vulputate ultricies tortor. Orci varius natoque penatibus"
				+ "et magnis dis parturient montes, nascetur ridiculus mus."
				+ "\nPellentesque quis leo nec lacus gravida placerat eu fringilla"
				+ "ante. Ut et leo mauris. Suspendisse faucibus metus eget felis"
				+ "ollis condimentum. In non nunc volutpat, porta ligula sed, feugiat"
				+ "risus.\nPellentesque habitant morbi tristique senectus et netus et"
				+ "malesuada fames ac turpis egestas.";
		String[] arrLipSum = lipSum.split("\n");
		String[] temp;
		
		for (int i = 0; i < arrLipSum.length; i++) {
			temp = arrLipSum[i].split(" ");
			arrArrString.add(new ArrayList<>());
			for (int j = 0; j < temp.length; j++)
				arrArrString.get(i).add(temp[j]);
		}
		
		for (int i = 0; i < arrArrString.size(); i++)
			for (int j = 0; j < arrArrString.get(i).size(); j++)
				System.out.println("String at (" + i + ", " + j + ")" + ": " + arrArrString.get(i).get(j));
	}
}