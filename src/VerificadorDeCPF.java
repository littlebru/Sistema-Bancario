import java.util.InputMismatchException;

public class VerificadorDeCPF {
	
	private String cpf;
	 
	/*----------- Construtor -----------*/
	public VerificadorDeCPF(String cpf) {
		this.cpf = cpf;
	}
	
	
	/* Método responsavel por realizar a verificação da veracidade dos dados do CPF */
    public boolean verificaCPF() {
    	
        boolean dig_1_valido, dig_2_valido;
        
        if(todosIguais()) {
        	return false;
        }

        dig_1_valido = verificaDigito(10, cpf.charAt(9));
        dig_2_valido = verificaDigito(11, cpf.charAt(10));
        
        if(dig_1_valido && dig_2_valido) {
        	return true;
        }
        else {
            return false;
        }
    }
    
    /*Método responsavel por verificar a veracidade dos digitos do CPF*/
	public boolean verificaDigito(int peso, char digito){
		int soma = 0;
		int numeroAtual;
		int resultado;
		char digitoObtido;
		
		try {
			int contador = peso - 1;
			
			for(int i=0; i< contador; i++) {
				numeroAtual = (int) (cpf.charAt(i) - 48);
				soma += numeroAtual * peso;
				
				peso -= 1;
			}
			
			resultado = 11 - (soma % 11);
	        if ((resultado == 10) || (resultado == 11))
	        	digitoObtido = '0';
	        else digitoObtido = (char)(resultado + 48);
	        
			
			if(digitoObtido == digito) {
				return true;
			}
			
		}catch(InputMismatchException erro) {
			System.out.println("Ocorreu um erro: " + erro.getMessage());
			return false;
		}
	
		return false;
	}
	
	/*Método responsavel por verificar se os numeros do CPF não se repetem*/
	public boolean todosIguais() {
		
		if (cpf.equals("00000000000") ||
	            cpf.equals("11111111111") ||
	            cpf.equals("22222222222") || cpf.equals("33333333333") ||
	            cpf.equals("44444444444") || cpf.equals("55555555555") ||
	            cpf.equals("66666666666") || cpf.equals("77777777777") ||
	            cpf.equals("88888888888") || cpf.equals("99999999999") ||
	            (cpf.length() != 11))
			
	       return true;
		
		return false;
	}
	
	/*Método responsavel por retornar o CPF formatado*/
	public String getCPF() {
		return cpf.substring(0, 3) + "." +
				   cpf.substring(3, 6) + "." + 
				   cpf.substring(6, 9) + "-" +
				   cpf.substring(9, 11);
	}

	/*Método responsavel por imprimir o CPF formatado*/
    public void imprimeCPF() {
    	System.out.println(cpf.substring(0, 3) + "." +
    					   cpf.substring(3, 6) + "." + 
    					   cpf.substring(6, 9) + "-" +
    					   cpf.substring(9, 11));
    	}
}
