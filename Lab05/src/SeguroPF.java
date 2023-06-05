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
		
		super.setValorMensal(this.calcularValor());
	}
	
	@Override
	public boolean autorizarCondutor(Condutor condutor)
	{
		for(Sinistro i : super.getListaSinistros())
		{
			if(i.getCondutor().getCpf().equals(condutor.getCpf()))
			{
				condutor.adicionarSinistro(i);
			}
		}
		super.getListaCondutores().add(condutor);
		super.setValorMensal(this.calcularValor());
		return true;
	}
	
	@Override
	public boolean desautorizarCondutor(String cpf)
	{
		cpf.replaceAll("[^0-9]", "");
		
		for(int i=0;i<super.getListaCondutores().size();i++)
		{
			if(super.getListaCondutores().get(i).getCpf().equals(cpf))
			{
				super.getListaCondutores().remove(i);
				super.setValorMensal(this.calcularValor());
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean gerarSinistro(Date data, String endereco, String cpf)
	{
		for(int i=0;i<super.getListaCondutores().size();i++)
		{
			if(super.getListaCondutores().get(i).getCpf().equals(cpf))
			{
				Sinistro sinistro = new Sinistro(data, endereco, super.getListaCondutores().get(i), this);
				super.getListaSinistros().add(sinistro);
				super.getListaCondutores().get(i).adicionarSinistro(sinistro);
				super.setValorMensal(this.calcularValor());
				return true;
			}
		}
		return false;
	}
	
	@Override
	public double calcularValor()
	{
		int idade = Validacao.calcularIdade(cliente.getDataNascimento());
		
		double base = CalcSeguro.VALOR_BASE.getFator() * (1 + 1/(cliente.getListaVeiculos().size() +2)) * (2 + super.getListaSinistros().size() /10);
		int quantidade = 0;
		for(Condutor i: super.getListaCondutores())
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
	
}