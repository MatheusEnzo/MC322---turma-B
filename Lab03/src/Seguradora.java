import java.util.LinkedList;
import java.util.List;

public class Seguradora
{
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private List<Sinistro> listaSinistros;
	private List<Cliente> listaClientes;
	
	// Constructor
	public Seguradora(String nome, String telefone, String email, String endereco)
	{
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.listaSinistros = new LinkedList<Sinistro>();
		this.listaClientes = new LinkedList<Cliente>();
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
	
	public List<Sinistro> getListaSinistros()
	{
		return listaSinistros;
	}
	public void setListaSinistros(List<Sinistro> listaSinistros)
	{
		this.listaSinistros = listaSinistros;
	}

	public List<Cliente> getListaClientes()
	{
		return listaClientes;
	}
	public void setListaClientes(List<Cliente> listaClientes)
	{
		this.listaClientes = listaClientes;
	}
	
	public String toString()
	{
		return "Nome: "+ nome + "\nTelefone: " + telefone + "\nEmail: " + email + "\nEndereço: " + endereco;
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
		for(int i=0; i<listaClientes.size(); i++)
		{
			if(listaClientes.get(i).getNome()==cliente)
			{
				listaClientes.remove(i);
				for(int j=0; j<listaSinistros.size(); j++)
				{
					if(listaSinistros.get(j).getCliente().getNome()==cliente)
					{
						listaSinistros.remove(j);
					}
				}
				return true;
			}
		}
		return false;
	}
	
	// Selecao de opcao 1 (fisica) e 2 (juridica) implementado na main
	public List<Cliente> listarClientes(String tipoCliente)
	{
		if(listaClientes.size()==0)
		{
			return listaClientes;
		}
		
		List<Cliente> listaTipo = new LinkedList<Cliente>();
		
		if(tipoCliente=="1")
		{
			for(int i=0; i<listaClientes.size(); i++)
			{
				if(listaClientes.get(i).getClass()== ClientePF.class)
				{
					listaTipo.add(listaClientes.get(i));
				}
			}
			return listaTipo;	
		}
		
		for(int i=0; i<listaClientes.size();i++)
		{
			if(listaClientes.get(i).getClass()== ClientePJ.class)
			{
				listaTipo.add(listaClientes.get(i));
			}
		}
		return listaTipo;
	}
	
	public boolean gerarSinistro(String data, String endereco, Veiculo veiculo, Cliente cliente)
	{
		if(listaClientes.size()==0)
		{
			return false;
		}
		if(listaClientes.contains(cliente))
		{
			if(listaClientes.get(listaClientes.indexOf(cliente)).getListaVeiculos().contains(veiculo))
			{
				Sinistro sinistro = new Sinistro(data, endereco, veiculo, cliente);
				return listaSinistros.add(sinistro);
			}
		}
		return false;
	}
	
	public boolean vizualizarSinistro(String cliente)
	{
		for(int i=0; i<listaSinistros.size(); i++)
		{
			if(listaSinistros.get(i).getCliente().getNome()==cliente)
			{
				return true;
			}
		}
		return false;
	}
	
	public List<Sinistro> listarSinistros()
	{
		return listaSinistros;
	}
}
