package desafio.idwall.arquivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import desafio.idwall.IQuebraTexto;
import desafio.idwall.QuebraTexto;

public class GeradorArquivoFormatado implements IGeradorArquivoFormatado {

	private final Logger log = Logger.getLogger(GeradorArquivoFormatado.class);
	
	@Override
	public void gerarArquivoFormatado(String texto, String nomeArquivo) {
		log.info("gerando arquivo");
		if(texto != null && nomeArquivo != null){
			try {
				
				FileWriter arquivo;
				arquivo = new FileWriter(new File(nomeArquivo));
				arquivo.write(texto);
				arquivo.close();
				log.info("Arquivo gerado com sucesso!");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("Erro ao gerar o arquivo: " + e);
			}
		}
	}
	
}
