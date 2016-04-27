/**
 * This class is a testNG listener class which will call the UpdateTestLink class after test execution
 *  This class will pass the test execution result as an argument to update Testlink
 * @author Sampath and Praveen
 * @since - 14/12/15
 */
package com.testlink;

import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import org.testng.reporters.JUnitReportReporter;
import com.reports.CreateReport;


public class ListenerClass extends JUnitReportReporter implements ITestListener {
	


	@Override
	public String getTestName(ITestResult result) {
		Map<String, String> params = result.getTestClass().getXmlTest()
				.getTestParameters();
		StringBuilder ret = new StringBuilder();
		ret.append(result.getMethod().getMethodName() + "(");
		for (String param : params.keySet()) {
			String value = params.get(param);
			if (value != null) {
				ret.append(value);
				ret.append(",");
			}
		}
		return (ret.substring(0, ret.length() - 1) + ")");
	}


	public void onFinish(ITestContext arg0) {
		String templatePath = "/Users/QE/Documents/SikuliJavaWorkspace/SeleniumGrid/src/com/utilities/base_template.html";
		String resultReportPath = "/Users/QE/Documents/HTMLReports/report.html";
		
		CreateReport report=new CreateReport();
		
		report.writeReports(templatePath,resultReportPath);
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

}