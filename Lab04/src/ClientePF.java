import java.util.Date;

public class ClientePF extends Cliente
{
	// Atributos especificos para cliente fisico
	private final String cpf;
	private String genero;
	private Date dataLicensa;
	private String educacao;
	private Date dataNascimento;
	private String classeEconomica;
	
	// Construtor
	public ClientePF (String nome, String endereco, String cpf, String genero, Date dataLicensa,
			String educacao, Date dataNascimento, String classeEconomica)
	{
		// Chama o construtor da superclasse
		super (nome, endereco);
		
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

	public String getIdentificacao()
	{
		return cpf;
	}

	@Override
	// String no formato de impressao
	public String toString()
	{
		String string = super.toString();
		string += "\nCPF: " + cpf + "\nGênero: " + genero + "\nData de Licensa: " + dataLicensa + "\nEducação: " + educacao +
				"\nData de Nascimento: " + dataNascimento + "\nClasse Econômica: " + classeEconomica;
		return string;
		
	}
	
	@Override
	public double calculaScore()
	{
		int idade = Validacao.calcularIdade(dataNascimento);
		if(idade<30)
		{
			return super.calculaScore() * CalcSeguro.FATOR_18_30.getFator();
		}
		else if(idade<60)
		{
			return super.calculaScore() * CalcSeguro.FATOR_30_60.getFator();
		}
		else
		{
			return super.calculaScore() * CalcSeguro.FATOR_60_90.getFator();
		}
	}
}
