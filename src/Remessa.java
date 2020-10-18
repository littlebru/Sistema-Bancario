public class Remessa {
	protected String nome;
	protected String CPF;
	protected String bancoPagamento;
	protected String bancoRecebimento;
	protected String data;
	protected double valor;
	protected String nomeTitular;
	protected String numeroCartao;
	protected int parcelas;
	protected String numeroBoleto;
	
	// ________CONSTRUCTORS________
  public Remessa() {
		super();
	}
	
	public Remessa(String nome, String cpf, String bancoPagamento, String bancoRecebimento, String data, double valor,
			String nomeTitular, String numeroCartao, int parcelas, String numeroBoleto) {
		super();
		this.nome = nome;
		CPF = cpf;
		this.bancoPagamento = bancoPagamento;
		this.bancoRecebimento = bancoRecebimento;
		this.data = data;
		this.valor = valor;
		this.nomeTitular = nomeTitular;
		this.numeroCartao = numeroCartao;
		this.parcelas = parcelas;
		this.numeroBoleto = numeroBoleto;
	}
	
	// ________MÉTODOS GET________
	
	public String getNome() {
		return nome;
	}

	public String getCPF() {
		return CPF;
	}

	public String getBancoPagamento() {
		return bancoPagamento;
	}

	public String getBancoRecebimento() {
		return bancoRecebimento;
	}

	public String getData() {
		return data;
	}

	public double getValor() {
		return valor;
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public int getParcelas() {
		return parcelas;
	}

	public String getNumeroBoleto() {
		return numeroBoleto;
	}
	
	
}
