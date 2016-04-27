/**
 *This class contains the repository for objects
 *This class contains Key,Value pair of locators & corresponding values
 *
 *@author - Sampath & @modified - praveen
 *@since - 07/12/15
 */
package com.objects;

public class ObjRepo {

	// AutomationDemoTest_1
	public static String lnkSignIn = "xpath=//a[contains(text(),'Sign in')]";
	public static String txtLoginName = "id=email";
	public static String txtLoginPassword = "id=passwd";
	public static String btnSignin = "id=SubmitLogin";
	public static String personalInfo = "xpath=//span[text()='My personal information']";
	public static String oldPassword = "id=old_passwd";
	public static String newPassword = "id=passwd";
	public static String confirmPassword = "id=confirmation";
	public static String saveInfo = "xpath=//button[@name='submitIdentity']";
	public static String saveNotification = "xpath=//p[contains(text(),'Your personal information has been successfully updated.')]";
	public static String btnSignOut = "xpath=//a[@class='logout']";

	// AutomationDemoTest_2
	public static String mnuWomen = "xpath=//div[contains(@id,'block_top_menu')]/ul/li/a[@title='Women']";
	public static String secondItem = "xpath=(//div[@class='right-block']/h5/a)[4]";
	public static String btnAddToCart = "id=add_to_cart";
	public static String btnProceedToCheck = "xpath=//a[@title='Proceed to checkout']";
	public static String imgCloseWindow = "xpath=//div[@id='layer_cart']//span[@title='Close window']";
	public static String lnkShoppingCart = "xpath=//div[@class='shopping_cart']//b[text()='Cart']";
	public static String vluProductOne = "xpath=//table[@id='cart_summary']//tr[1]//input[@value='1']";
	public static String icnPlus = "xpath=//table[@id='cart_summary']//tr[1]//i[@class='icon-plus']";
	public static String vluProductTwo = "xpath=//table[@id='cart_summary']//tr[1]//input[@value='2']";
	public static String icnTrash = "xpath=//table[@id='cart_summary']//tr[1]//i[@class='icon-trash']";

	// AutomationDemoTest_3
	public static String lnkSummerDress = "xpath=//a[text()='Women']//..//..//li[@class='sfHoverForce']//a[text()='Summer Dresses']";
	public static String lnkSummerDressOne = "xpath=(//a[@title='Summer Dresses'])[2]";
	
	
	// AutomationDemoTest_4 
	public static String txtSearch = "xpath=//input[@id='search_query_top']";
	public static String btnSubmit = "name=submit_search";
	public static String proceedToCart = "xpath=(//*[span[contains(text(),'Proceed to checkout')]])[2]";
	public static String chkTermsAndCondition ="xpath=//div[@class='order_carrier_content box']//p[@class='checkbox']//input";
	public static String payByBank = "xpath=//a[contains(text(),'Pay by bank wire ')]";
	public static String confirmOrder = "xpath=//button[@class='button btn btn-default button-medium']";
	public static String lnkAccountName = "xpath=//a[@title='View my customer account']";
	public static String btnOrderHistory = "xpath=//li/a[@title='Orders']";
	public static String verifyOrderReference = "xpath=(//td[@class='history_link bold footable-first-column']//span)[1]";

	
	// AutomationDemoTest_5
	public static String mnuDresses = "xpath=//div[contains(@id,'block_top_menu')]/ul/li/a[@title='Dresses']";
	public static String ptdSort = "id=selectProductSort";
	public static String sortOption = "xpath=//select[@id='selectProductSort']/option[2]";
	public static String listIcon = "id=list";
	public static String gridIcon = "id=grid";
	public static String checkBox = "id=layered_id_attribute_group_2";
	public static String dressSecondItem = "xpath=(//ul[@class='product_list grid row']/li[contains(@class,'ajax_block_product')]//div[@class='right-block']/h5/a)[2]";
	public static String imgsBlockDress = "xpath=//div[@id='views_block']//ul/li[2]";
	public static String viewLarger = "xpath=//span[text()='View larger']";
	public static String lnkPrevious = "xpath=//div[@class='fancybox-outer']/a[@title='Previous']";
	public static String lnkNext = "xpath=//div[@class='fancybox-outer']/a[@title='Next']";
	public static String lnkClose = "xpath=//div[@class='fancybox-outer']//../a[@title='Close']";

	public static String btnPlus = "xpath=//div[@class='box-info-product']//p/a[contains(@class,'button-plus')]";
	public static String btnMinus = "xpath=//div[@class='box-info-product']//p/a[contains(@class,'button-minus')]";
	public static String selectSize = "xpath=//div[@class='box-info-product']//select";
	public static String lnkColorPick = "xpath=//div[@class='box-info-product']//ul[@id='color_to_pick_list']//a[@class='color_pick']";
	public static String lblWriteaReview = "xpath=//a[contains(text(),'Write a review')]";
	public static String starFiveRating = "xpath=//ul[@id='criterions_list']//a[@title='5']";
	public static String txtReviewTitle = "xpath=//input[@name='title']";
	public static String txtareaContent = "xpath=//textarea[@name='content']";
	public static String btnSend = "xpath=//button[@name='submitMessage']";
	public static String LabelComment = "xpath=//div[@class='fancybox-inner']//h2[text()='New comment']";
	public static String btnOk = "xpath=//div[@class='fancybox-inner']//button";
	
	

}
