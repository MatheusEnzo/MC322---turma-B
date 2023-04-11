import java.util.LinkedList;

public class Seguradora
{
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private LinkedList<Sinistro> listaSinistros;
	private LinkedList<Cliente> listaClientes;
	
	// Constructor
	public Seguradora(String nome, String telefone, String email, String endereco)
	{
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
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

	public String getTelefone()
	{
		return telefone;
	}

	public void setTelefone(String telefone)
	{
		this.telefone = telefone;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getEndereco()
	{
		return endereco;
	}

	public void setEndereco(String endereco)
	{
		this.endereco = endereco;
	}
	
	public LinkedList<Sinistro> getListaSinistros()
	{
		return listaSinistros;
	}
	public void setListaSinistros(LinkedList<Sinistro> listaSinistros)
	{
		this.listaSinistros = listaSinistros;
	}

	public LinkedList<Cliente> getListaClientes()
	{
		return listaClientes;
	}
	public void setListaClientes(LinkedList<Cliente> listaClientes)
	{
		this.listaClientes = listaClientes;
	}

	public boolean cadastrarCliente(Cliente cliente)
	{
		if(listaClientes.contains(cliente))
		{
			return false;
		}
		return listaClientes.add(cliente);
	}
	
	public boolean removerCliente(String cliente)
	{
		
	}
	
	public LinkedList<Cliente> listarClientes(String tipoCliente)
	{
		for(int i=0; i<listaClientes.size(); i++)
		{
			
		}
	}
	
	public boolean gerarSinistro()
	{
		if(listaClientes.size()==0)
		{
			return false;
		}
		Sinistro sinistro = new Sinistro()
	}
	
	// Devolve a string no formato para impressao
	public String toString()
	{
		return "Nome: " + nome + "\nEndereco: " + endereco + "\nEmail: " + email + "\nTelefone: " + telefone;
	}
}
