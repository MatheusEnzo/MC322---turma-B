import java.text.ParseException;
import java.util.Date;

public class SeguroPF extends Seguro
{
	private Veiculo veiculo;
	private ClientePF cliente;

	SeguroPF(Seguradora seguradora, Veiculo veiculo, ClientePF cliente) throws ParseException
	{
		super(seguradora);
		
		this.veiculo = veiculo;
		this.cliente = cliente;
		
		this.setValorMensal(this.calcularValor());
	}
	
	@Override
	public boolean autorizarCondutor(Condutor condutor)
	{
		for(Sinistro i : getListaSinistros())
		{
			if(i.getCondutor().getCpf().equals(condutor.getCpf()))
			{
				condutor.adicionarSinistro(i);
			}
		}
		getListaCondutores().add(condutor);
		this.setValorMensal(this.calcularValor());
		return true;
	}
	
	@Override
	public boolean desautorizarCondutor(String cpf)
	{
		cpf = cpf.replaceAll("[^0-9]", "");
		
		for(int i=0;i<getListaCondutores().size();i++)
		{
			if(getListaCondutores().get(i).getCpf().equals(cpf))
			{
				getListaCondutores().remove(i);
				this.setValorMensal(this.calcularValor());
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean gerarSinistro(Date data, String endereco, String cpf)
	{
		cpf = cpf.replaceAll("[^0-9]", "");
		
		for(int i=0;i<getListaCondutores().size();i++)
		{
			if(getListaCondutores().get(i).getCpf().equals(cpf))
			{
				Sinistro sinistro = new Sinistro(data, endereco, getListaCondutores().get(i), this);
				getListaSinistros().add(sinistro);
				getListaCondutores().get(i).adicionarSinistro(sinistro);
				this.setValorMensal(this.calcularValor());
				return true;
			}
		}
		return false;
	}
	
	@Override
	public double calcularValor()
	{
		int idade = Validacao.calcularIdade(cliente.getDataNascimento());
		
		double base = CalcSeguro.VALOR_BASE.getFator() * (1 + 1/(cliente.getListaVeiculos().size() +2)) * (2 + getListaSinistros().size() /10);
		int quantidade = 0;
		for(Condutor i: getListaCondutores())
		{
			quantidade += i.getListaSinistros().size();
		}
		base = base * (5 + (quantidade /10));
		
		if(idade<30)
		{
			return base * CalcSeguro.FATOR_18_30.getFator();
		}
		else if(idade<60)
		{
			return base * CalcSeguro.FATOR_30_60.getFator();
		}
		else
		{
			return base * CalcSeguro.FATOR_60_90.getFator();
		}
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	@Override
	public Cliente getCliente() {
		return cliente;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nVeiculo: [" + veiculo + "]";
	}
}