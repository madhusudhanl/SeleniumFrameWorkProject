package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	
	public static ExtentReports getExtentReportObject() {
		
		String reportPath = System.getProperty("user.dir")+"\\extentReports\\index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("Web Automation");
		reporter.config().setDocumentTitle("Test case Results");
		
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Madhu");
		
		return extent;
	}

}
