import java.util.Date;
import java.util.ArrayList;

public class Condutor
{
	private final String cpf;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private Date dataNascimento;
	private ArrayList<Sinistro> listaSinistros;
	
	public Condutor(String cpf, String nome, String telefone, String endereco, String email, Date dataNascimento)
	{
		this.cpf = cpf.replaceAll("[^0-9]", "");
		this.nome = nome;
		this.telefone = telefone;
		this.endereco =  endereco;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.listaSinistros = new ArrayList<Sinistro>();
	}

	public boolean adicionarSinistro(Sinistro sinistro)
	{
		return listaSinistros.add(sinistro);
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

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}
	
}
