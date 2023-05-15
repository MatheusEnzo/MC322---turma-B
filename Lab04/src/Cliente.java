import java.util.List;

public class Cliente
{
	private String nome;
	private String endereco;
	private List<Veiculo> listaVeiculos;
	private double valorSeguro;
	
	// Constructor
	public Cliente(String nome, String endereco, List<Veiculo> listaVeiculo)
	{
		this.nome = nome;
		this.endereco = endereco;
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
	
	public List<Veiculo> getListaVeiculos()
	{
		return listaVeiculos;
	}
	public void setListaVeiculos(List<Veiculo> listaVeiculos)
	{
		this.listaVeiculos = listaVeiculos;
	}

	public double getValorSeguro()
	{
		return valorSeguro;
	}

	public void setValorSeguro(double valorSeguro)
	{
		this.valorSeguro = valorSeguro;
	}

	// Devolve a string com todos atributos no formato para impressao
	public String toString()
	{
		String string = "Nome: " + nome + "\nEndereço: " + endereco + "\nVeículos:";
		for(int i=0;i<listaVeiculos.size();i++)
		{
			string = string + " [" + listaVeiculos.get(i).toString() + "]";
		}
		string += "Valor do Seguro:" + valorSeguro;
		
		return string;
	}
}