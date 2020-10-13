import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class LeitorDeArquivo {
	
	protected Remessa[] remessasXml;
	protected ArrayList<Remessa> remessasJson;
	protected String caminhoDoArquivo;
	protected StringBuffer conteudo;
	protected String linha;
	protected InterfaceDeImpressao impressor;
	protected VerificadorDeCPF verfiCpf;
	
	public LeitorDeArquivo(String caminho){
		this.caminhoDoArquivo = caminho;
	}	

	// metodo permite realizar a leitura de arquivos diversos
	public StringBuffer lerArquivo(){

		File f = new File(this.caminhoDoArquivo);
		
		this.conteudo = new StringBuffer();

		//EFETUANDO A LEITURA
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			//ENQUANTO EXISTE DADOS CONTINUA IMPRIMINDO
			while ((linha = br.readLine()) != null) {
				conteudo.append(this.linha + "\n");
			}
			br.close();
			
		} catch (IOException e) {
			System.out.println("###### Erro: "+e.getMessage());
			e.printStackTrace();
		}
		
		return this.conteudo; 
	}
	
	public abstract void converteParaObjeto();
	
	public abstract void imprimeRemessa();
}