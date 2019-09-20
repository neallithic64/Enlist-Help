package c.enlistinghelp;

import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
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
			System.out.println("thread interrupted! " + e);
		}
	}
	public void pageWait() {
		pageWait(3);
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
		pageWait();
		
		System.out.println("scraping for " + query + "...");
		List<WebElement> imgURLs = siteDriver.findElements(By.className("rg_l"));
		
		File outFol = new File(query + "_images");
		outFol.mkdir();
		System.out.println("downloading " + imgURLs.size() + " images...");
		int countF = 0;
		for (int i = 0; i < imgURLs.size(); i++) {
			try {
				System.out.println(i + ": " + imgURLs.get(i).getAttribute("href"));
				siteDriver.get(imgURLs.get(i).getAttribute("href"));
				
/*				URL imageURL = new URL(img);
				// read url and retrieve image
				BufferedImage saveImage = ImageIO.read(imageURL);
				// this will create an image with new name each time
				ImageIO.write(saveImage, "jpg", new File(outFol.getPath() + "\\img_" + String.format("%05d", countF) + ".jpg"));
				countF++;
*/			} catch (/*IO*/Exception e) {
			}
		}
		
	}
	
	public void printList(List<String> list) {
		for (int i = 0; i < list.size(); i++)
			System.out.println(list.get(i));
	}
	
	public List<String> coScrape(String course) {
		siteDriver.get("http://enroll.dlsu.edu.ph/dlsu/view_actual_count");
		siteDriver.findElement(By.name("p_course_code")).sendKeys(course);
		siteDriver.findElement(By.name("p_course_code")).submit();
		
		String tableText = siteDriver.findElement(By.xpath("/html/body/table[4]/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr/td[2]/center[1]")).getAttribute("innerHTML");
		
		Document d = Jsoup.parse(tableText);
		Elements e = d.select("tr");
		List<String> listStrings = e.eachText();
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
		try {
			siteDriver.get("https://animo.sys.dlsu.edu.ph/psp/ps/EMPLOYEE/HRMS/c/SA_LEARNER_SERVICES.SSR_SSENRL_CART.GBL");
			siteDriver.switchTo().frame(siteDriver.findElement(By.id("ptifrmtgtframe")));
			for (int i = 0; i < 5 /*n*/; i++) {
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
		} catch (NoSuchElementException e) {
			System.out.println("bad page loading, siteDriver report: " + siteDriver.getCurrentUrl() + "\n"
					+ "exiting to google...");
			siteDriver.get("https://google.com/");
		}
	}
	
	public void aSysEnlistSched(WeekSchedule sched) {
		siteDriver.get(null);
		
	}
	
	public void close() {
		siteDriver.quit();
	}
}
