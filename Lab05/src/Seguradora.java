import java.text.ParseException;
import java.util.ArrayList;

public class Seguradora
{
	private final String cnpj;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Seguro> listaSeguros;
	
	private static ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
	
	public Seguradora(String cnpj, String nome, String telefone, String endereco, String email)
	{
		this.cnpj = cnpj.replaceAll("[^0-9]", "");
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.listaClientes = new ArrayList<Cliente>();
		this.listaSeguros = new ArrayList<Seguro>();
		
		listaSeguradoras.add(this);
	}
	
	public ArrayList<Cliente> listarClientes(String tipoCliente)
	{
		if(listaClientes.size()==0)
		{
			return listaClientes;
		}
		
		ArrayList<Cliente> listaTipo = new ArrayList<Cliente>();
		
		if(tipoCliente.equals("1"))
		{
			for(int i=0; i<listaClientes.size(); i++)
			{
				if(listaClientes.get(i).getClass() == ClientePF.class)
				{
					listaTipo.add(listaClientes.get(i));
				}
			}
			return listaTipo;	
		}
		
		for(int i=0; i<listaClientes.size();i++)
		{
			if(listaClientes.get(i).getClass() == ClientePJ.class)
			{
				listaTipo.add(listaClientes.get(i));
			}
		}
		return listaTipo;
	}
	
	public boolean gerarSeguro(String placa, String cpf) throws ParseException
	{
		for(Seguro teste : listaSeguros)
		{
			if(teste.getClass() == SeguroPF.class)
			{
				if(((SeguroPF) teste).getVeiculo().getPlaca().equals(placa))
				{
					return false;
				}
			}
		}
		
		for(int i=0; i<listaClientes.size(); i++)
		{
			if(listaClientes.get(i).getIdentificacao().equals(cpf))
			{
				for(int j=0; j<((ClientePF) listaClientes.get(i)).getListaVeiculos().size(); j++)
				{
					if(((ClientePF) listaClientes.get(i)).getListaVeiculos().get(j).getPlaca().equals(placa))
					{
						SeguroPF seguro = new SeguroPF(this, ((ClientePF) listaClientes.get(i)).getListaVeiculos().get(j), (ClientePF) listaClientes.get(i));
						return listaSeguros.add(seguro);
					}
				}
			}
		}
		return false;
	}
	public boolean gerarSeguro(int codigo, String cnpj) throws ParseException
	{
		for(Seguro teste : listaSeguros)
		{
			if(teste.getClass() == SeguroPJ.class)
			{
				if(((SeguroPJ) teste).getFrota().getCode() == codigo)
				{
					return false;
				}
			}
		}
		
		for(int i=0; i<listaClientes.size(); i++)
		{
			if(listaClientes.get(i).getIdentificacao().equals(cnpj))
			{
				for(int j=0; j<((ClientePJ) listaClientes.get(i)).getListaFrota().size(); j++)
				{
					if(((ClientePJ) listaClientes.get(i)).getListaFrota().get(j).getCode() == codigo)
					{
						SeguroPJ seguro = new SeguroPJ(this, ((ClientePJ) listaClientes.get(i)).getListaFrota().get(j), ((ClientePJ) listaClientes.get(i)));
						return listaSeguros.add(seguro);
					}
				}
			}
		}
		return false;
	}
	
	public boolean cancelarSeguro(String placa)
	{
		for(int i=0; i<listaSeguros.size(); i++)
		{
			if(listaSeguros.get(i).getClass() == SeguroPF.class)
			{
				if(((SeguroPF) listaSeguros.get(i)).getVeiculo().getPlaca().equals(placa))
				{
					listaSeguros.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	public boolean cancelarSeguro(int codigo)
	{
		for(int i=0; i<listaSeguros.size(); i++)
		{
			if(listaSeguros.get(i).getClass() == SeguroPJ.class)
			{
				if(((SeguroPJ) listaSeguros.get(i)).getFrota().getCode() == codigo)
				{
					listaSeguros.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean cadastrarCliente()
	{
		
	}
	
	public boolean removerCliente()
	{
		
	}
	
	public ArrayList<Seguro> getSegurosPorCliente(String identificacao)
	{
		ArrayList<Seguro> segurosCliente = new ArrayList<Seguro>();
		for(Seguro i : listaSeguros)
		{
			if(i.getCliente().getIdentificacao().equals(identificacao))
			{
				segurosCliente.add(i);
			}
		}
		return segurosCliente;
	}
	
	public ArrayList<Sinistro> getSinistrosPorCliente(String identificacao)
	{
		ArrayList<Sinistro> sinistrosCliente = new ArrayList<Sinistro>();
		for(Seguro i : listaSeguros)
		{
			if(i.getCliente().getIdentificacao().equals(identificacao))
			{
				for(Sinistro j: i.getListaSinistros())
				{
					sinistrosCliente.add(j);
				}
			}
		}
		return sinistrosCliente;
	}
	
	public double calcularReceita()
	{
		double receita = 0;
		for(Seguro i: listaSeguros)
		{
			receita += i.getValorMensal();
		}
		return receita;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpj() {
		return cnpj;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public ArrayList<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	public static ArrayList<Seguradora> getListaSeguradora()
	{
		return listaSeguradoras;
	}
}
