import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientePF extends Cliente
{
	private final String cpf;
	private String genero;
	private String educacao;
	private Date dataNascimento;
	private List<Veiculo> listaVeiculos;
	
	public ClientePF(String nome, String telefone, String endereco, String email,
			String cpf, String genero, String educacao, Date dataNascimento)
	{
		super(nome, telefone, endereco, email);
		
		this.cpf = cpf.replaceAll("[^0-9]", "");
		this.genero = genero;
		this.educacao = educacao;
		this.dataNascimento = dataNascimento;
		this.listaVeiculos = new ArrayList<Veiculo>();
	}
	
	public boolean cadastrarVeiculo(String placa, String marca, String modelo, int anoFabricacao)
	{
		// Verificacao da placa
		Veiculo v = new Veiculo(placa, marca, modelo, anoFabricacao);
		return listaVeiculos.add(v);
	}
	
	public boolean removerVeiculo(String placa)
	{
		for(int i=0; i<listaVeiculos.size(); i++)
		{
			if(listaVeiculos.get(i).getPlaca().equals(placa))
			{
				listaVeiculos.remove(i);
				return true;
			}
		}
		return false;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	public String getIdentificacao() {
		return cpf;
	}

	@Override
	public String toString() {
		String s = super.toString() + "CPF: " + cpf + "\nGênero: " + genero + "\nEducação: " + educacao + "\nData de Nascimento: "
				+ dataNascimento + "\nVeículos:";
		for(Veiculo i:listaVeiculos)
		{
			s += " [" + i + "]";
		}
		return s;
	}
}
