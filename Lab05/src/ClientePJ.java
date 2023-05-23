import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientePJ extends Cliente
{
	private final String cnpj;
	private Date dataFundacao;
	private int qtdeFuncionarios;
	private List<Frota> listaFrota;
	
	ClientePJ(String nome, String telefone, String endereco, String email,
			String cnpj, Date dataFundacao, int qtdeFuncionarios)
	{
		super(nome, telefone, endereco, email);
		
		this.cnpj = cnpj.replaceAll("[^0-9]", "");
		this.dataFundacao = dataFundacao;
		this.qtdeFuncionarios = qtdeFuncionarios;
		this.listaFrota = new ArrayList<Frota>();
	}
	
	//Getters e setters
	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public int getQtdeFuncionarios() {
		return qtdeFuncionarios;
	}

	public void setQtdeFuncionarios(int qtdeFuncionarios) {
		this.qtdeFuncionarios = qtdeFuncionarios;
	}

	public List<Frota> getListaFrota() {
		return listaFrota;
	}

	public void setListaFrota(List<Frota> listaFrota) {
		this.listaFrota = listaFrota;
	}

	public String getCnpj() {
		return cnpj;
	}
	
	@Override
	// String no formato de impressao
	public String toString()
	{
		String string = super.toString();
		string += "\nCNPJ: " + cnpj + "\nData de Fundação: " + dataFundacao +
					"\nQuantidade de Funcionários: " + qtdeFuncionarios + "Frota: ";
		
		for(Frota i : listaFrota)
		{
			string += i + "\n";
		}
		return string;
	}
	
	public boolean cadastrarFrota(Frota frota)
	{
		//Validar cada placa de veiculo
		
		return listaFrota.add(frota);
	}
	
	public boolean atualizarFrota()
	{
		
	}
	
	public getVeiculosPorFrota()
	{
		
	}
}
