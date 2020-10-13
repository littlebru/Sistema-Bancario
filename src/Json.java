import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Json extends LeitorDeArquivo {

	public Json(String caminho) {
		super(caminho);
		this.conteudo = lerArquivo();
	}

	@Override
	public void converteParaObjeto() {
		Gson g = new Gson();
		String json = conteudo.toString();

		Type lista = new TypeToken<List<Remessa>>() {
		}.getType();

		remessasJson = g.fromJson(json, lista);
		System.out.println("Arquivo lido: arquivos/remessa.json\n");
		System.out.println("---------------------------------------------------------\n");
	}

//	public void imprimeClienteEmPosicaoEspecifica(int posicao) {
//
//		// Pegando cliente na posicao indicada
//		Remessa res = remessasJson.get(posicao);
//
//		 this.impressor.imprimeCliente(res);
//	}

	@Override
	public void imprimeRemessa() {

		for (Remessa res : remessasJson) {
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
				System.out.printf("VALOR: %.2f", res.getValor());

				if (res.getNumeroBoleto() != null) {
					// Se o valor do boleto não é nulo
					// Então o cliente pagou com boleto
					System.out.println("\n- PAGO COM BOLETO -");
					System.out.println("Nº BOLETO: " + res.getNumeroBoleto() + '\n');
				} else if (res.getParcelas() == 0) {
					// Se o cliente não pagou com boleto então pagou com cartão
					// Se não tem a quantidade de parcelas
					// Então o cliente pagou com cartão de Débito
					System.out.println("\n- PAGO COM CARTÃO DE DÉBITO -");
					System.out.println("NOME TITULAR DO CARTÃO: " + res.getNomeTitular());
					System.out.println("Nº CARTÃO: " + res.getNumeroCartao() + '\n');
				}

				else {
					// Se não o cliente pagou com o cartão de Crédito
					System.out.println("\n- PAGO COM CARTÃO DE CRÉDITO -");
					System.out.println("NOME TITULAR DO CARTÃO: " + res.getNomeTitular());
					System.out.println("Nº CARTÃO: " + res.getNumeroCartao());
					System.out.println("QNT. PARCELAS: " + res.getParcelas() + '\n');
				}
			} else {
				System.out.println("---- CPF INVÁLIDO ----\n");
			}
		}
	}
}
