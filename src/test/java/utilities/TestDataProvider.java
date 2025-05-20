package utilities;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

	static String filePath = ConfigReader.getPropertyValue("EXCELPATH");

	@DataProvider(name = "pythonCode")
	public static List<Map<String, String>> getAllCodeData() {
	    String sheetName = "PythonCode";
	    ExcelReader reader = new ExcelReader(filePath);
	    return reader.getDataAll(sheetName);
	}
	
//	@DataProvider(name = "ValidLoginData")
//	public Object[][] getLoginData() {
//		String sheetName = "Login";
//		String validationType = "ValidCredential";
//		String usernameTestData = null;
//		String passwordTestData = null;
//		List<Map<String, String>> testData;
//		ExcelReader reader = new ExcelReader(filePath);
//		testData = reader.getDataAll(sheetName);
//
//		for (Map<String, String> row : testData) {
//			String validationTestData = row.get("validation");
//
//			if (validationType.equalsIgnoreCase(validationTestData)) {
//				usernameTestData = row.get("username");
//				passwordTestData = row.get("password");
//				break;
//			}
//		}
//		return new Object[][] { { usernameTestData, passwordTestData } };
//	}
//
//	@DataProvider(name = "EmptyCode")
//	public Object[][] getEmptyCode() {
//		String sheetName = "PythonCode";
//		String validationType = "Empty";
//		String emptyCode = null;
//		String expectedResults = null;
//		List<Map<String, String>> testData;
//		ExcelReader reader = new ExcelReader(filePath);
//		testData = reader.getDataAll(sheetName);
//
//		for (Map<String, String> row : testData) {
//			String validationTestData = row.get("codeValidations");
//
//			if (validationType.equalsIgnoreCase(validationTestData)) {
//				emptyCode = row.get("code");
//				expectedResults = row.get("expectedResults");
//				break;
//			}
//		}
//		return new Object[][] {{emptyCode, expectedResults}};
//	}
//	
//	@DataProvider(name = "ValidCode")
//	public Object[][] getValidCode() {
//		String sheetName = "PythonCode";
//		String validationType = "Valid";
//		String validCode = null;
//		String expectedResults = null;
//		List<Map<String, String>> testData;
//		ExcelReader reader = new ExcelReader(filePath);
//		testData = reader.getDataAll(sheetName);
//
//		for (Map<String, String> row : testData) {
//			String validationTestData = row.get("codeValidations");
//
//			if (validationType.equalsIgnoreCase(validationTestData)) {
//				validCode = row.get("code");
//				expectedResults = row.get("expectedResults");
//				break;
//			}
//		}
//		return new Object[][] {{validCode, expectedResults}};
//	}
	
//	@DataProvider(name = "invalidCode")
//	public Object[][] getInvalidCode() {
//		String sheetName = "PythonCode";
//		String validationType = "Invalid";
//		String invalidCode = null;
//		String expectedResults = null;
//		List<Map<String, String>> testData;
//		ExcelReader reader = new ExcelReader(filePath);
//		testData = reader.getDataAll(sheetName);
//
//		for (Map<String, String> row : testData) {
//			String validationTestData = row.get("codeValidations");
//
//			if (validationType.equalsIgnoreCase(validationTestData)) {
//				invalidCode = row.get("code");
//				expectedResults = row.get("expectedResults");
//				break;
//			}
//		}
//		return new Object[][] {{invalidCode, expectedResults}};
//	}
	

	    // Use a list to collect all rows that match validation
//	    List<Object[]> data = new ArrayList<>();
//
//	    for (Map<String, String> row : testData) {
//	        String validationTestData = row.get("codeValidations");
//	        String pythonCode = row.get("code");
//	        String expectedResults = row.get("expectedResults");
//
//	        // You can apply a filter if needed, for example:
//	        // if (validationTestData.equalsIgnoreCase("Invalid")) { ... }
//
//	        data.add(new Object[] { validationTestData, pythonCode, expectedResults });
//	    }
//
//	    // Convert List<Object[]> to Object[][]
//	    return data.toArray(new Object[][] {});
	
	
}
