import java.util.ArrayList;
import java.util.Date;

public class Cliente
{
	private String nome;
	private String endereco;
	private Date dataLicensa;
	private String educacao;
	private String genero;
	private String classeEconomica;
	private ArrayList<Veiculo> listaVeiculos;
	
	// Constructor
	public Cliente(String nome, String endereco, Date dataLicensa, String educacao,
			String genero, String classeEconomica, ArrayList<Veiculo> listaVeiculo)
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

	public String getGenero()
	{
		return genero;
	}
	public void setGenero(String genero)
	{
		this.genero = genero;
	}

	public String getClasseEconomica()
	{
		return classeEconomica;
	}
	public void setClasseEconomica(String classeEconomica)
	{
		this.classeEconomica = classeEconomica;
	}

	public ArrayList<Veiculo> getListaVeiculos()
	{
		return listaVeiculos;
	}
	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos)
	{
		this.listaVeiculos = listaVeiculos;
	}

	// Devolve a string no formato para impressao
	public String toString()
	{
		return "Nome: " + nome + "\nEndereço: " + endereco + "\nData de Licensa: " + dataLicensa + "\nEducação: " + educacao +
				"\nGênero: " + genero + "\nClasse Econômica: " + classeEconomica + "\nVeículos: " + listaVeiculos;
	}
}