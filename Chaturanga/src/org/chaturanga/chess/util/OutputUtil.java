/**
 * 
 */
package org.chaturanga.chess.util;

import java.io.PrintWriter;

/**
 * @author bhagvan_kommadi
 *
 */
public class OutputUtil {

	private static PrintWriter writer = null;
	
	public static void setFileName(String fileName)
	{
		if(writer == null)
		{
			try
			{
			 writer = new PrintWriter(fileName);
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}
		}
		
	}
	public static PrintWriter getWriter()
	{
	
		return writer;
	}
	
	public static void closeWriter()
	{
		writer.close();
	}
}
