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
		for(Cliente i: listaClientes)
		{
			if(cliente.getIdentificacao().equals(i.getIdentificacao()))
			{
				return false;
			}
		}
		listaClientes.add(cliente);
		return true;
	}
	
	// Metodo para remover cliente e todos sinistros associados a ele, utilizando o CPF/CNPJ como parâmetro para busca
	public boolean removerCliente(String identificacao)
	{
		// Busca do cliente
		for(int i=0; i<listaClientes.size(); i++)
		{
			if(listaClientes.get(i).getIdentificacao().equals(identificacao))
			{
				listaClientes.remove(i);
				
				// Busca de todos os sinistros associados ao cliente
				for(int j=0; j<listaSinistros.size(); j++)
				{
					if(listaSinistros.get(j).getCliente().getIdentificacao().equals(identificacao))
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
		identificacao = identificacao.replaceAll("[^0-9]", "");
		if(listaClientes.size()==0)
		{
			return false;
		}
		
		// Checagem, atraves do CPF ou CNPJ, para ver se o cliente está cadastrado na seguradora
		for(int i=0; i<listaClientes.size();i++)
		{
			if(listaClientes.get(i).getIdentificacao().equals(identificacao))
			{
				// Checagem, atraves da placa, para ver se o veículo passado como parâmetro pertence ao cliente
				for(Veiculo p : listaClientes.get(i).getListaVeiculos())
				{
					if(p.getPlaca().equals(placa))
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
	
	public boolean removerSinistro(int id)
	{
		for(int i=0; i<listaSinistros.size();i++)
		{
			if(listaSinistros.get(i).getId() == id)
			{
				Cliente aux = listaSinistros.get(i).getCliente();
				listaSinistros.remove(i);
				aux.setValorSeguro(calcularPrecoSeguroCliente(aux));
				return true;
			}
		}
		return false;
	}
	
	// Metodo para verificar a existência, ou não, de sinistros de um certo cliente, utilizando o CPF ou CNPJ como parâmetro de busca
	public boolean visualizarSinistro(String id)
	{
		id.replaceAll("[^0-9]", "");
		for(Cliente i : listaClientes)
		{
			if(i.getIdentificacao().equals(id))
			{
				return true;
			}
		}
		return false;
	}
	
	// Metodo que retorna uma lista com todos os sinistros existentes
	public String listarSinistros()
	{
		if(listaSinistros.size()==0)
		{
			return "Não há sinistros cadastrados";
		}
		String sinistros = "";
		for(Sinistro i : listaSinistros)
		{
			sinistros += "[" + i + "]\n";
		}
		return sinistros;
	}
	
	public boolean cadastrarVeiculo(Cliente cliente, Veiculo veiculo)
	{
		for(Cliente i: listaClientes)
		{
			for(Veiculo j : i.getListaVeiculos())
			{
				if(j.getPlaca() == veiculo.getPlaca())
				{
					return false;
				}
			}
		}
		cliente.getListaVeiculos().add(veiculo);
		return true;
	}
	
	public boolean removerVeiculo(String placa)
	{
		for(int i=0; i<listaClientes.size(); i++)
		{
			for(int j=0; j<listaClientes.get(i).getListaVeiculos().size(); j++)
			{
				if(listaClientes.get(i).getListaVeiculos().get(j).getPlaca() == placa)
				{
					for(int k=0; k<listaSinistros.size(); k++)
					{
						if(listaSinistros.get(k).getVeiculo().getPlaca() == placa)
						{
							listaSinistros.remove(k);
							k = k - 1;
						}
					}
					listaClientes.get(i).getListaVeiculos().remove(j);
					listaClientes.get(i).setValorSeguro(calcularPrecoSeguroCliente(listaClientes.get(i)));
					return true;
				}
			}
		}
		return false;
	}
	
	public void transferencia(String cliente_emissor, String cliente_receptor)
	{
			int index_emissor = 0, index_receptor = 0;
			for(int i=0; i<listaClientes.size(); i++)
			{
				if(listaClientes.get(i).getIdentificacao() == cliente_emissor)
				{
					index_emissor = i;
				}
				if(listaClientes.get(i).getIdentificacao() == cliente_receptor)
				{
					index_receptor = i;
				}
			}
			
			List<Veiculo> aux = listaClientes.get(index_emissor).getListaVeiculos();
			for(int i=0; i<aux.size(); i++)
			{
				listaClientes.get(index_receptor).getListaVeiculos().add(aux.get(i));
				for(int j=0; j<listaSinistros.size(); j++)
				{
					if(listaSinistros.get(j).getVeiculo() == aux.get(i))
					{
						listaSinistros.get(j).setCliente(listaClientes.get(index_receptor));
					}
				}
			}
			
			listaClientes.get(index_receptor).setValorSeguro(calcularPrecoSeguroCliente(listaClientes.get(index_receptor)));
			listaClientes.remove(index_emissor);
			
	}
	
	public double calcularPrecoSeguroCliente(Cliente cliente)
	{
		int cont=1;
		for(int i=0; i<listaSinistros.size();i++)
		{
			if(listaSinistros.get(i).getCliente().equals(cliente))
			{
				cont++;
			}
		}
		return cliente.calculaScore() * cont;

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
