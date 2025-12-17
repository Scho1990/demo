package com.companyname.constants;

public class FrameworkConstants {
	
	private FrameworkConstants() {}
	
	private static final String CONFIG_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/config/config.properties";
	private static final String TEST_DATA_EXCEL_FILE_PATH = System.getProperty("user.dir") +"/src/test/resources/testdata/TestData.xlsx";
	public static final int EXPLICIT_WAIT = 20;
	
	public static String getConfigFilePath() {
		return CONFIG_FILE_PATH;
	}
	
	public static String getTestDataExcelFilePath() {
		return TEST_DATA_EXCEL_FILE_PATH;
	}
	
	public static int getExplicitWaitTime() {
		return EXPLICIT_WAIT;
	}

}
