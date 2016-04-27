package com.reports;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlSuite;


public class CustomReport extends TestListenerAdapter implements IReporter {

	ITestNGMethod[] allTest = null;
	ITestContext testContext = null;
	//List testcaseNames=null;
	List<ITestContext> completeTestsInfo = new ArrayList<ITestContext>();
	ReportSupport classData=new ReportSupport();
	String packageName="selenium.tests.";
	String resultPlaceholder = "<!-- INSERT_RESULTS -->";
	public Map<String, List<ITestContext>> completeData;

	//@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		
		for (ISuite suite : suites) {
			//suite.getParallel().
			Map<String, ISuiteResult> suiteResults = suite.getResults();

			for (String testName : suiteResults.keySet()) {
				
//				System.out.println(suiteResults);
//
//				System.out.println("Test case name >" + suiteResults.get(testName));

				ISuiteResult suiteResult = suiteResults.get(testName);
				
				
				
				testContext = suiteResult.getTestContext();
				
				Set<String> classNamesSet=new HashSet<String>();
				ITestNGMethod[] allTestMethods = testContext.getAllTestMethods();
				for (ITestNGMethod testngMethod : allTestMethods)
				{
					String classNames= testngMethod.getTestClass().getName();
					classNamesSet.add(classNames);
					//System.out.println("Only class Names-----"+classNames );
					//System.out.println("]]]]]]]]]]]]]]]]]"+testngMethod.getTestClass().getAfterSuiteMethods().toString());
				}
				
				//System.out.println("Class set--->>>  "+classNamesSet);
				//String browser = testContext.getCurrentXmlTest().getParameter("browser");
//				System.out.println("Browser name>>>>>>>>>>>>>>>>"+browser);
//				System.out.println("XML suit tests are -------->"+ testContext.getCurrentXmlTest().getClasses().get(0));
				//XmlClass a = testContext.getCurrentXmlTest().getClasses().get(0);
				//System.out.println("This is another one--->>>>"+a.getName());
				String classname=testContext.getCurrentXmlTest().getClasses().get(0).getName();
			
				
				//String classNameInfo=testContext.getCurrentXmlTest().getClasses().toString();
//				String classname=xmlSuites.iterator().next().getTests().iterator().next().getClasses().iterator().next().getName();
//				System.out.println("Class namesss-------->>>>>"+classname);
//				
				String[] classNameWithPackage=classname.split("\\.");
				String TestSuiteName=classNameWithPackage[classNameWithPackage.length-1];
				
				//System.out.println("This is the class from jeberson----------"+TestSuiteName);
				//String TestSuiteName=classNameInfo.substring(classNameInfo.indexOf(packageName)+packageName.length(),classNameInfo.indexOf("]",classNameInfo.indexOf(packageName)));
				
				completeTestsInfo.add(testContext);

				completeData=new HashMap<String, List<ITestContext>>();

				//System.out.println("Name of the test class---->>>>>"+TestSuiteName);
				completeData.put(TestSuiteName, completeTestsInfo);

				classData.setData(completeData);


			}

		}

	}

}
