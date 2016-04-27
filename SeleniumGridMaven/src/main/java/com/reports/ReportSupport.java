package com.reports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.reporters.JUnitReportReporter;


public class ReportSupport extends JUnitReportReporter implements IExecutionListener{

	public static long lStartTime;
	public static long EndTime;
	public static String duration;
	public static String startDate;
	
	String className;
	public static List<Map<String, List<ITestContext>>> resultlist=new ArrayList<Map<String,List<ITestContext>>>();
	
	@Override
	public String getTestName(ITestResult result) {
		
		Map<String, String> params = result.getTestClass().getXmlTest().getTestParameters();
		
		StringBuilder ret = new StringBuilder();
		ret.append(result.getMethod().getMethodName() + "(");
		
		for (String param : params.keySet()) {
			
			if(param.equalsIgnoreCase("browser")){
				ret.append(params.get(param));
			}
			
		}
		return (ret.substring(0, ret.length()) + ")");
	}
	
	
//@Override
	public void onExecutionStart() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
	    String StartDate = sdf.format(now);
	    lStartTime = new Date().getTime();
	    setStartTime(lStartTime);
	    setStartDate(StartDate);
	    
	}

	public void setStartDate(String startDate) {
		ReportSupport.startDate=startDate;
		
	}
	
	public String getStartDate(){
		return startDate;
	}

	private void setStartTime(long lStartTime2) {
		lStartTime=lStartTime2;
		
	}

	public long getStartTime(){
		return lStartTime;
		
	}
	
	//@Override
	public void onExecutionFinish() {
		long lEndTime = new Date().getTime();
		setEndTime(lEndTime);
		
	}

	private void setEndTime(long lEndTime) {
		EndTime=lEndTime;
		
	}
	public long getEndTime() {
		return EndTime;
		
	}
	
	public String getDuration(){
		long durationInMillis = EndTime - lStartTime;
		String duration =String.format("%d:%02d:%02d:%02d",durationInMillis / 60000/60, (durationInMillis / 60000)%60, (durationInMillis / 1000) % 60, (durationInMillis % 1000));
		return duration;
	}
	
	
	public void setclassName(String className){
		this.className=className;
		
	}
	
	public String getClassName(){
		return this.className;
	}
	
	public void setData(Map<String, List<ITestContext>> ResultData){
		resultlist.add(ResultData);
		
	}
	
	public List<Map<String, List<ITestContext>>> getData(){
		
		return resultlist;
	}
}
