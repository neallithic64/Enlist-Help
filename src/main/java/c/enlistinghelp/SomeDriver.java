package c.enlistinghelp;

import java.util.Scanner;
import java.io.*;

public class SomeDriver {
	public static void main(String[] args) {
		
		CourseOfferingScraper cos = new CourseOfferingScraper();
		ChromeCrawler cc = new ChromeCrawler();
		
		String[] sis = "HUMAART\nMADSMAN\nMARKRTL\nMARKMGT\nBUSLAW2\nCSRGOVE\n".split("\n");
		for (String sis1 : sis)
			cos.parseList(cc.scrape(sis1));
		
		cos.parseList(cc.scrape("GEDANCE"));
//		cos.parseList(cc.scrape("CCINFOM"));
		cc.pageWait(2);
		cc.close();
		cos.dispCOffers();
		cos.writeTo("logtext1.txt");
		
		
		/*
		Scanner sc = new Scanner(System.in);
		ChromeCrawler cc = new ChromeCrawler();
		
		try {
			System.out.print("enter filename for animoSys: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("login.txt")));
			cc.animoSysLogIn(br.readLine(), br.readLine());
			br.close();
			
			cc.aSysAddClass(100);
			
		} catch (FileNotFoundException e) {
			System.out.println("can't find file! this bad.\n" + e);
		} catch (IOException e) {
			System.out.println("file error! this bad.\n" + e);
		}
		
		cc.googleSearch("cheese");
		cc.pageWait(10);
		cc.close();
		*/
	}
}