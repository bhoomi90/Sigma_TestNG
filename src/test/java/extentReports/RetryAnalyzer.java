package extentReports;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 2;  //retry up to 2 timess
    
	@Override
	public boolean retry(ITestResult result) {
	  if (retryCount < maxRetryCount) {
		   retryCount++;
		   return true;  //retry failed test
	  }
		return false; // do not retry if passed or max retry reached
	}
	


}
