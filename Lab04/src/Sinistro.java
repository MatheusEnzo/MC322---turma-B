public class Sinistro
{
	private final int id;
	private String data;
	private String endereco;
	private Veiculo veiculo;
	private Cliente cliente;
	
	private static int contador = 1;
	
	// Constructor
	public Sinistro(String data, String endereco, Veiculo veiculo, Cliente cliente)
	{
		this.id = gerarID();
		this.data = data;
		this.endereco = endereco;
		this.veiculo = veiculo;
		this.cliente = cliente;
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
	public Veiculo getVeiculo()
	{
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo)
	{
		this.veiculo = veiculo;
	}
	
	public Cliente getCliente()
	{
		return cliente;
	}
	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}
	
	// Devolve a string com todos atributos no formato para impressao
	public String toString()
	{
		return "ID: " + id + ", Data: " + data + ", Endereco: " + endereco + ", Veículo: " + veiculo + ", Nome do Cliente: " + cliente.getNome();
	}
}