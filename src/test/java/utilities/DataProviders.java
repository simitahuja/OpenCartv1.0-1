package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException 
	{
		String path=".\\testData\\Opencart_LoginData.xlsx"; //taking xl file from test data
		
		ExcelUtils xlutil= new ExcelUtils(path); //creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcols];  //created for 2-D array which store
		
		for(int i=1;i<=totalrows;i++)
		{
			for (int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		return logindata;
	}

}
