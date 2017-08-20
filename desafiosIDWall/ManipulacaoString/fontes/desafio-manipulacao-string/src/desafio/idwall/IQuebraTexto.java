package desafio.idwall;
/**
 * Interface referente a formatação de uma determinado texto
 * por quebra de linhas
 * @author ledzo
 *
 */
public interface IQuebraTexto {

	/**
	 * Metodo responsável por formatar um texto em determinadas quebras de linha
	 * 
	 * @param texto - texto a ser formatado
	 * @param tamanhoLinha - quantidade máxima de carácteres por linha
	 * @return
	 */
	public String formatarTextoPorQuebraLinha(String texto, int tamanhoLinha);
	/**
	 * Metodo responsável por formatar um texto em determinadas quebras de linha com texto justificado
	 * 
	 * @param texto - texto a ser formatado
	 * @param tamanhoLinha - quantidade máxima de carácteres por linha
	 * @return
	 */
	public String formatarTextoPorQuebraLinhaJustificado(String texto, int tamanhoLinha);
}
