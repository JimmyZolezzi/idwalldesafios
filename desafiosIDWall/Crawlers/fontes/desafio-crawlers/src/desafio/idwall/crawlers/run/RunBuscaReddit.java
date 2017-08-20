package desafio.idwall.crawlers.run;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import desafio.idwall.crawlers.BuscaRedditImpl;
import desafio.idwall.crawlers.IBuscaReddit;
import desafio.idwall.crawlers.PropriedadesCrawlers;
import desafio.idwall.crawlers.PropriedadesCrawlers.Propriedades;
import desafio.idwall.crawlers.beans.ThreadReddit;

/**
 * Classe responsavel por rodar a busca de reddit
 * 
 * @author ledzo
 *
 */
public class RunBuscaReddit {
	
	private final Logger log = Logger.getLogger(RunBuscaReddit.class);
	
	static{
		System.setProperty("phantomjs.binary.path",PropriedadesCrawlers.lerPropriedade(Propriedades.DRIVER_SELENIUM_PATH));
	}
	
	/**
	 * 
	 * 
	 * @param args Lista de subreddits ex: "askreddit;worldnews;cats" com maxUpvote 5000
	 */
	public static void main(String[] args) {
		if(args != null && args.length >= 1){
			String subreddits = args[0];
			String sMinupVote = PropriedadesCrawlers.lerPropriedade(Propriedades.MIN_UPVOTES);
			int minUpvotes = Integer.valueOf(sMinupVote);
			WebDriver driver = null;
			try{
				
				driver = new PhantomJSDriver();
				IBuscaReddit buscaReddit = new BuscaRedditImpl(driver);
				Map<String,List<ThreadReddit>> mapThreadReddit = buscaReddit.buscarThreadsRedditPorListaSubReddit(subreddits, minUpvotes);
				if(mapThreadReddit != null){
					Set<String> chaves = mapThreadReddit.keySet();
					for (String subreddit : chaves) {
						System.out.println("_________________________________________________________________________________________________________________________________________________");
						System.out.println("#Subreddit: " + subreddit);
						
						
						List<ThreadReddit> listaReddit = mapThreadReddit.get(subreddit);
						if(listaReddit != null && listaReddit.size() != 0){
							for (ThreadReddit threadReddit : listaReddit) {
								System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
								System.out.println("	Título      : " + threadReddit.getTitulo());
								System.out.println("	Upvotes     : " + threadReddit.getUpVotes());
								System.out.println("	Link Thread : " + threadReddit.getLinkThread());
								System.out.println("	Link Comentário : " + threadReddit.getLinkComentarios());
							}
						}else{
							System.out.println("	Sem resultados.");
						}
					}
					
				}
				
			}catch(Exception e){
			
				System.out.println("Ocorreu um erro na busca" + e);

			}finally{
				if(driver != null){
					driver.quit();
					
				}
			}
			
		
		}
	}

}
