package desafio.idwall;


public class QuebraTexto implements IQuebraTexto {

	/**
	 * Metodo responsável por formatar um texto em determinadas quebras de linha
	 * 
	 * @param texto - texto a ser formatado
	 * @param tamanhoLinha - quantidade máxima de carácteres por linha
	 * @return
	 */
	@Override
	public String formatarTextoPorQuebraLinha(String texto, int tamanhoLinha) {

		if(texto != null && tamanhoLinha != 0){
			
			StringBuilder sb = new StringBuilder();
			int textoTamanho = texto.length();
			int posicaoInicial = 0;
			for (int i = tamanhoLinha; i < textoTamanho; i = i + tamanhoLinha) {
				String linha = texto.substring(posicaoInicial, i);
				posicaoInicial = i;
				
				// verifica se o proximo caracter não é branco
				String proximoCaracter = texto.substring(i, i + 1);
				if (!proximoCaracter.equals(" ")) {
					// remove a palavra quebrada da linha;
					String ultimaPalavra = linha.replaceFirst(".* ", "");
					
					// recua a quantidade de caracteres da ultima palavra
					int tamanhoUltimaPalavra = ultimaPalavra.length();
					i = i - tamanhoUltimaPalavra;
					linha = linha.substring(0, linha.length() - tamanhoUltimaPalavra);
					posicaoInicial = posicaoInicial - tamanhoUltimaPalavra;
					
				} else {
					// pula caracter em branco
					i++;
					posicaoInicial++;
				}
				sb.append(linha.trim()).append("\n");
			}
			// concatena o texto restante
			String linha = texto.substring(posicaoInicial, textoTamanho);
			sb.append(linha.trim());
			
			return sb.toString();
		}
		
		return texto;
	}

	/**
	 * Metodo responsável por formatar um texto em determinadas quebras de linha com texto justificado
	 * 
	 * @param texto - texto a ser formatado
	 * @param tamanhoLinha - quantidade máxima de carácteres por linha
	 * @return
	 */
	@Override
	public String formatarTextoPorQuebraLinhaJustificado(String texto, int tamanhoMaximoLinha) {
		if(texto != null && tamanhoMaximoLinha != 0){
			String textoFormatado = formatarTextoPorQuebraLinha(texto, tamanhoMaximoLinha);
			String[] linhas = textoFormatado.split("\n");
			StringBuilder sbTexto = new StringBuilder();
			for (String linha : linhas) {
				
				if(linha.length() < tamanhoMaximoLinha){
					StringBuilder sbLinha = new StringBuilder();
					//tamanho da linha atual
					int tamanhoLinha = linha.length();
					// distribui a quantidade de brancos até preencher a quantidade de caracteres
					int qtdPreenchimento = tamanhoMaximoLinha  - tamanhoLinha;
					String[] palavras = linha.split(" ");
					//acha a quantidade de espacos de cada palavra
					palavras = obterQtdEspacoPreenchimento(palavras, qtdPreenchimento);
					
					for (String palavra : palavras) {
						sbLinha.append(palavra);
					}
					
					sbTexto.append(sbLinha).append("\n");
					
				}else{
					
					sbTexto.append(linha).append("\n");
				}
			}
			
			return sbTexto.toString();
		}
		return texto;
	}

	/**
	 * Obtem a quantidade de espaço em branco de preenchimento
	 * 
	 * @param palavras
	 * @param qtdPreenchimento
	 * @return
	 */
	public String[] obterQtdEspacoPreenchimento(String[] palavras, int qtdPreenchimento){
		
		if(palavras != null && palavras.length >=2){
			
			int posicaoInicial = 0;
			int qtdPalavras = palavras.length;
			int qtdPreenchida = 0;
			
			for (int i = 0; i< qtdPalavras - 1;i++) {
				String palavra = palavras[i];
				palavra = palavra + " ";
				palavras[i] = palavra;
			}
			
			while(qtdPreenchida < qtdPreenchimento){
				for (int i = posicaoInicial; i< qtdPalavras -1; i=i+2) {
					String palavra = palavras[i];
					palavra = palavra + " ";
					palavras[i] = palavra;
					qtdPreenchida ++;
					if(qtdPreenchida == qtdPreenchimento){
						break;
					}
				}
				if(posicaoInicial == 0){
					posicaoInicial = 1;
				}else{
					posicaoInicial = 0;
				}
			}
			
		}
		
		return palavras;
	}


}
