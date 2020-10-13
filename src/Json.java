import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Json extends LeitorDeArquivo{
	

	public Json(String caminho) {
		super(caminho);
		this.conteudo = lerArquivo();
	}

	@Override
	public void converteParaObjeto() {
		Gson g = new Gson();
		String json = conteudo.toString();
		
		Type lista = new TypeToken<List<Remessa>>() {}.getType();
		
		remessasJson = g.fromJson(json, lista);
		System.out.println("ok");
	}

	public void imprimeClienteEmPosicaoEspecifica(int posicao) {
		 
		// Pegando cliente na posicao indicada
		Remessa res = remessasJson.get(posicao);
		
		this.impressor.imprimeCliente(res);
	}
	
	@Override
	public void imprimeRemessa() {
		
		for(Remessa res: remessasJson) {
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


