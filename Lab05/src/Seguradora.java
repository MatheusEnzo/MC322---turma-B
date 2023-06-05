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
	
	public boolean gerarSeguro(Veiculo veiculo, ClientePF cliente) throws ParseException
	{
		SeguroPF seguro = new SeguroPF(this, veiculo, cliente);
		return listaSeguros.add(seguro);
	}
	public boolean gerarSeguro(Frota frota, ClientePJ cliente) throws ParseException
	{
		SeguroPJ seguro = new SeguroPJ(this, frota, cliente);
		return listaSeguros.add(seguro);
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
