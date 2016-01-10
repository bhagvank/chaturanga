/**
 * 
 */
package org.chaturanga.chess.game;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.chaturanga.chess.util.PGNUtil;

/**
 * @author bhagvan_kommadi
 *
 */
public class GameManager
{
	private PGNUtil pgnUtil = new PGNUtil();

	public List<List<String>> getGames(List<String> pgnStream,String resultFilter)
	{
		
		List<List<String>> games = new ArrayList();
	    //System.out.println(pgnStream.size());
		int i=0;
		while(i< pgnStream.size())
		{
			String pgnLine = pgnStream.get(i);
			boolean resultGameFilter = false;
			if(pgnLine.contains("[Result"))
			{
              StringTokenizer resultTokenizer = new StringTokenizer(pgnLine," ");
              
              String resultStr = resultTokenizer.nextToken();
              String resultToken = resultTokenizer.nextToken();
              String result = resultToken.substring(0, resultToken.indexOf("]"));
            	  String resultGame = '"'+resultFilter+'"';
            	  
            	  //System.out.println(result + " : "+resultGame);
            	  
            	  if(resultGame.contains(result))
            	  {
            		  resultGameFilter = true;
            		//  System.out.println(resultGameFilter);
            	  }
                 
            	  pgnLine = pgnStream.get(i+3);
			}
			if(pgnLine.contains("[ECO") && resultGameFilter)
			{
		        //System.out.println("ECO"+pgnLine+"lineNumber"+i);
				List<String> gameMoves = new ArrayList<String>();

				pgnLine = pgnStream.get(i+5);
				//System.out.println(pgnLine);
				int j= i+5;
				 boolean starFlag = true;
				 
				while( starFlag && pgnLine.trim().length() !=0  && j < pgnStream.size())
				{
					//System.out.println(pgnLine);
				  
					   if(pgnLine.contains("*"))
					   {
						   starFlag = false;
						   List<String> pgnLineMoves = pgnUtil.getMoves(pgnLine);
						   
						    gameMoves.addAll(pgnLineMoves);
						    i=j;
					   }
					   else
					   {
					    List<String> pgnLineMoves = pgnUtil.getMoves(pgnLine);
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

	public String getResult(String pgnLine)
	{
		//List<String> moves = new ArrayList<String>();
		
		String result = null;
		//System.out.println(pgnLine);
		
		StringTokenizer tokenizer = new StringTokenizer(pgnLine,"  ");
		//System.out.println(tokenizer.countTokens());
		while(tokenizer.hasMoreElements())
		{
			String token = tokenizer.nextToken();
			//System.out.println(token);
			
			if(token.contains("."))
			{
			 //int dotIndex = token.indexOf(".");
			
			//moves.add(token.substring(dotIndex+1));
			}
			else if(token.contains("1-0") || token.contains("1/2-1/2") || token.contains("0-1"))
			{
				result = token;
			}
			else if(token.contains("*"))
			{
				
			}
			else
			{
				//moves.add(token);
			}
		}
		return result;
	}

}
