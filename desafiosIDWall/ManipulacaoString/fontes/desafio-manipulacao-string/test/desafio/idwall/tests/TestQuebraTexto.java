package desafio.idwall.tests;

import org.junit.Assert;
import org.junit.Test;

import desafio.idwall.IQuebraTexto;
import desafio.idwall.QuebraTexto;

/**
 * Classe referente ao Test da Quebra de Texto
 * @author ledzo
 *
 */
public class TestQuebraTexto {

	@Test
	public void verificarQuebraLinha(){
		
		int quantidadeMaximaCaracteres = 40;
		String texto = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.";
		texto += "And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";
		IQuebraTexto quebraTexto = new QuebraTexto();
		String novoTexto = quebraTexto.formatarTextoPorQuebraLinha(texto, quantidadeMaximaCaracteres);
		
		String[] linhas = novoTexto.split("\n");
		
		for (String linha : linhas) {
			Assert.assertTrue(linha.length() <= quantidadeMaximaCaracteres);
		}
	
	}
	
	@Test
	public void verificarQuebraLinhaJustificado(){
		
		int quantidadeMaximaCaracteres = 40;
		String texto = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.";
		texto += "And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";
		IQuebraTexto quebraTexto = new QuebraTexto();
		String novoTexto = quebraTexto.formatarTextoPorQuebraLinhaJustificado(texto, quantidadeMaximaCaracteres);
		
		String[] linhas = novoTexto.split("\n");
		
		for (String linha : linhas) {
			Assert.assertTrue(linha.length() == quantidadeMaximaCaracteres);
		}
	
	}
}
