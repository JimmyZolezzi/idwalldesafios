package desafio.idwall.crawlers.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import desafio.idwall.crawlers.BuscaRedditImpl;
import desafio.idwall.crawlers.IBuscaReddit;
import desafio.idwall.crawlers.beans.ThreadReddit;

public class BuscarRedditTest {
	
	static{
		System.setProperty("phantomjs.binary.path","C:\\desafios\\phantomjs.exe");
	}
	
	
	@Test
	public void verificarConsultaReddit(){
		
		WebDriver driver = new PhantomJSDriver();
		int maxUpvote = 500;
		String subreddits = "cats;worldnews";
		IBuscaReddit buscaReddit = new BuscaRedditImpl(driver);
		Map<String,List<ThreadReddit>> mapThreadReddit = buscaReddit.buscarThreadsRedditPorListaSubReddit(subreddits, maxUpvote);
		if(mapThreadReddit != null){
			Set<String> chaves = mapThreadReddit.keySet();
			for (String chave : chaves) {
				
				List<ThreadReddit> listaReddit = mapThreadReddit.get(chave);
				for (ThreadReddit threadReddit : listaReddit) {
					Assert.assertTrue(threadReddit.getUpVotes() >= maxUpvote);
					Assert.assertNotNull(threadReddit.getTitulo());
					Assert.assertNotNull(threadReddit.getLinkThread());
					Assert.assertNotNull(threadReddit.getLinkComentarios());
				}
			}
			
		}
		
	}

}
