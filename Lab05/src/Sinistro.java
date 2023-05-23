import java.util.Date;

public class Sinistro
{
	private final int id;
	private Date data;
	private String endereco;
	private Condutor condutor;
	private Seguro seguro;
	
	private static int contador = 1;
	
	// Constructor
	public Sinistro(Date data, String endereco, Condutor condutor, Seguro seguro)
	{
		this.id = gerarID();
		this.data = data;
		this.endereco = endereco;
		this.condutor = condutor;
		this.seguro = seguro;
	}

	// Metodo gerador de id inteiro unico
	private static int gerarID()
	{
		return contador++;
	}
	
	// Getters e setters
	public int getId()
	{
		return id;
	}

	public Date getData()
	{
		return data;
	}

	public void setData(Date data)
	{
		this.data = data;
	}

	public String getEndereco()
	{
		return endereco;
	}

	public void setEndereco(String endereco)
	{
		this.endereco = endereco;
	}
	public Condutor getCondutor()
	{
		return condutor;
	}
	public void setCondutor(Condutor condutor)
	{
		this.condutor = condutor;
	}
	
	public Seguro getSeguro()
	{
		return seguro;
	}
	public void setSeguro(Seguro seguro)
	{
		this.seguro = seguro;
	}
	
	// Devolve a string com todos atributos no formato para impressao
	public String toString()
	{
		return "ID: " + id + ", Data: " + data + ", Endereco: " + endereco + ", Condutor: [" + condutor + "], Seguro: " + seguro;
	}
}