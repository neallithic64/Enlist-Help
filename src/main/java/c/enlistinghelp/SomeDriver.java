package c.enlistinghelp;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import org.openqa.selenium.NoSuchElementException;

public class SomeDriver {
	public static void main(String[] args) {
		ChromeCrawler cc = new ChromeCrawler();
		CourseOfferingScraper cos = new CourseOfferingScraper();
		ScheduleMaker sm = new ScheduleMaker();
		
		try {
			BufferedReader brFlow = new BufferedReader(new InputStreamReader(new FileInputStream("flowchartTest.txt")));
			ArrayList<String[]> samp1 = cos.readCourseCode(brFlow);
			cos.dispFlow(samp1);
			
			if (samp1.size() != 1)
				for (int i = 0; i < samp1.size(); i++)
					for (String get : samp1.get(i)) {
						cos.parseList(cc.coScrape(get));
				}
			brFlow.close();
			
			cos.parseList(cc.coScrape("GEDANCE"));
			cos.parseList(cc.coScrape("CCINFOM"));
			cc.pageWait(2);
			cc.close();
			cos.dispCOffers();
			cos.writeTo("logtext1.txt");
		} catch (NoSuchElementException e) {
			System.out.println("it's 2am! site's down :(");
			cc.close();
		} catch (FileNotFoundException e) {
			System.out.println("can't find file!\n" + e);
		} catch (IOException e) {
			System.out.println("something wrong here");
			cc.close();
		}
		
		// enlsitment; can't develop on this end without access yet
/*		try {
			System.out.print("enter filename for animoSys: ");
			BufferedReader brLogin = new BufferedReader(new InputStreamReader(new FileInputStream("login.txt")));
			cc.animoSysLogIn(brLogin.readLine(), brLogin.readLine());
			brLogin.close();
			
			cc.aSysAddClass(100);
			
		} catch (FileNotFoundException e) {
			System.out.println("can't find file!\n" + e);
		} catch (IOException e) {
			System.out.println("file error! this bad.\n" + e);
		}
		
		
		cc.googleImgScrape("cheese");
		cc.close();
*/		
	}
}
