package desafio.idwall.crawlers.beans;

import org.openqa.selenium.WebElement;

/**
 * Interface que determina o metodo de procura de elementos de uma thread
 * @author ledzo
 *
 */
public interface IThreadReddit {

	/**
	 * 
	 * Metodo responsável por procurar elementos da thread
	 * @param elementThread
	 */
	void procurarElementosThread(WebElement elementThread);
}
