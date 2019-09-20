package c.enlistinghelp;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import org.openqa.selenium.NoSuchElementException;

public class SomeDriver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChromeCrawler cc = new ChromeCrawler();
		CourseOfferingScraper cos = new CourseOfferingScraper();
		ScheduleMaker sm = new ScheduleMaker();
		
		try {
			BufferedReader brFlow = new BufferedReader(new InputStreamReader(new FileInputStream("flowchart1Term.txt")));
			ArrayList<String[]> samp1 = cos.readCourseCode(brFlow);
			cos.dispFlow(samp1);
			
			if (!samp1.isEmpty())
				for (int i = 0; i < samp1.size(); i++)
					for (String get : samp1.get(i))
						cos.parseList(cc.coScrape(get));
			brFlow.close();
			
			cc.pageWait(2);
			cc.close();
			cos.dispCOffers();
			cos.writeTo("logtext1.txt");
			WeekSchedule sampleWeekSched = sm.generateOneSched(cos.getCourseOffers());
			System.out.println(sampleWeekSched);
		} catch (NoSuchElementException e) {
			System.out.println("it's 2am! site's down :(");
		} catch (FileNotFoundException e) {
			System.out.println("can't find file!\t" + e);
		} catch (IOException e) {
			System.out.println("something wrong here");
		} finally {
			cc.close();
		}
		
		// enlistment; can't develop on this end without access yet
/*		try {
			System.out.print("enter filename for animoSys: ");
//			BufferedReader brLogin = new BufferedReader(new InputStreamReader(new FileInputStream(sc.nextLine() + ".txt")));
			BufferedReader brLogin = new BufferedReader(new InputStreamReader(new FileInputStream("login.txt")));
			cc.animoSysLogIn(brLogin.readLine(), brLogin.readLine());
//			cc.aSysEnlistSched();
			brLogin.close();
			
			cc.aSysAddClass(100);
			
		} catch (FileNotFoundException e) {
			System.out.println("can't find file!\n" + e);
		} catch (IOException e) {
			System.out.println("file error! this bad.\n" + e);
		}
		
		
		cc.googleImgScrape("cheese");
		cc.close();
*/		sc.close();
		
	}
}
