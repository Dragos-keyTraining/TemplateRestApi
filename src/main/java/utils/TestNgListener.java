package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.internal.IResultListener;
/**
 * Class used for automatically log customization with methods start/end and timestamp
 * Needs to be called on @Listeners annotation inside class or in listener tag from test-ng.xml 
 *
 */
public class TestNgListener implements IResultListener {

	/**
	 * Method used to customize the log when a @Test method start
	 * Logs in both console and file logger
	 */
	public void onTestStart(ITestResult result) {
		Log.info("+++++++++++++++++++++++++++++++++++++++++++++");
		Log.info("+++ Started test case : " + result.getMethod().getMethodName() );
		Log.info("++++++++++++++++++++++++++++++++++++++++++++++");
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		Log.info("Start time : " + timeStamp);
		Log.info("++++++++++++++++++++++++++++++++++++++++++++++");

	}
	/**
	 * Method used to customize the log when a @Test method finishes the execution with status PASS
	 * Logs in both console and file logger
	 */
	public void onTestSuccess(ITestResult result) {
		Log.info("+++++++++++++++++++++++++++++++++++++++++++++");
		Log.info("+++ Pass test case : " + result.getMethod().getMethodName() );
		Log.info("++++++++++++++++++++++++++++++++++++++++++++++");
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		Log.info("End time : " + timeStamp);
		Log.info("--------------------------------------------------");
	}
	/**
	 * Method used to customize the log when a @Test method finishes the execution with status FAILED
	 * Logs in both console and file logger
	 */
	public void onTestFailure(ITestResult result) {
		Log.error("+++++++++++++++++++++++++++++++++++++++++++++");
		Log.error("+++ Failed test case : " + result.getMethod().getMethodName() );
		Log.error("++++++++++++++++++++++++++++++++++++++++++++++");
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		Log.error("Failed time : " + timeStamp);
		Log.error(result.getThrowable());
		Log.error("++++++++++++++++++++++++++++++++++++++++++++++");
	}
	/**
	 * Method used to customize the log when a @Test method is SKIPED
	 * Logs in both console and file logger
	 */
	public void onTestSkipped(ITestResult result) {
		Log.info("+++++++++++++++++++++++++++++++++++++++++++++");
		Log.info("+++ Skipped test case : " + result.getMethod().getMethodName() );
		Log.info("++++++++++++++++++++++++++++++++++++++++++++++");

	}
	
	
	

}
