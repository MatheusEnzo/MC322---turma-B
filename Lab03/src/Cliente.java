public class Cliente
{
	private String nome;
	private String endereco;
	private Date dataLicensa;
	private String educacao;
	private String genero;
	private String classeEconomica;
	private List<Veiculo> listaVeiculos;
	
	// Constructor
	public Cliente(String nome, String endereco, Date dataLicensa, String educacao,
			String genero, String classeEconomica, List<Veiculo> listaVeiculo)
	{
		this.nome = nome;
		this.endereco = endereco;
		this.dataLicensa = dataLicensa;
		this.educacao = educacao;
		this.genero = genero;
		this.classeEconomica = classeEconomica;
		this.listaVeiculos = listaVeiculo;
	}

	// Getters e setters
	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getEndereco()
	{
		return endereco;
	}

	public void setEndereco(String endereco)
	{
		this.endereco = endereco;
	}
	
	// Devolve a string no formato para impressao
	public String toString()
	{
		return "Nome: " + nome + "\nCPF: " + "\nEndereco: " + endereco;
	}
}