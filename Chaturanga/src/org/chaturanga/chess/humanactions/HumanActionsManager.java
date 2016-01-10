/**
 * 
 */
package org.chaturanga.chess.humanactions;

import java.util.ArrayList;
import java.util.List;

import org.chaturanga.chess.util.OutputUtil;

/**
 * @author bhagvan_kommadi
 *
 */
public class HumanActionsManager
{

	public List<List<String>> matchPGNWithHumanAction(List<String> gameMoves, List<List<String>> history, int humanActions)
	{
		List<List<String>> match = new ArrayList();
		
		StringBuffer gameBuffer = new StringBuffer();
		
		int index=0;
		int moveNumber=1;
		
		for(String gameMove: gameMoves)
		{
		   if(index < humanActions*2)
		   {
			   
			if(Math.floorMod(index, 2) ==0)
			{
			   gameBuffer.append(String.valueOf(moveNumber)+"."+gameMove+" ");
			   moveNumber++;
			}
			else
			{
				gameBuffer.append(gameMove+" ");
			}
		   }
		   index++;
		}
		
		String gameString = gameBuffer.toString();
		int matchedGames=0;
		for(List<String> historygameMoves : history)
		{
			StringBuffer historygameBuffer = new StringBuffer();
			
			int i=0;
			int historygameMoveNumber=1;
			
			
			for(String historygameMove: historygameMoves)
			{
				
					if(Math.floorMod(i,2) == 0)
					{
						historygameBuffer.append(String.valueOf(historygameMoveNumber)+"."+historygameMove+" ");
						historygameMoveNumber++;
					}
					else
					{
				     historygameBuffer.append(historygameMove+" ");
					}
				
				i++;
			}
			
			String historygameString = historygameBuffer.toString();
			//System.out.println(historygameString);
			
			if(historygameString.startsWith(gameString))
             {
            	 System.out.println(gameString);
            	 OutputUtil.getWriter().println(gameString);
            	 System.out.println(historygameString);
            	 OutputUtil.getWriter().println(historygameString);
            	 match.add(historygameMoves);
            	 matchedGames++;
             }
		}
		
		System.out.println(matchedGames);
		OutputUtil.getWriter().println("MatchedGames Number:"+matchedGames);
		return match;
	}
}
