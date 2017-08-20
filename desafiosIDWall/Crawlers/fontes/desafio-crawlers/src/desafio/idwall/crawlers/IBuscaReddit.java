package desafio.idwall.crawlers;

import java.util.List;
import java.util.Map;
import desafio.idwall.crawlers.beans.ThreadReddit;

/**
 * Interface resposável por definir métodos de buscar de Threads para Reddit 
 * 
 * @author ledzo
 *
 */
public interface IBuscaReddit {

	/**
	 * Busca as Threads de uma lista de subreddit seperados por ";" 
	 * exemplo: "askreddit;worldnews;cats"
	 * 
	 * @param subreddits
	 * @return Map<String, List<ThreadReddit>>
	 */
	public Map<String, List<ThreadReddit>> buscarThreadsRedditPorListaSubReddit(String subreddits, int minUpvotes);
	/**
	 * Busca as Threads de um sureddit especifico
	 * 
	 * @param subreddit
	 * @return List<ThreadReddit>
	 */
	public List<ThreadReddit> buscarThreadsRedditPorSubReddit(String subreddit, int minUpvotes);
}
