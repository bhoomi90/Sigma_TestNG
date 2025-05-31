package utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;


public class TestDataProvider {

	private static final String filePath = ConfigReader.getPropertyValue("EXCELPATH");
	private static final ExcelReader reader = new ExcelReader(filePath);
	private static final Map<String, List<Map<String, String>>> sheetDataCache = new HashMap<>();

	static {
		sheetDataCache.put("Login", reader.getDataAll("Login"));
		sheetDataCache.put("PythonCode", reader.getDataAll("PythonCode"));
		sheetDataCache.put("Array", reader.getDataAll("Array"));
		sheetDataCache.put("Register", reader.getDataAll("Register"));
	}
	
	public Object[][] getLoginTestDataByValidationType(String validationType) {
		List<Map<String, String>> loginData = sheetDataCache.get("Login");

		for (Map<String, String> row : loginData) {
			String validationTestData = row.get("validation");

			if (validationType.equalsIgnoreCase(validationTestData)) {
				return new Object[][] { { row.get("username"), row.get("password"), row.get("message") } };
			}
		}
		throw new RuntimeException("No test data found for validation type: " + validationType);
	}

	@DataProvider(name = "ValidLoginData")
	public Object[][] getValidLoginData() {
		return getLoginTestDataByValidationType("ValidCredential");
	}

	@DataProvider(name = "InvalidUserLoginData")
	public Object[][] getInvalidUserLoginData() {
		return getLoginTestDataByValidationType("InvalidUsername");
	}

	@DataProvider(name = "InvalidPWLoginData")
	public Object[][] getInvalidPWLoginData() {
		return getLoginTestDataByValidationType("InvalidPassword");
	}

	@DataProvider(name = "EmptyUserLoginData")
	public Object[][] getEmptyUserLoginData() {
		return getLoginTestDataByValidationType("EmptyUsername");
	}

	@DataProvider(name = "EmptyPWLoginData")
	public Object[][] getEmptyPWLoginData() {
		return getLoginTestDataByValidationType("EmptyPassword");
	}

	public Object[][] getRegisterTestDataByValidationType(String validationType) {
		List<Map<String, String>> registerData = sheetDataCache.get("Register");

		for (Map<String, String> row : registerData) {
			String validationTestData = row.get("validation");

			if (validationType.equalsIgnoreCase(validationTestData)) {
				return new Object[][] { { row.get("username"), row.get("password"), row.get("confirmpassword"), row.get("message") } };
			}
		}
		throw new RuntimeException("No test data found for validation type: " + validationType);
	}
	
	@DataProvider(name="EmptyUserRegisterData")
	public Object[][] getEmptyUserRegisterData() {
		return getRegisterTestDataByValidationType("EmptyUsername");
	}
	
	@DataProvider(name="EmptyPWRegisterData")
	public Object[][] getEmptyPWRegisterData() {
		return getRegisterTestDataByValidationType("EmptyPassword");
	}
	
	@DataProvider(name="EmptyConfirmPWRegisterData")
	public Object[][] getEmptyConfirmPWRegisterData() {
		return getRegisterTestDataByValidationType("EmptyConfirmPassword");
	}
	
	@DataProvider(name="MismatchPWRegisterData")
	public Object[][] getMismatchPWRegisterData() {
		return getRegisterTestDataByValidationType("MismatchPassword");
	}
	
	@DataProvider(name="ShortPWRegisterData")
	public Object[][] getShortPWRegisterData() {
		return getRegisterTestDataByValidationType("ShortPassword");
	}
	
	@DataProvider(name="NumPWRegisterData")
	public Object[][] getNumPWRegisterData() {
		return getRegisterTestDataByValidationType("NumericPassword");
	}
	
	@DataProvider(name="ValidDataRegisterData")
	public Object[][] getValidDataRegisterData() {
		return getRegisterTestDataByValidationType("ValidCredential");
	}
		
	public Object[][] getPythonCodeDataByValidationType(String validationType) {
		List<Map<String, String>> codeData = sheetDataCache.get("PythonCode");

		for (Map<String, String> row : codeData) {
			String validationTestData = row.get("codeValidations");

			if (validationType.equalsIgnoreCase(validationTestData)) {
				return new Object[][] { { row.get("code"), row.get("expectedResults")} };
			}
		}
		throw new RuntimeException("No test data found for validation type: " + validationType);
	}
	
	@DataProvider(name="EmptyPythonCode")
	public Object[][] getEmptyPythonCodeData() {
		return getPythonCodeDataByValidationType("Empty");
	}
	
	@DataProvider(name="ValidPythonCode")
	public Object[][] getValidPythonCodeData() {
		return getPythonCodeDataByValidationType("Valid");
	}
	
	@DataProvider(name="InvalidPythonCode")
	public Object[][] getInvalidPythonCodeData() {
		return getPythonCodeDataByValidationType("Invalid");
	}
	
	@DataProvider(name = "pythonCode")
	public static List<Map<String, String>> getAllCodeData() {
		return sheetDataCache.get("PythonCode");
	}

	@DataProvider(name = "practiceQueCode")
	public static List<Map<String, String>> getAllpracticeQueCodeData() {
		return sheetDataCache.get("Array");
	}
	
}
	//public static Object[][] convertListTo2DArray(List<Map<String, String>> list) {
	//	Object[][] data = new Object[list.size()][1];
	//	for (int i = 0; i < list.size(); i++) {
		//	data[i][0] = list.get(i); // Each row gets one Map
		//}
		//return data;
	//}

	//@DataProvider(name = "TryeditorProvider")
	//public Object[][] getPythonData() throws InvalidFormatException, IOException {
		//String sheetName = "PythonCode";
		//ExcelReader reader = new ExcelReader(filePath);
		//String filePath = configReader.getProperty("FilePath");
		//String sheetName = configReader.getProperty("tryeditorSheet");
		//List<Map<String, String>> excelData = reader.getDataAll(sheetName);
		//return convertListTo2DArray(excelData);
	//}
//	@DataProvider(name = "loginData")
	//public static Object[][] getLoginData() throws IOException, InvalidFormatException {
	  //  String sheetName = "Login";
	  //  ExcelReader reader = new ExcelReader(filePath);
	   // List<Map<String, String>> testData = reader.getDataAll(sheetName);

	    //Object[][] data = new Object[testData.size()][1];
	    //for (int i = 0; i < testData.size(); i++) {
	       // data[i][0] = testData.get(i);
	   // }
	   // return data;
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
	
	


