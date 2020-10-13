import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;

public class Xml extends LeitorDeArquivo{
	
	public Xml(String caminho) {
		super(caminho);
		this.conteudo = lerArquivo();
	}
	
	@Override
	public void converteParaObjeto() {
		XStream xstream = new XStream();
		
		String conteudoDoArquivo = conteudo.toString();
		
		Type lista = new TypeToken<List<Remessa>>() {}.getType();
		
		// informando que a lista de argumentos pertence ao tipo Remessa
		xstream.alias("list", Remessa[].class);
		
		// atribuindo cada argumento do xml a uma classe especifica
		xstream.alias("br.com.pageseguro.RemessaCartaoCredito", Remessa.class);
		xstream.alias("br.com.pageseguro.RemessaBoleto", Remessa.class);
		xstream.alias("br.com.pageseguro.RemessaCartaoDebito", Remessa.class);
		
		this.remessasXml = (Remessa[]) xstream.fromXML(conteudoDoArquivo, lista);
	}
	
	@Override
	public void imprimeRemessa() {
		
		for(Remessa res: remessasXml) {
			System.out.println("Nome: " + res.getNome());
			System.out.println("CPF: " + res.getCPF());
			System.out.println("Banco do Recebimento: " + res.getBancoRecebimento());
			System.out.println("Banco do Pagamaento: " + res.getBancoPagamento());
			System.out.println("Data da Transação: " + res.getData());
			System.out.printf( "%.2f", res.getValor() );
			
			if (res.getNumeroBoleto() != null) {
				// Se o valor do boleto  não é nulo
				// Então o cliente pagou com boleto
				System.out.println("\nPAGO COM BOLETO:");
				System.out.println("Núm. Boleto: " + res.getNumeroBoleto());
			}
			else if (res.getParcelas() == 0) {
				// Se o cliente não pagou com boleto então pagou com cartão
				// Se não tem a quantidade de parcelas
				// Então o cliente pagou com cartão de Débito
				System.out.println("\nPAGO COM CARTÃO DE DÉBITO:");
				System.out.println("Nome Títular do Cartão: " + res.getNomeTitular());
				System.out.println("Núm. Cartão: " + res.getNumeroCartao());
			}
			
			else {
				// Se não o cliente pagou com o cartão de Crédito
				System.out.println("\nPAGO COM CARTÃO DE CRÉDITO:");
				System.out.println("Nome Títular do Cartão: " + res.getNomeTitular());
				System.out.println("Núm. Cartão: " + res.getNumeroCartao());
				System.out.println("Qnt. Parcelas: " + res.getParcelas());
			}
		}
	}
	
}