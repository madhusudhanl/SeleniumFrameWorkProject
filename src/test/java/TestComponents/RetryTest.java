package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer{

	int count = 0;
	int maxtry = 1;
	
	@Override
	public boolean retry(ITestResult arg0) {
		// TODO Auto-generated method stub
		if(count<maxtry){
			
			count++;
			return true;
		}
		return false;
	}

}
