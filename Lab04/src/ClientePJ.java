import java.util.List;
import java.util.Date;

public class ClientePJ extends Cliente
{
	// Atributos especificos para cliente juridico
	private final String cnpj;
	private Date dataFundacao;
	private int qtdeFuncionarios;
	
	// Construtor
	public ClientePJ (String nome, String endereco, List <Veiculo> listaVeiculos,
			String cnpj, Date dataFundacao, int qtdeFuncionarios)
	{
		// Chama o construtor da superclasse
		super (nome, endereco, listaVeiculos);
		
		this.cnpj = cnpj.replaceAll("[^0-9]", "");
		this.dataFundacao = dataFundacao;
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

	public String getIdentificacao()
	{
		return cnpj;
	}

	@Override
	// String no formato de impressao
	public String toString()
	{
		String string = super.toString();
		string += "\nCNPJ: " + cnpj + "\nData de Fundação: " + dataFundacao +
				"\nQuantidade de Funcionários: " + qtdeFuncionarios;
		return string;
	}
	
	@Override
	public double calculaScore()
	{
		return super.calculaScore() * (1 + (qtdeFuncionarios)/100);
	}
}
