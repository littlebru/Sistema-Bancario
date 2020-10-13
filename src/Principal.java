
public class Principal {

	public static void main(String[] args){
		// TESTANDO A CLASSE LEITOR
		Json json = new Json("arquivos/remessa.json");
		json.converteParaObjeto();
		json.imprimeRemessa();
		
		Xml xml = new Xml("arquivos/remessa.xml");
		xml.converteParaObjeto();
		xml.imprimeRemessa();
	}

}