package c.enlistinghelp;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ChromeCrawler {
	private WebDriver siteDriver;
	
	public ChromeCrawler() {
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		siteDriver = new ChromeDriver();
	}
	
	public WebDriver getSiteDriver() {
		return siteDriver;
	}
	
	public void open(String url) {
		siteDriver.get(url);
	}
	
	public void pageWait(int secs) {
		try {
			Thread.sleep((long) secs*1000);
		} catch (InterruptedException e) {
			System.out.println("yuck, it won't sleep! " + e);
		}
	}
	
	public void googleSearch(String query) {
		siteDriver.get("https://www.google.com");
		siteDriver.findElement(By.name("q")).sendKeys(query);
		pageWait(1);
		siteDriver.findElement(By.name("btnK")).click();
	}
	
	public void googleImgScrape(String query) {
		googleSearch(query);
		siteDriver.findElement(By.className("qs")).click();
	}
	
	public void printList(List<String> list) {
		for (int i = 0; i < list.size(); i++)
			System.out.println(list.get(i));
	}
	
	public List<String> scrape(String course) {
		siteDriver.get("http://enroll.dlsu.edu.ph/dlsu/view_actual_count");
		siteDriver.findElement(By.name("p_course_code")).sendKeys(course);
		siteDriver.findElement(By.name("p_course_code")).submit();
		
		String tableText = siteDriver.findElement(By.xpath("/html/body/table[4]/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/center[1]")).getAttribute("innerHTML");
		
		Document d = Jsoup.parse(tableText);
		Elements e = d.select("tr");
		List<String> listStrings = e.eachText();
//		System.out.println("\t\tjsoup data (size=" + listStrings.size() + "):\n");
//		printList(listStrings);
		
		return listStrings;
	}
	
	public void animoSysLogIn(String id, String pass) {
		siteDriver.get("https://animo.sys.dlsu.edu.ph/psp/ps/?cmd=login");
		siteDriver.findElement(By.name("userid")).sendKeys(id);
		siteDriver.findElement(By.name("pwd")).sendKeys(pass);
		pageWait(1);
		siteDriver.findElement(By.name("Submit")).click();
	}
	
	public void aSysAddClass(int n) {
		// assume that the user is already logged in
		siteDriver.get("https://animo.sys.dlsu.edu.ph/psp/ps/EMPLOYEE/HRMS/c/SA_LEARNER_SERVICES.SSR_SSENRL_CART.GBL");
		siteDriver.switchTo().frame(siteDriver.findElement(By.id("ptifrmtgtframe")));
		for (int i = 0; i < n; i++) {
			try {
				siteDriver.findElement(By.className("SSSBUTTON_CONFIRMLINK")).click();
				pageWait(2);
				siteDriver.findElement(By.name("DERIVED_REGFRM1_SSR_PB_SUBMIT")).click();
				pageWait(2);
				siteDriver.findElement(By.name("DERIVED_REGFRM1_SSR_LINK_STARTOVER")).click();
				pageWait(2);
			} catch (NoSuchElementException e) {
				System.out.println("error! log given here: " + siteDriver.navigate().toString() + "\n" + e);
			}
		}
	}
	
	public void close() {
		siteDriver.quit();
	}
}
