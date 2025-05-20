package utilities;

import java.util.List;

import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.testng.annotations.DataProvider;
import java.io.IOException;
public class TestDataProvider {

	static String filePath = ConfigReader.getPropertyValue("EXCELPATH");

	
	public static Object[][] convertListTo2DArray(List<Map<String, String>> list) {
		Object[][] data = new Object[list.size()][1];
		for (int i = 0; i < list.size(); i++) {
			data[i][0] = list.get(i); // Each row gets one Map
		}
		return data;
	}

	@DataProvider(name = "TryeditorProvider")
	public Object[][] getPythonData() throws InvalidFormatException, IOException {
		String sheetName = "PythonCode";
		ExcelReader reader = new ExcelReader(filePath);
		//String filePath = configReader.getProperty("FilePath");
		//String sheetName = configReader.getProperty("tryeditorSheet");
		List<Map<String, String>> excelData = reader.getDataAll(sheetName);
		return convertListTo2DArray(excelData);
	}
	@DataProvider(name = "loginData")
	public static Object[][] getLoginData() throws IOException, InvalidFormatException {
	    String sheetName = "Login";
	    ExcelReader reader = new ExcelReader(filePath);
	    List<Map<String, String>> testData = reader.getDataAll(sheetName);

	    Object[][] data = new Object[testData.size()][1];
	    for (int i = 0; i < testData.size(); i++) {
	        data[i][0] = testData.get(i);
	    }
	    return data;
	}

	
	@DataProvider(name = "pythonCode")
	public static List<Map<String, String>> getAllCodeData() {
	    String sheetName = "PythonCode";
	    ExcelReader reader = new ExcelReader(filePath);
	    return reader.getDataAll(sheetName);
	}
	   
}