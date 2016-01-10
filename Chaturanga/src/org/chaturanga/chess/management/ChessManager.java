/**
 * 
 */
package org.chaturanga.chess.management;

import java.util.List;

import org.chaturanga.chess.game.GameManager;
import org.chaturanga.chess.humanactions.HumanActionsManager;
import org.chaturanga.chess.util.OutputUtil;
import org.chaturanga.chess.util.PGNUtil;
import org.chaturanga.chess.util.PropertiesUtil;

/**
 * @author bhagvan_kommadi
 *
 */
public class ChessManager
{

   private static final String HUMANACTIONS = "humanactions";
   private static final String GAMEPGNFILE = "gamepgnfile";
   private static final String OUTPUTFILENAME = "outputfilename";
   private PropertiesUtil propertiesUtil = new PropertiesUtil();
   private String propertiesFileName = null;
   private PGNUtil pgnUtil = new PGNUtil();
   private HumanActionsManager humanActionsManager = new HumanActionsManager();
   private GameManager gameManager = new GameManager();
   
   public void init(String propertiesFile)
   {
	   
	   propertiesFileName = propertiesFile;
	   String outputFile = propertiesUtil.getValueFrom(propertiesFile, OUTPUTFILENAME);
		
	   
		  System.out.println(outputFile);
		  OutputUtil.setFileName(outputFile);
		  OutputUtil.getWriter().println("output starting");
		  
		  
		
   }
   
   public void match()
   {
	   String gamePgnFile = propertiesUtil.getValueFrom(propertiesFileName,GAMEPGNFILE);
		  
	   String humanActions = propertiesUtil.getValueFrom(propertiesFileName, HUMANACTIONS);
		  List<String> gamePgnLines =  pgnUtil.getPGNStream(gamePgnFile);
		  
		  List<String> results = propertiesUtil.getResultValuesFrom(propertiesFileName);
		  
			 // System.out.println(pgnLine);
			  List<List<String>> games = pgnUtil.getGames(gamePgnLines);
			  
			  List<String> game = games.get(0);
			     
	   List<String> pgnFileNames = propertiesUtil.getHistoryPGNValuesFrom(propertiesFileName);
		  
		  
		  for(String pgnFile : pgnFileNames)
		  {
			  System.out.println(pgnFile);
			  OutputUtil.getWriter().println("pgnFileName:" + pgnFile);
			  
		    List<String> pgnLines = pgnUtil.getPGNStream(pgnFile);
		    List<List<String>> historyGames = pgnUtil.getGames(pgnLines);
		    
		    System.out.println(historyGames.size());
		    OutputUtil.getWriter().println("TotalNumber of Games: "+historyGames.size());
		    
		    for(String resultFilter: results)
		    {
		      System.out.println(resultFilter);
		      OutputUtil.getWriter().println("Result:"+ resultFilter);
		      List<List<String>> filteredHistoryGames = gameManager.getGames(pgnLines, resultFilter);
		      System.out.println(filteredHistoryGames.size());
		      OutputUtil.getWriter().println("FilteredGames:"+filteredHistoryGames.size());
		      humanActionsManager.matchPGNWithHumanAction(game, filteredHistoryGames, Integer.parseInt(humanActions));
		    }
		  }
		  
		  OutputUtil.closeWriter();
   }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      System.out.println("Chess Manager Running");
      
      String propertiesFile = args[0];
      ChessManager chessManager = new ChessManager();
      chessManager.init(propertiesFile);
      chessManager.match();
    
	 
	  
	  //OutputUtil.getWriter().close();
	}

}
