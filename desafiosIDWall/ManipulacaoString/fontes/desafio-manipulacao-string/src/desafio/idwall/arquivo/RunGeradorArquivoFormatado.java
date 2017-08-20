package desafio.idwall.arquivo;

import desafio.idwall.IQuebraTexto;
import desafio.idwall.QuebraTexto;

public class RunGeradorArquivoFormatado {
	
	

	public static void main(String[] args) {
		
		String texto = "Paola Carosella e Erick Jacquin fizeram uma breve avaliação sobre a atuação de Deborah Werneck e Michele Crispim, participantes que chegaram à final do MasterChef Brasil, da Band, após três meses intensos de competição";
		String tipo = "J";
		String caminho = "desafio1.txt";
		
		int quantidadeMaximaCaracteres = 30;
		
		if(args != null){
			int tamanho = args.length;
			
			if(tamanho >= 1){
				texto = args[0];
			}
			if(tamanho >= 2){
				tipo = args[1];
			}
			if(tamanho >= 3){
				caminho = args[2];
			}
			if(tamanho >= 4){
				String qtdCarateres = args[3];
				if(qtdCarateres.matches("[0-9].*")){
					quantidadeMaximaCaracteres = Integer.valueOf(qtdCarateres);
				}
			}
			
			
		}
		
		
		IQuebraTexto quebraTexto = new QuebraTexto();
		if(tipo !=null && tipo.equals("J")){
			texto = quebraTexto.formatarTextoPorQuebraLinhaJustificado(texto, quantidadeMaximaCaracteres);
		}else{
			texto = quebraTexto.formatarTextoPorQuebraLinha(texto, quantidadeMaximaCaracteres);
		}
		System.out.println(texto);
		GeradorArquivoFormatado geradorArquivo = new GeradorArquivoFormatado();
		geradorArquivo.gerarArquivoFormatado(texto, caminho);
		
	}
}
