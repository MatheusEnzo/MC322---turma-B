public class ClientePJ
{
	private String cnpj;
	private Date dataFundacao;
	
	public ClientePJ (String nome, String endereco, Date dataLicensa,
	String educacao, String genero, String classeEconomica,
	List <Veiculo> listaVeiculos, String cnpj, Date dataFundacao)
	{
		super (nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos)
		
		this.dataFundacao = dataFundacao;
		this.cnpj = cnpj.replaceAll("[^0-9]", "");
	}

	public String getCnpj()
	{
		return cnpj;
	}
	public void setCnpj(String cnpj)
	{
		this.cnpj = cnpj;
	}

	public Date getDataFundacao()
	{
		return dataFundacao;
	}
	public void setDataFundacao(Date dataFundacao)
	{
		this.dataFundacao = dataFundacao;
	}
	
}
