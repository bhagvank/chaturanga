
/**
 * @author bhagvan_kommadi
 *
 */
package org.chaturanga.chess.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertiesUtil
{
 
	 private static final String PGNFILE = "pgnfile";
	private static final String PGNFILESNUMBER = "pgnfilesnumber";
	
	
	public List<String> getResultValuesFrom(String propertiesFileName)
	{
		Properties properties = getProperties(propertiesFileName);
		
		String resultsStr = properties.getProperty("resultnumber");
		
		int resultsno = Integer.parseInt(resultsStr);
		
		List<String> resultValues = new ArrayList<String>();
		
		for(int i=0; i< resultsno; i++)
		{
			String resultKey = "result"+String.valueOf(i+1);
			
			String resultValue = properties.getProperty(resultKey);
			
			if(resultValue != null)
			{
				//System.out.println(resultValue);
				resultValues.add(resultValue);
			}
		}
		
		return resultValues;
	}
	public List<String> getHistoryPGNValuesFrom(String propertiesFileName)
	 {
		 Properties properties = getProperties(propertiesFileName);
		 
		 String pgnfilesnumber = properties.getProperty(PGNFILESNUMBER);
		 
		 int pgnfilesno = Integer.parseInt(pgnfilesnumber);
		 
		 List<String> pgnFileNames = new ArrayList<String>();
		 
		 for(int i=0; i< pgnfilesno; i++)
		 {
			 String pgnFile = PGNFILE+String.valueOf(i+1);
			 
			 String pgnFileName = properties.getProperty(pgnFile);
			 
			 if(pgnFileName != null)
			 {
				// System.out.println(pgnFileName);
				 pgnFileNames.add(pgnFileName);
			 }
			 
		 }
		 
		 return pgnFileNames;
	 }
	 
	 public Properties getProperties(String fileName)
	 {
		 FileInputStream inputStream = null;
		 Properties properties = new Properties();
		 try
		 {
			 inputStream = new FileInputStream(fileName);
			 properties.load(inputStream);
		 }
		 catch(Exception exception)
		 {
			 exception.printStackTrace();
		 }
		 
		 return properties;
		 
	 }
	 public String getValueFrom(String propertiesFile, String key)
	 {
		 Properties properties = getProperties(propertiesFile);
		 
		 return properties.getProperty(key);
	 }
	 
	
}
