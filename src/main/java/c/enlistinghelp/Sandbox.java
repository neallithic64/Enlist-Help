package c.enlistinghelp;
import java.util.List;
import java.util.ArrayList;

class Thing {
	List<String> wow;
	Thing() {
		wow = new ArrayList<>();
	}
	@Override
	public String toString() {
		String strData = "";
		for (int i = 0; i < wow.size(); i++)
			strData += wow.get(i) + " ";
		return strData;
	}
}

public class Sandbox {
	public static void combi(ArrayList<ArrayList<String>> orig, ArrayList<String> result, int step, String curr) {
		if (step == orig.size())
			result.add(curr);
		else for (int i = 0; i < orig.get(step).size(); i++)
			combi(orig, result, step+1, curr+orig.get(step).get(i));
	}
	
	public static void combiTest(ArrayList<ArrayList<String>> orig, ArrayList<Thing> result, int step, Thing curr) {
		if (step == orig.size())
			result.add(curr);
		else for (int i = 0; i < orig.get(step).size(); i++) {
			Thing temp = new Thing();
			temp.wow = curr.wow;
			temp.wow.add(orig.get(step).get(i));
			combiTest(orig, result, step+1, temp);
		}
	}
	
	public static void dispArrList(ArrayList<?> aList) {
		System.out.println("size: " + aList.size());
		for (int i = 0; i < aList.size(); i++)
			System.out.println(aList.get(i));
		System.out.println();
	}
	
	
	
	/* Main method!
	*/
	public static void main(String[] args) {
		ArrayList<ArrayList<String>> list = new ArrayList<>();
		
		for (int i = 0; i < 4; i++)
			list.add(new ArrayList<>());
		
		list.get(0).add("a"); list.get(0).add("b"); list.get(0).add("c"); list.get(0).add("d");
		list.get(1).add("A"); list.get(1).add("B"); list.get(1).add("C");
		list.get(2).add("1"); list.get(2).add("2");
		list.get(3).add("!");
		
/*	confirmed to work:
		dispArrList(list.get(0));
		dispArrList(list.get(1));
		dispArrList(list.get(2));
		dispArrList(list.get(3));
		ArrayList<String> resultSet = new ArrayList<>();
		combi(list, resultSet, 0, "");
		dispArrList(resultSet);*/
		
		// testing
		ArrayList<Thing> hi = new ArrayList<>();
		combiTest(list, hi, 0, new Thing());
		dispArrList(hi);
		
		ArrayList<Integer> test = new ArrayList<>();
		for (int o = 1; o <= 10; o++)
			test.add(o);
		System.out.println(test.stream().mapToInt(a -> a).reduce(1, (a,b) -> a*b));
	}
}
