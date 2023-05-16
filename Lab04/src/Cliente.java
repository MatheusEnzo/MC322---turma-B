import java.util.List;
import java.util.ArrayList;

public class Cliente
{
	private String nome;
	private String endereco;
	private List<Veiculo> listaVeiculos;
	private double valorSeguro;
	
	// Constructor
	public Cliente(String nome, String endereco)
	{
		this.nome = nome;
		this.endereco = endereco;
		this.listaVeiculos = new ArrayList<Veiculo>();
		this.valorSeguro = 0;
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

	public void setValorSeguro(double v)
	{
		this.valorSeguro = v;
	}

	// String no formato de impressao
	public String toString()
	{
		String string = "Nome: " + nome + "\nEndereço: " + endereco + "\nVeículos:";
		for(int i=0;i<listaVeiculos.size();i++)
		{
			string += " [" + listaVeiculos.get(i).toString() + "]";
		}
		string += "\nValor do Seguro:" + valorSeguro;
		
		return string;
	}
	
	public String getIdentificacao()
	{
		return null;
	}
	
	public double calculaScore()
	{
		return CalcSeguro.VALOR_BASE.getFator() * listaVeiculos.size();
	}
}