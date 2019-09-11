package c.enlistinghelp;

import java.util.Scanner;
import java.io.*;

public class SomeDriver {
	public static void main(String[] args) {
		ChromeCrawler cc = new ChromeCrawler();
		
		CourseOfferingScraper cos = new CourseOfferingScraper();
		
		String[] sampSer = "HUMAART\nMADSMAN\nMARKRTL\nMARKMGT\nBUSLAW2\nCSRGOVE\n".split("\n");
		for (String sampSer1 : sampSer)
			cos.parseList(cc.coScrape(sampSer1));
		
/*		cos.parseList(cc.coScrape("GEDANCE"));
		cos.parseList(cc.coScrape("CCINFOM"));
*/		cc.pageWait(2);
		cc.close();
		cos.dispCOffers();
		cos.writeTo("logtext1.txt");
		
		
		/*
		try {
			System.out.print("enter filename for animoSys: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("login.txt")));
			cc.animoSysLogIn(br.readLine(), br.readLine());
			br.close();
			
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
