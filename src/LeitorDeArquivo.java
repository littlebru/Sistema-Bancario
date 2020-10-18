import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public abstract class LeitorDeArquivo {
	
	protected Remessa[] remessasXml;
	protected ArrayList<Remessa> remessasJson;
	protected String caminhoDoArquivo;
	protected StringBuffer conteudo;
	protected String linha;
	//protected InterfaceDeImpressao impressor;
	
	public LeitorDeArquivo(String caminho){
		this.caminhoDoArquivo = caminho;
	}	

	// metodo permite realizar a leitura de arquivos diversos
	public StringBuffer lerArquivo(){
	
		File f = new File(this.caminhoDoArquivo);
		System.out.println("-------------------------------------------");
		System.out.println("ARQUIVO LIDO: " + f );
		System.out.println("-------------------------------------------");
		
		this.conteudo = new StringBuffer();

		//EFETUANDO A LEITURA
		try {
			Reader reader = new InputStreamReader(new FileInputStream(f), "UTF-8");
			// Lê os arquivos com os formatos UTF-8
			BufferedReader br = new BufferedReader(reader);

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
