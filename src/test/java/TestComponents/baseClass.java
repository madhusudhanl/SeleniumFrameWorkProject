package TestComponents;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFrameWork.PageObject.LandingPage;

public class baseClass {

	public WebDriver driver;
	public LandingPage lp;

	public WebDriver initilizeDriver() throws IOException {

		// properties class

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// String browserName = prop.getProperty("browser");
		System.out.println(browserName);

		if (browserName.contains("chrome")) {

			ChromeOptions opt = new ChromeOptions();
			WebDriverManager.chromedriver().setup();

			if (browserName.equalsIgnoreCase("chromeheadless")) {
				System.out.println("chrome without body");
				opt.addArguments("headless");
				driver = new ChromeDriver(opt);
				driver.manage().window().setSize(new org.openqa.selenium.Dimension(1440, 900));
			} else {
				System.out.println("chrome with body");
				driver = new ChromeDriver();
			}

		} else if (browserName.equalsIgnoreCase("Edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	// Data Reader method
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// convert json to string
		String JsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// string to HashMap using jackson databind dependency
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(JsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
		// now this data has two hashMap values in it from json file.
	}

	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcfile = ts.getScreenshotAs(OutputType.FILE);
		File distfile = new File(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png");
		FileUtils.copyFile(srcfile, distfile);
		return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true) // this is will be the first method to execute
	public LandingPage startApplication() throws IOException {

		driver = initilizeDriver();
		lp = new LandingPage(driver);
		lp.goTo();
		return lp;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();

	}
}
