import java.util.List;
import java.util.Date;

public class ClientePJ extends Cliente
{
	// Atributos especificos para cliente juridico
	private final String cnpj;
	private Date dataFundacao;
	private int qtdeFuncionarios;
	
	public ClientePJ (String nome, String endereco, List <Veiculo> listaVeiculos,
			String cnpj, Date dataFundacao, int qtdeFuncionarios)
	{
		// Chama o construtor da superclasse
		super (nome, endereco, listaVeiculos);
		
		this.dataFundacao = dataFundacao;
		this.cnpj = cnpj.replaceAll("[^0-9]", "");
		this.qtdeFuncionarios = qtdeFuncionarios;
	}

	//Getters e setters
	public Date getDataFundacao()
	{
		return dataFundacao;
	}
	public void setDataFundacao(Date dataFundacao)
	{
		this.dataFundacao = dataFundacao;
	}

	public int getQtdeFuncionarios()
	{
		return qtdeFuncionarios;
	}

	public void setQtdeFuncionarios(int qtdeFuncionarios)
	{
		this.qtdeFuncionarios = qtdeFuncionarios;
	}

	public String getCnpj()
	{
		return cnpj;
	}

	@Override
	// Devolve a string com todos atributos no formato para impressao
	public String toString()
	{
		return super.toString() + "\nCNPJ: " + cnpj + "\nData de Fundação: " + dataFundacao;	
	}//Arrumar toString
	
	public double calculaScore()
	{
		
	}//Implementar funcao
}
