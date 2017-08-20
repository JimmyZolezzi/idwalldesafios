package desafio.idwall.crawlers.beans;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import desafio.idwall.crawlers.PropriedadesCrawlers;

/**
 * Classe modelo de uma Thread do Reddit
 * @author ledzo
 *
 */
public class ThreadReddit implements IThreadReddit {
	
	private static final Logger log = Logger.getLogger(PropriedadesCrawlers.class);
	
	public ThreadReddit(){
		
	}

	public ThreadReddit(WebElement elementThread, String subreddit){
		this.subreddit = subreddit;
		procurarElementosThread(elementThread);
	}
	
	private String subreddit;
	private int upVotes;
	private String titulo;
	private String linkThread;
	private String linkComentarios;
	
	public String getSubreddit() {
		return subreddit;
	}
	public void setSubreddit(String subreddit) {
		this.subreddit = subreddit;
	}
	public int getUpVotes() {
		return upVotes;
	}
	public void setUpVotes(int upVotes) {
		this.upVotes = upVotes;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getLinkThread() {
		return linkThread;
	}
	public void setLinkThread(String linkThread) {
		this.linkThread = linkThread;
	}
	public String getLinkComentarios() {
		return linkComentarios;
	}
	public void setLinkComentarios(String linkComentarios) {
		this.linkComentarios = linkComentarios;
	}
	
	/**
	 * Metodo responsável por procurar elementos da thread
	 */
	@Override
	public void procurarElementosThread(WebElement elementThread) {
		
		procurarUpvoted(elementThread);
		procurarTitulo(elementThread);
		procurarLinkThread(elementThread);
		procurarLinkComentario(elementThread);
		
	}
	
	private void procurarUpvoted(WebElement elementThread){
		
		try{
			WebElement webElementMidcol = elementThread.findElement(By.className("midcol"));
			WebElement elementUnvoted = webElementMidcol.findElement(By.className("unvoted"));
			String upvotes =  elementUnvoted.getAttribute("title").toString();
			if(upvotes != null && upvotes.matches("[0-9].*")){
				int upvotesInt = Integer.valueOf(upvotes);
				this.upVotes = upvotesInt;
			}
			
		}catch (Exception e) {

			System.out.println("Elemento Upvoted não encontrado: " + e);
		}
		
		
	}
	
	private void procurarTitulo(WebElement elementThread){
		
		try{
			
			WebElement elementEntry =  elementThread.findElement(By.className("entry"));
			WebElement elementPTitle = elementEntry.findElement(By.className("title"));
			WebElement elementATitle = elementPTitle.findElement(By.className("title"));
			String titulo = elementATitle.getText();
			this.titulo = titulo;
			
		}catch(Exception e){

			System.out.println("Elemento titulo não encontrado: " + e);
		}
	}

	private void procurarLinkThread(WebElement elementThread){
		
		try{
			WebElement elementEntry =  elementThread.findElement(By.className("entry"));
			WebElement elementPTitle = elementEntry.findElement(By.className("title"));
			WebElement elementATitle = elementPTitle.findElement(By.className("title"));
			String linkThread = elementATitle.getAttribute("href");
			this.linkThread = linkThread;
			
		}catch(Exception e){
			
			System.out.println("Elemento linkThread não encontrado: " + e);
		}
	}
	
	private void procurarLinkComentario(WebElement elementThread){
		
		try{
			
			WebElement elementEntry =  elementThread.findElement(By.className("entry"));
			WebElement elementFirst = elementEntry.findElement(By.className("first"));
			String linkComentarios = elementFirst.findElement(By.tagName("a")).getAttribute("href");
			this.linkComentarios = linkComentarios; 
			
		}catch(Exception e){
			
			log.error("Elemento linkComentarios não encontrado: " + e);
		
		}
		
	}
}
