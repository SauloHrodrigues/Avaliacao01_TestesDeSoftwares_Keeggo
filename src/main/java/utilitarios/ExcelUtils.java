package utilitarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtils {
	private static HSSFWorkbook pastaDeTrabalho;
	private static HSSFSheet folha;
	//private static HSSFRow linha;
	private static HSSFCell celula; 
	
	public void setArquivoExcel(String sNomeDaAbaDaPlanilha ) {
//		System.out.println("valor da aba é = "+sNomeDaAbaDaPlanilha);
		File arquivo = new File(Constantes.sEnderecoDoArquivoExcel+"//"+Constantes.sNomeDoArquivoExcel); 
		FileInputStream planilha = null;
		try {
			planilha = new FileInputStream(arquivo);
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrada.");
		} 
		try {
			pastaDeTrabalho = new HSSFWorkbook(planilha);
		} catch (IOException e) {
			System.out.println("Planilha não encontrada.");
//			e.printStackTrace();
		}
		folha = pastaDeTrabalho.getSheet(sNomeDaAbaDaPlanilha);
	}
	
	public String[] credenciais(){
//		System.out.println("entrou em credencial");
		setArquivoExcel("confidencial");
		String email = getValorDaCelulaDoArquivo(0, 0);
		String senha = getValorDaCelulaDoArquivo(1, 0);
		String[] i= new String[2]; 
			i[0] = email;
			i[1]= senha;
		return i;
	}
	
//	*********************** versao 2 em construção
	public void buscarBancoV2(String id) {
		setArquivoExcel("massaDados");
		String dadoProcurado=id;
		String conteudoEncontrado="";
		int linha=0;
		int i=1;
		while (conteudoEncontrado.isEmpty()&& i<= getTotalLinhasFolha("massaDados")){
			if(getValorDaCelulaDoArquivo(i,0).equalsIgnoreCase(dadoProcurado)){
				conteudoEncontrado = getValorDaCelulaDoArquivo(i,1);
				linha = i;	
			}
//			System.out.println(getValorDaCelulaDoArquivo(i,0));
			i++;			
		}
		i=0;
		List<String> listaProdutos = new ArrayList();
		while (conteudoEncontrado.contains(", ")) {
			String[] aux = conteudoEncontrado.split(","); 
			
		}	
		System.out.println("o conteudo encontrado foi = "+conteudoEncontrado);
	}
//	*********************** versao 2 - em construção

	
	public String buscarBanco(String id) {
		setArquivoExcel("massaDados");
		String dadoProcurado=id;
		String dadoEncontrado="";
		String sProduto="";
		int linha=0;
		int i=1;
		while (dadoEncontrado.isEmpty()&& i<= getTotalLinhasFolha("massaDados")){
			if(getValorDaCelulaDoArquivo(i,0).equalsIgnoreCase(dadoProcurado)){
				dadoEncontrado = getValorDaCelulaDoArquivo(i,0);
				sProduto = getValorDaCelulaDoArquivo(i,1);
				linha = i;
			
			}
//			System.out.println(getValorDaCelulaDoArquivo(i,0));
			i++;
		}
		if(dadoEncontrado.isEmpty()) {
			return "O dado "+dadoProcurado+" não foi encontrado.";
		}else {
			
			
			return sProduto;
			
		}
	}
	
	
	
	public String getValorDaCelulaDoArquivo(int iNumeroDaLinha, int iNumeroDaCelula) {

		//capturando o endetreço da celula na linha e celula(coluna), passada nos parametros
		celula = folha.getRow(iNumeroDaLinha).getCell(iNumeroDaCelula);
		//retorna o valor da celula encontrada
		return celula.getStringCellValue();
	}
	
	// metodo mretorna o total de linhas da tabela
	public int getTotalLinhasFolha(String abaPlanilha) {
		setArquivoExcel(abaPlanilha);
		int totalDeLinhas = folha.getLastRowNum() - folha.getFirstRowNum();
		return totalDeLinhas;
	}
	
	//Metodo cria uma nova coluna na linha
	public void setNovaColunaNalinha(int iNumeroDaLinha, int iNumeroDaNovaColuna, String sConteudo, String sCaminhoDoArquivo) throws IOException {
		folha.getRow(iNumeroDaLinha).createCell(iNumeroDaNovaColuna).setCellValue(sConteudo);
		FileOutputStream planilha = new FileOutputStream(sCaminhoDoArquivo);//lança exeção
		pastaDeTrabalho.write(planilha);//lança exeção
	}
	
}
