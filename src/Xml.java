import java.lang.reflect.Type;
import java.util.List;

//import org.junit.Test;

import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;

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
			System.out.println("\n_____________________________________\n");
			System.out.println("NOME: " + res.getNome());
			
			String cpf = res.getCPF();
			System.out.println("CPF: " + cpf);

			VerificadorDeCPF verifCpf = new VerificadorDeCPF(cpf);
			
			if (verifCpf.verificaCPF() == true) {
				System.out.println("---- CPF VÁLIDO ----\n");
				
				System.out.println("BANCO DO RECEBIMENTO: " + res.getBancoRecebimento());
				System.out.println("BANCO DO PAGAMENTO: " + res.getBancoPagamento());
				System.out.println("DATA DA TRANSAÇÃO: " + res.getData());
				System.out.printf( "VALOR: %.2f", res.getValor() );
				
				if (res.getNumeroBoleto() != null) {
					// Se o valor do boleto  não é nulo
					// Então o cliente pagou com boleto
					System.out.println("\n\n- PAGO COM BOLETO -");
					System.out.println("Nº BOLETO: " + res.getNumeroBoleto());
				}
				else if (res.getParcelas() == 0) {
					// Se o cliente não pagou com boleto então pagou com cartão
					// Se não tem a quantidade de parcelas
					// Então o cliente pagou com cartão de Débito
					System.out.println("\n\n- PAGO COM CARTÃO DE DÉBITO -");
					System.out.println("NOME TITULAR DO CARTÃO: " + res.getNomeTitular());
					System.out.println("Nº CARTÃO: " + res.getNumeroCartao());
				}
				
				else {
					// Se não o cliente pagou com o cartão de Crédito
					System.out.println("\n\n- PAGO COM CARTÃO DE CRÉDITO -");
					System.out.println("NOME TITULAR DO CARTÃO: " + res.getNomeTitular());
					System.out.println("Nº CARTÃO: " + res.getNumeroCartao());
					System.out.println("QNT. PARCELAS: " + res.getParcelas());
				}
			}
			else {
				System.out.println("---- CPF INVÁLIDO ----");
			}

		}
	}
	
}
