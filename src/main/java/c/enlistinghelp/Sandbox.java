package c.enlistinghelp;

import java.util.ArrayList;

public class Sandbox {
	public static void combi(ArrayList<ArrayList<String>> orig, ArrayList<String> result, int step, String curr) {
		if (step == orig.size())
			result.add(curr);
		else for (int i = 0; i < orig.get(step).size(); i++)
			combi(orig, result, step+1, curr+orig.get(step).get(i));
	}
	
	
	public static void dispArrList(ArrayList<String> aList) {
		System.out.println("begin printing, list size: " + aList.size());
		for (int i = 0; i < aList.size(); i++)
			System.out.println(aList.get(i));
		System.out.println();
	}
	
	public static void main(String[] args) {
		ArrayList<ArrayList<String>> list = new ArrayList<>();
		ArrayList<String> resultSet = new ArrayList<>();
		
		for (int i = 0; i < 4; i++)
			list.add(new ArrayList<>());
		
		list.get(0).add("a");
		list.get(0).add("b");
		list.get(0).add("c");
		list.get(0).add("d");
		list.get(1).add("A");
		list.get(1).add("B");
		list.get(1).add("C");
		list.get(2).add("1");
		list.get(2).add("2");
		list.get(3).add("!");
//		list.get(3).add("@");
//		list.get(3).add("#");
//		list.get(3).add("$");
//		list.get(3).add("%");
		
		dispArrList(list.get(0));
		dispArrList(list.get(1));
		dispArrList(list.get(2));
		dispArrList(list.get(3));
		
		combi(list, resultSet, 0, "");
		dispArrList(resultSet);
	}
}
