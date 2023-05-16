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
	private static List<Seguradora> listaSeguradoras = new LinkedList<Seguradora>();
	
	// Construtor
	public Seguradora(String nome, String telefone, String email, String endereco)
	{
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.listaSinistros = new LinkedList<Sinistro>();
		this.listaClientes = new LinkedList<Cliente>();
		listaSeguradoras.add(this);
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
	
	public static List<Seguradora> getListaSeguradora()
	{
		return listaSeguradoras;
	}


	// String no formato de impressao
	public String toString()
	{
		return "Nome: "+ nome + "\nTelefone: " + telefone + "\nEmail: " + email + "\nEndereço: " + endereco;
	}

	// Metodo para cadastrar o cliente, retornando false se o cliente já está cadastrado, ou true se conseguir cadastrar com sucesso
	public boolean cadastrarCliente(Cliente cliente)
	{
		if(listaClientes.contains(cliente))
		{
			return false;
		}
		listaClientes.add(cliente);
		cliente.setValorSeguro(calcularPrecoSeguroCliente(cliente));
		return true;
	}
	
	// Metodo para remover cliente e todos sinistros associados a ele, utilizando o nome como parâmetro para busca do cliente na seguradora
	public boolean removerCliente(String cliente)
	{
		// Busca do cliente
		for(int i=0; i<listaClientes.size(); i++)
		{
			if(listaClientes.get(i).getNome().equals(cliente))
			{
				listaClientes.remove(i);
				
				// Busca de todos os sinistros associados ao cliente
				for(int j=0; j<listaSinistros.size(); j++)
				{
					if(listaSinistros.get(j).getCliente().getNome().equals(cliente))
					{
						listaSinistros.remove(j);
						j=j-1;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	// Metodo que retorna uma lista de todos os clientes de um tipo, onde a opcao 1 (fisica) ou 2 (juridica) é passado como argumento na main
	public List<Cliente> listarClientes(String tipoCliente)
	{
		if(listaClientes.size()==0)
		{
			return listaClientes;
		}
		
		// Lista auxiliar para retorno, com clientes apenas do tipo desejado
		List<Cliente> listaTipo = new LinkedList<Cliente>();
		
		if(tipoCliente=="1")
		{
			// Busca dos clientes do tipo pessoa física
			for(int i=0; i<listaClientes.size(); i++)
			{
				if(listaClientes.get(i).getClass()== ClientePF.class)
				{
					listaTipo.add(listaClientes.get(i));
				}
			}
			return listaTipo;	
		}
		
		// Busca dos clientes do tipo pessoa jurídica
		for(int i=0; i<listaClientes.size();i++)
		{
			if(listaClientes.get(i).getClass()== ClientePJ.class)
			{
				listaTipo.add(listaClientes.get(i));
			}
		}
		return listaTipo;
	}
	
	// Metodo para gerar sinistro, retornando true com sucesso, ou false caso contrário
	public boolean gerarSinistro(String data, String endereco, String placa, String identificacao)
	{
		identificacao.replaceAll("[^0-9]", "");
		if(listaClientes.size()==0)
		{
			return false;
		}
		
		// Checagem, atraves do CPF ou CNPJ, para ver se o cliente está cadastrado na seguradora
		for(int i=0; i<listaClientes.size();i++)
		{
			if(listaClientes.get(i).getIdentificacao() == identificacao)
			{
				// Checagem, atraves da placa, para ver se o veículo passado como parâmetro pertence ao cliente
				for(Veiculo p : listaClientes.get(i).getListaVeiculos())
				{
					if(p.getPlaca() == placa)
					{
						Sinistro sinistro = new Sinistro(data, endereco, p, listaClientes.get(i));	
						listaSinistros.add(sinistro);
						listaClientes.get(i).setValorSeguro(calcularPrecoSeguroCliente(listaClientes.get(i)));
						return true;
					}
				}
			}
		}
		return false;
	}
	
	// Metodo para verificar a existência, ou não, de sinistros de um certo cliente, utilizando o CPF ou CNPJ como parâmetro de busca
	public boolean vizualizarSinistro(String id)
	{
		for(Cliente i : listaClientes)
		{
			if(i.getIdentificacao() == id)
			{
				return true;
			}
		}
		return false;
	}
	
	// Metodo que retorna uma lista com todos os sinistros existentes
	public List<Sinistro> listarSinistros()
	{
		return listaSinistros;
	}
	
	public double calcularPrecoSeguroCliente(Cliente cliente)
	{
		int cont=0;
		for(int i=0; i<listaSinistros.size();i++)
		{
			if(listaSinistros.get(i).getCliente().equals(cliente))
			{
				cont++;
			}
		}
		return cliente.calculaScore() * (1 + cont);

	}
	
	public double calcularReceita()
	{
		double soma=0;
		for(Cliente i : listaClientes)
		{
			soma += i.getValorSeguro();
		}
		return soma;
	}
}
