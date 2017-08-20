package desafio.idwall;
/**
 * Interface referente a formata��o de uma determinado texto
 * por quebra de linhas
 * @author ledzo
 *
 */
public interface IQuebraTexto {

	/**
	 * Metodo respons�vel por formatar um texto em determinadas quebras de linha
	 * 
	 * @param texto - texto a ser formatado
	 * @param tamanhoLinha - quantidade m�xima de car�cteres por linha
	 * @return
	 */
	public String formatarTextoPorQuebraLinha(String texto, int tamanhoLinha);
	/**
	 * Metodo respons�vel por formatar um texto em determinadas quebras de linha com texto justificado
	 * 
	 * @param texto - texto a ser formatado
	 * @param tamanhoLinha - quantidade m�xima de car�cteres por linha
	 * @return
	 */
	public String formatarTextoPorQuebraLinhaJustificado(String texto, int tamanhoLinha);
}
