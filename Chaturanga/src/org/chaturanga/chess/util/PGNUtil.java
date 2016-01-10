/**
 * 
 */
package org.chaturanga.chess.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author bhagvan_kommadi
 *
 */
public class PGNUtil {

	
	public List<List<String>> getGames(List<String> pgnStream)
	{
		
		List<List<String>> games = new ArrayList();
	    //System.out.println(pgnStream.size());
		int i=0;
		while(i< pgnStream.size())
		{
			String pgnLine = pgnStream.get(i);
			
			if(pgnLine.contains("[ECO") )
			{
		        // System.out.println("ECO"+pgnLine+"lineNumber"+i);
				List<String> gameMoves = new ArrayList<String>();

				pgnLine = pgnStream.get(i+2);
				//System.out.println(pgnLine);
				int j= i+2;
				 boolean starFlag = true;
				 
				while( starFlag && pgnLine.trim().length() !=0  && j < pgnStream.size())
				{
					//System.out.println(pgnLine);
				  
					   if(pgnLine.contains("*"))
					   {
						   starFlag = false;
						   List<String> pgnLineMoves = getMoves(pgnLine);
						   
						    gameMoves.addAll(pgnLineMoves);
						    i=j;
					   }
					   else
					   {
					    List<String> pgnLineMoves = getMoves(pgnLine);
					    gameMoves.addAll(pgnLineMoves);
					    
					    j++;
					   
					   }
					  // System.out.println(j);
					   if(j <pgnStream.size())
					   {
					     pgnLine = pgnStream.get(j);
					   }
					   //System.out.println(pgnLine);
				   
				  
				}
		
				games.add(gameMoves);
			}
			
			i++;
		}
		
		return games;
	}
	
	public List<String> getMoves(String pgnLine)
	{
		List<String> moves = new ArrayList<String>();
		
		//System.out.println(pgnLine);
		
		StringTokenizer tokenizer = new StringTokenizer(pgnLine,"  ");
		//System.out.println(tokenizer.countTokens());
		while(tokenizer.hasMoreElements())
		{
			String token = tokenizer.nextToken();
			//System.out.println(token);
			
			if(token.contains("."))
			{
			 int dotIndex = token.indexOf(".");
			
			moves.add(token.substring(dotIndex+1));
			}
			else if(token.contains("1-0") || token.contains("1/2-1/2") || token.contains("0-1"))
			{
				
			}
			else if(token.contains("*"))
			{
				
			}
			else
			{
				moves.add(token);
			}
		}
		return moves;
	}
	public List<String> getPGNStream(String pgnFileName)
	{
		List<String> pgnStream = new ArrayList<String>();
		
		BufferedReader bufferedReader = null;
		
		try
		{
		 bufferedReader = new BufferedReader( new FileReader(pgnFileName));
	
		String pgnLine = null;
		
		while((pgnLine = bufferedReader.readLine() ) != null)
		{
			//System.out.println(pgnLine);
			pgnStream.add(pgnLine);
		}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			if(bufferedReader != null)
			{
				try
				{
		         bufferedReader.close();	
				}
				catch(Exception exception)
				{
					exception.printStackTrace();
				}
			}
		}
		return pgnStream;
	}
}