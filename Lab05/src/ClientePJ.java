import java.util.ArrayList;
import java.util.Date;

public class ClientePJ extends Cliente
{
	private final String cnpj;
	private Date dataFundacao;
	private int qtdeFuncionarios;
	private ArrayList<Frota> listaFrota;
	
	ClientePJ(String nome, String telefone, String endereco, String email,
			String cnpj, Date dataFundacao, int qtdeFuncionarios)
	{
		super(nome, telefone, endereco, email);
		
		this.cnpj = cnpj.replaceAll("[^0-9]", "");
		this.dataFundacao = dataFundacao;
		this.qtdeFuncionarios = qtdeFuncionarios;
		this.listaFrota = new ArrayList<Frota>();
	}
	
	public boolean cadastrarFrota(Frota frota)
	{
		return listaFrota.add(frota);
	}
	
	public boolean atualizarFrota(int code, int opcao, Veiculo veiculo)
	{
		for(int i=0; i<listaFrota.size(); i++)
		{
			if(listaFrota.get(i).getCode() == code)
			{
				if(opcao==0)
				{
					return listaFrota.get(i).addVeiculo(veiculo);
				}
				else if(opcao==1)
				{
					return listaFrota.get(i).removeVeiculo(veiculo);
				}
				else
				{
					listaFrota.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	public ArrayList<Veiculo> getVeiculosPorFrota(int code)
	{
		for(Frota i : listaFrota)
		{
			if(i.getCode() == code)
			{
				return i.getListaVeiculos();
			}
		}
		return null;
	}
	
	public Date getDataFundacao() {
		return dataFundacao;
	}

	public int getQtdeFuncionarios() {
		return qtdeFuncionarios;
	}

	public void setQtdeFuncionarios(int qtdeFuncionarios) {
		this.qtdeFuncionarios = qtdeFuncionarios;
	}

	public ArrayList<Frota> getListaFrota() {
		return listaFrota;
	}

	public String getIdentificacao() {
		return cnpj;
	}
	
	@Override
	public String toString()
	{
		String string = super.toString();
		string += "\nCNPJ: " + cnpj + "\nData de Fundação: " + dataFundacao +
					"\nQuantidade de Funcionários: " + qtdeFuncionarios + "\nFrota: ";
		
		for(Frota i : listaFrota)
		{
			string += i + "\n";
		}
		return string;
	}
}
