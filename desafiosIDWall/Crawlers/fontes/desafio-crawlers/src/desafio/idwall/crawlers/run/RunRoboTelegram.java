package desafio.idwall.crawlers.run;

import desafio.idwall.crawlers.telegram.EnvioRedditTelegramImpl;

/**
 * Classe que roda o Robo do Telegram, responsavel por
 * fazer envios de reddit apartir do comando /nadaprafazer
 * @author ledzo
 *
 */
public class RunRoboTelegram {

	/**
	 * Inicializ o robo do telegram
	 * @param args
	 */
	public static void main(String[] args) {

		EnvioRedditTelegramImpl envioRedditTelegram = new EnvioRedditTelegramImpl();
		Thread threadEnvio = new Thread(envioRedditTelegram);
		threadEnvio.start();
	}
}
