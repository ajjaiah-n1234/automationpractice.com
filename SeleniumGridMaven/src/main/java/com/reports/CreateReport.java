package com.reports;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestResult;

public class CreateReport {

	@SuppressWarnings("rawtypes")
	public static void writeReports(String templatePath,String resultReportPath) {

		String resultPlaceholder = "<!-- INSERT_RESULTS -->";
		String startDatePlaceHolder="<!--START_TIME-->";
		String durationPlaceHolder="<!--DURATION-->";
		String statusPlaceHolder="<!--STATUS-->";
		String suiteResultsPlaceHolder="<!-- INSERT_SUITE_RESULTS -->";
		ReportSupport classData=new ReportSupport();
		List<Map<String, List<ITestContext>>> reportData=classData.getData();
		
		int count=0;
		//System.out.println("Size of list--------->"+reportData.size());
		int TotalNoOfTCs=0;
		int TotalNoPassedTCs=0;
		int TotalNoFailedTCs=0;
		int TotalNoSkippedTCs=0;
		double passedPercentage=0;
		double failedPercentage=0;
		double skippedPercentage=0;		
		double totalPassedPercentage=0;
		double totalFailedPercentage=0;
		double totalSkippedPercentage=0;	
		
		String rowColor=null;
		String className=null;
		DecimalFormat df = new DecimalFormat("#.##");
		
		try{
			//delete previous result file, if already exists
			File resultFile=new File(resultReportPath);
			
			if(resultFile.exists()){
				resultFile.delete();
			}

			String reportIn = new String(Files.readAllBytes(Paths.get(templatePath)));
			
			ReportSupport reportSupport=new ReportSupport();
			

			for(Map<String, List<ITestContext>> completeData:reportData){


				Set<Entry<String, List<ITestContext>>> set = completeData.entrySet();
				Iterator<Entry<String, List<ITestContext>>> iterator = set.iterator();
				

				while(iterator.hasNext()) {

					Map.Entry mentry = (Map.Entry)iterator.next();
					
					

					int TotalNoOfTCPerSuite=completeData.get(mentry.getKey()).get(count).getAllTestMethods().length;
					int PassedTCPerSuite=completeData.get(mentry.getKey()).get(count).getPassedTests().size();
					int FailedTCPerSuite=completeData.get(mentry.getKey()).get(count).getFailedTests().size();
					int SkippedTCPerSuite=completeData.get(mentry.getKey()).get(count).getSkippedTests().size();
					int colValue=count+1;

					Set<ITestResult> testsPassed=completeData.get(mentry.getKey()).get(count).getPassedTests().getAllResults();
					Set<ITestResult> testsFailed=completeData.get(mentry.getKey()).get(count).getFailedTests().getAllResults();
					Set<ITestResult> testsSkipped=completeData.get(mentry.getKey()).get(count).getSkippedTests().getAllResults();

					if(testsFailed.size()!=0){
						rowColor="#DC143C";
						className="errorClass";
					}else if(testsPassed.size()!=0){
						rowColor="#008000";
						className="passClass";
					}else{
						rowColor="#B0C4DE";
					}
					
					if(PassedTCPerSuite!=0){
						passedPercentage=Double.valueOf((df.format((PassedTCPerSuite*100)/(double)TotalNoOfTCPerSuite)));	
						totalPassedPercentage=totalPassedPercentage+passedPercentage;
					}else{
						passedPercentage=0;
					}
					if(FailedTCPerSuite!=0){
						failedPercentage=Double.valueOf((df.format((FailedTCPerSuite*100)/(double)TotalNoOfTCPerSuite)));
						
						totalFailedPercentage=totalFailedPercentage+failedPercentage;
					}else{
						failedPercentage=0;
					}
					if(SkippedTCPerSuite!=0){
						skippedPercentage=Double.valueOf((df.format((SkippedTCPerSuite*100)/(double)TotalNoOfTCPerSuite)));	
						totalSkippedPercentage=totalSkippedPercentage+skippedPercentage;
						
					}else{
						skippedPercentage=0;
					}


					String detailJS="<a href=" + "\"javascript:showClassDetail('c"+ colValue + "',"+ TotalNoOfTCPerSuite + ")\" "+ ">";
					
					if(!testsPassed.isEmpty()){
						
					}
						
					
					reportIn = reportIn.replaceFirst(resultPlaceholder,"<tr class='"+className+"' bgcolor="+ rowColor+"><td><b>" + mentry.getKey() + "</td><td>" + TotalNoOfTCPerSuite + "</td><td>" + PassedTCPerSuite + "</td><td>" + FailedTCPerSuite +"</td><td>"+ SkippedTCPerSuite +"</td><td>"+ detailJS + "Detail" + "</td></tr>" + resultPlaceholder);
					reportIn = reportIn.replaceFirst(suiteResultsPlaceHolder,"<tr><td><b>" + mentry.getKey() + "</td><td>" + passedPercentage+"%"+ "</td><td>" +failedPercentage+"%"+"</td><td>" +skippedPercentage+"%"+ "</td></tr>" + suiteResultsPlaceHolder);

					int countOfTC=1;
					for (ITestResult testPass : testsPassed){
						reportIn = reportIn.replaceFirst(resultPlaceholder,"<tr id='pt"+colValue+"."+countOfTC+"' class='hiddenRow'><td class='none'><div class='testcase'>"+reportSupport.getTestName(testPass)+"</div></td><td colspan='5' align='center' bgcolor='#006400'  border: 1px>"+"Pass"+"</td></tr>"+ resultPlaceholder);
						//System.out.println("TestcaseName " +testPass.getTestClass().getName());
						countOfTC++;
					}
					
					int failCount=1;
					for (ITestResult testFail : testsFailed){
						
						String firstLine= "<a class='popup_link' onfocus='this.blur();' href="+"\"javascript:showTestDetail('div_ft"+count+"."+ failCount +"')\""+" > Fail</a>";
						String secondLine="<div id='div_ft"+count+"."+ failCount +"' class='popup_window' style='display: none;'> <div style='text-align: right; color:red;cursor:pointer'> <a onfocus='this.blur();' onclick="+"\"document.getElementById('div_ft"+count+"."+ failCount +"').style.display = 'none' \""+">[x]</a></div>";
						String thirdline="<pre>"+ testFail.getThrowable() +"</pre></div>";
								
						reportIn = reportIn.replaceFirst(resultPlaceholder,"<tr id='ft"+colValue+"."+countOfTC+"' class='hiddenRow'><td class='errorCase'><div class='testcase'>"+reportSupport.getTestName(testFail)+"</div></td><td colspan='5' align='center' bgcolor='#DC143C'  border: 1px >"+firstLine +secondLine+thirdline+"</td></tr>"+ resultPlaceholder);
						countOfTC++;
						//testFail.
						//System.out.println("Failure log --------->>>>>>"+testFail.getThrowable()) ;
						failCount++;
					}

					for (ITestResult testSkip : testsSkipped){
						reportIn = reportIn.replaceFirst(resultPlaceholder,"<tr id='pt"+colValue+"."+countOfTC+"' class='hiddenRow'><td class='skipCase'><div class='testcase'>"+reportSupport.getTestName(testSkip)+"</td><td colspan='5' align='center' bgcolor='#B0C4DE'  border: 1px>"+"Skip"+"</td></tr>"+ resultPlaceholder);
						countOfTC++;
					}

					TotalNoOfTCs=TotalNoOfTCs+TotalNoOfTCPerSuite;
					TotalNoPassedTCs=TotalNoPassedTCs+PassedTCPerSuite;
					TotalNoFailedTCs=TotalNoFailedTCs+FailedTCPerSuite;
					TotalNoSkippedTCs=TotalNoSkippedTCs+SkippedTCPerSuite;
					count++;
				}
			}

			reportIn = reportIn.replaceFirst(resultPlaceholder,"<tr><td>" + "Total" + "</td><td>" + TotalNoOfTCs + "</td><td>" + TotalNoPassedTCs + "</td><td>" + TotalNoFailedTCs +"</td><td>"+ TotalNoSkippedTCs +"</td><td>" +"</td></tr>"+ resultPlaceholder);

			reportIn = reportIn.replaceFirst(suiteResultsPlaceHolder,"<tr id='total_row'><td><b>" + "Total" + "</td><td>" + Double.valueOf(df.format(totalPassedPercentage/count)) +"%"+ "</td><td>" +Double.valueOf(df.format(totalFailedPercentage/count))+"%"+"</td><td>" +Double.valueOf(df.format(totalSkippedPercentage/count))+"%"+ "</td></tr>" + suiteResultsPlaceHolder);

			reportIn =reportIn.replaceFirst(startDatePlaceHolder,"<p class='attribute'><strong>Start Time:</strong>"+ reportSupport.getStartDate()+"</p>"+ startDatePlaceHolder);
			reportIn =reportIn.replaceFirst(durationPlaceHolder,"<p class='attribute'><strong>Duration:</strong>"+ reportSupport.getDuration()+"</p>"+ durationPlaceHolder);
			
			reportIn =reportIn.replaceFirst(statusPlaceHolder,"<p class='attribute'><strong>Status:</strong>" + " Pass " + + TotalNoPassedTCs + " Fail "+ TotalNoFailedTCs+ " Skip "+ TotalNoSkippedTCs +"</p>"+ statusPlaceHolder);

			//String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			//String reportPath = "/Users/QE/Desktop/report_" + currentDate + ".html";
			
			Files.write(Paths.get(resultReportPath),reportIn.getBytes(),StandardOpenOption.CREATE);

		} catch (Exception e) {
			System.out.println("Error when writing report file:\n" + e.toString());
		}


	}
}
