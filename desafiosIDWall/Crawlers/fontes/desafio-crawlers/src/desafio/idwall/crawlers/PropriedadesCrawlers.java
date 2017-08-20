package desafio.idwall.crawlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropriedadesCrawlers {

	private static final Logger log = Logger.getLogger(PropriedadesCrawlers.class);
	
	public static String lerPropriedade(Propriedades propriedades){
		if(propriedades != null){
			Properties prop = new Properties();
			InputStream input = null;
			try {
				input = new FileInputStream(new File("/desafioJimmy/configuracoes.properties"));
				prop.load(input);
				String propriedade = prop.getProperty(propriedades.name());
				return propriedade;
				
			} catch (IOException ex) {
				
				log.error("erro ao ler o arquivo de propriedades" + ex);
				
				if (input != null) {
					try {
						
						input.close();
					} catch (IOException e) {
						
						log.error("Erro ao fechar o arquivo de propriedades" + e);
					}
				}
				
			}
		}
		
		return null;
	}
	
	
	public enum Propriedades{
		DRIVER_SELENIUM_PATH,
		BOT_TELEGRAM_TOKEN,
		MIN_UPVOTES
	}
}
