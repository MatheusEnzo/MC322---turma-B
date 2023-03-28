public class Sinistro
{
	private int id;
	private String data;
	private String endereco;
	
	private static int contador = 0;
	
	// Constructor
	public Sinistro(String data, String endereco)
	{
		this.id = gerarID();
		this.data = data;
		this.endereco = endereco;
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

	public void setId(int id)
	{
		this.id = id;
	}

	public String getData()
	{
		return data;
	}

	public void setData(String data)
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
	
	// Devolve a string no formato para impressao
	public String toString()
	{
		return "ID: " + id + "\nData: " + data + "\nEndereco: " + endereco;
	}
}