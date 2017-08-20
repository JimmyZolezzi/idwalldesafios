package desafio.idwall.crawlers.telegram;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import desafio.idwall.crawlers.BuscaRedditImpl;
import desafio.idwall.crawlers.IBuscaReddit;
import desafio.idwall.crawlers.PropriedadesCrawlers;
import desafio.idwall.crawlers.PropriedadesCrawlers.Propriedades;
import desafio.idwall.crawlers.beans.ThreadReddit;

/**
 * Classe responsavel por enviar a lista Reddit para o telegram através de um comando específico
 * 
 * @author ledzo
 *
 */
public class EnvioRedditTelegramImpl implements IEnvioRedditTelegram, Runnable {
	
	private final Logger log = Logger.getLogger(EnvioRedditTelegramImpl.class);

	static{
		System.setProperty("phantomjs.binary.path",PropriedadesCrawlers.lerPropriedade(Propriedades.DRIVER_SELENIUM_PATH));
	}
	
	
	private final static String COMANDO = "/NADAPRAFAZER";
	
	
	/**
	 * Metodo de envio de lista do Reddit para o Telegram
	 * 
	 * @param comando exemplo /NadaPraFazer [cats;dogs;worldnews]
	 */
	@Override
	public Map<String, List<ThreadReddit>> obterListaRedditTelegram(String comando, WebDriver driver) {
		if(comando != null && comando.toUpperCase().startsWith(COMANDO)){
			comando = comando.toUpperCase();
			IBuscaReddit buscaReddit = new BuscaRedditImpl(driver);
			
			String listaSubreddit = comando.replaceAll(COMANDO, "");
			comando = comando.toLowerCase().trim();
			String minUpvotesStr = PropriedadesCrawlers.lerPropriedade(Propriedades.MIN_UPVOTES);
			int upvotes = 0;
			if(minUpvotesStr.matches("[0-9].*")){
				upvotes = Integer.valueOf(minUpvotesStr);
			}
			
			Map<String, List<ThreadReddit>> mapThreadReddit = buscaReddit.buscarThreadsRedditPorListaSubReddit(listaSubreddit, upvotes);
			
			return mapThreadReddit;
			
		}
			
		
		return null;
		
	}

	@Override
	public void run() {
		
		WebDriver driver = new PhantomJSDriver();
		
		String botToken = PropriedadesCrawlers.lerPropriedade(Propriedades.BOT_TELEGRAM_TOKEN);
		//Criação do objeto bot com as informações de acesso
		TelegramBot bot = TelegramBotAdapter.build(botToken);

		//objeto responsável por receber as mensagens
		GetUpdatesResponse updatesResponse;
		//objeto responsável por gerenciar o envio de respostas
		SendResponse sendResponse;
		//objeto responsável por gerenciar o envio de ações do chat
		BaseResponse baseResponse;
		
		//controle de off-set, isto é, a partir deste ID será lido as mensagens pendentes na fila
		int m=0;
		try{
			int interacoes = 0;
			while (true){
				
				//executa comando no Telegram para obter as mensagens pendentes a partir de um off-set (limite inicial)
				updatesResponse =  bot.execute(new GetUpdates().limit(100).offset(m));
				
				//lista de mensagens
				List<Update> updates = updatesResponse.updates();
				
				//análise de cada ação da mensagem
				for (Update update : updates) {
					interacoes++;
					//atualização do off-set
					m = update.updateId()+1;
					//obtem a mensagem de envio
					String mensagem = update.message().text();
					
					//envio de "Escrevendo" antes de enviar a resposta
					baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
					//verificação de ação de chat foi enviada com sucesso
					log.info("Resposta de Chat Action Enviada?" + baseResponse.isOk());
					if(interacoes == 1){
						
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Olá para buscar reddits digite o comando /nadaprafazer [subreddit1,subreddit2] exemplo: /nadaprafazer askreddit;worldnews;cats"));
						log.info("Mensagem Enviada?" +sendResponse.isOk());
					}
					//envio da mensagem de resposta
					if(mensagem != null && !mensagem.equals("")){
						
						if(mensagem == null || !mensagem.toUpperCase().startsWith(COMANDO) && interacoes > 1){
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Comando não reconhecido..."));
							log.info("Mensagem Enviada?" +sendResponse.isOk());
							
						}else{
							Map<String, List<ThreadReddit>> mapThreadReddit = obterListaRedditTelegram(mensagem, driver);
							
							if(mapThreadReddit != null){
								Set<String> chaves = mapThreadReddit.keySet();
								
								for (String subreddit : chaves) {
									sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"#Subreddit : " + subreddit));
									log.info("Mensagem Enviada?" +sendResponse.isOk());
									List<ThreadReddit> listaThreadReddit = mapThreadReddit.get(subreddit);
									if(listaThreadReddit != null){
										
										for (ThreadReddit threadReddit : listaThreadReddit) {
											sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"#Titulo: " + threadReddit.getTitulo() + " #Upvotes: " + threadReddit.getUpVotes()
											+ " #Link: " + threadReddit.getLinkThread() + " #Comentarios: " + threadReddit.getLinkComentarios()));
											log.info("Mensagem Enviada?" +sendResponse.isOk());
										}
									}
									
									
								}
								
							}
							
						}
					}
					
				}
			}
		}catch(Exception e){
			
			log.error("Erro na busca de reddits" + e);
			
		}finally{
			driver.quit();
		}
		
	}

}
