import java.util.Date;
import java.util.List;

public class ClientePF extends Cliente
{
	// Atributos especificos para cliente fisico
	private final String cpf;
	private String genero;
	private Date dataLicensa;
	private String educacao;
	private Date dataNascimento;
	private String classeEconomica;

	public ClientePF (String nome, String endereco, List <Veiculo> listaVeiculos, String cpf,
	String genero, Date dataLicensa, String educacao, Date dataNascimento, String classeEconomica)
	{
		// Chama o construtor da superclasse
		super (nome, endereco, listaVeiculos);
		
		this.cpf = cpf.replaceAll("[^0-9]", "");
		this.genero = genero;
		this.dataLicensa = dataLicensa;
		this.educacao = educacao;
		this.dataNascimento = dataNascimento;
		this.classeEconomica = classeEconomica;
	}

	// Getters e setters
	public String getGenero()
	{
		return genero;
	}
	public void setGenero(String genero)
	{
		this.genero = genero;
	}

	public Date getDataLicensa()
	{
		return dataLicensa;
	}
	public void setDataLicensa(Date dataLicensa)
	{
		this.dataLicensa = dataLicensa;
	}

	public String getEducacao()
	{
		return educacao;
	}
	public void setEducacao(String educacao)
	{
		this.educacao = educacao;
	}

	public Date getDataNascimento()
	{
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento)
	{
		this.dataNascimento = dataNascimento;
	}

	public String getClasseEconomica()
	{
		return classeEconomica;
	}
	public void setClasseEconomica(String classeEconomica)
	{
		this.classeEconomica = classeEconomica;
	}

	public String getCpf()
	{
		return cpf;
	}

	@Override
	// Devolve a string com todos atributos no formato para impressao
	public String toString()
	{
		return super.toString() + "\nCPF: " + cpf + "\nData de Nascimento: " + dataNascimento;
		
	}//Arrumar toString
	
	public double calculaScore()
	{
		
	}//Implementar funcao
}
