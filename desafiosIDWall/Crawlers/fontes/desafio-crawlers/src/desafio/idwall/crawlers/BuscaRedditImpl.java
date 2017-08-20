package desafio.idwall.crawlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import desafio.idwall.crawlers.PropriedadesCrawlers.Propriedades;
import desafio.idwall.crawlers.beans.ThreadReddit;

public class BuscaRedditImpl implements IBuscaReddit{
	
	private final Logger log = Logger.getLogger(BuscaRedditImpl.class);
	
	static{
		System.setProperty("phantomjs.binary.path",PropriedadesCrawlers.lerPropriedade(Propriedades.DRIVER_SELENIUM_PATH));
	}
	
	private WebDriver driver;
	
	public BuscaRedditImpl(WebDriver driver){
		this.driver = driver;
	}

	@Override
	public Map<String, List<ThreadReddit>> buscarThreadsRedditPorListaSubReddit(String subreddits, int minUpvotes) {

		if(subreddits != null){
			
			Map<String, List<ThreadReddit>> map = new HashMap<String, List<ThreadReddit>>();
			
			String[] subredditsArray = subreddits.split(";");
			
			for (String subreddit : subredditsArray) {
				List<ThreadReddit> threadList = buscarThreadsRedditPorSubReddit(subreddit, minUpvotes);
				map.put(subreddit, threadList);
			}
			
			return map;
			
		}
		
		return null;
	}

	@Override
	public List<ThreadReddit> buscarThreadsRedditPorSubReddit(String subreddit, int minUpvotes) {
		
		
		List<ThreadReddit> threadsReddit = null; 
		subreddit = subreddit.trim();
		StringBuilder sbBaseUrl = new StringBuilder();
		sbBaseUrl.append("https://www.reddit.com/r/");
		sbBaseUrl.append(subreddit);
		sbBaseUrl.append("/top");
		String baseUrl = sbBaseUrl.toString();
		
		
		try{
			driver.get(baseUrl);
			
			List<WebElement> listaThing = null;
			listaThing = driver.findElements(By.className("thing"));
			
			if(listaThing != null){
				threadsReddit = new ArrayList<ThreadReddit>();
				int quantidade = 0;
				for (WebElement webElementThing : listaThing) {
					quantidade ++;
					//primeiro resultado nao conta, ficticio do site 
					if(quantidade > 1){
						//Obtem os Reddit acima de 5000 upvotes
						ThreadReddit threadReddit = new ThreadReddit(webElementThing, subreddit);
						
						if(threadReddit.getUpVotes() < minUpvotes){
							break;
						}
						threadsReddit.add(threadReddit);
						
					}
					
				}
			}
		
		}catch(Exception e){
			
			log.error("Threads não encontrada " + e);
			
		}
		
		return threadsReddit;
		
		
	}
	

}
