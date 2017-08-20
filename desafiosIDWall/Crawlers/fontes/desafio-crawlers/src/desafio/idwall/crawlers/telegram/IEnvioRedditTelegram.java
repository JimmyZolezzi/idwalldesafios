package desafio.idwall.crawlers.telegram;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import desafio.idwall.crawlers.beans.ThreadReddit;

/**
 * Interface que de define o metodo de envio de lista Reddit para o Telegram
 * 
 * @author ledzo
 *
 */
public interface IEnvioRedditTelegram {

	/**
	 * Metodo de envio de lista do Reddit para o Telegram
	 * 
	 * @param comando exemplo /NadaPraFazer [cats;dogs;worldnews]
	 */
	public Map<String, List<ThreadReddit>> obterListaRedditTelegram(String comando, WebDriver driver);
	
}
