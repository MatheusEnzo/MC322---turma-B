import java.text.ParseException;
import java.util.Date;

public class SeguroPJ extends Seguro
{
	private Frota frota;
	private ClientePJ cliente;
	
	SeguroPJ(Seguradora seguradora, Frota frota, ClientePJ cliente) throws ParseException
	{
		super(seguradora);
		
		this.frota = frota;
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
		int idade = Validacao.calcularIdade(cliente.getDataFundacao());
		double base = CalcSeguro.VALOR_BASE.getFator() * (10 + (cliente.getQtdeFuncionarios()) /10) *
						(1 + 1/(frota.getListaVeiculos().size() +2)) * (1 + 1/(idade +2) ) * (2 + getListaSinistros().size() /10);
		
		int quantidade = 0;
		for(Condutor i : getListaCondutores())
		{
			quantidade += i.getListaSinistros().size();
		}
		
		return base * (5 + quantidade /10);
	}
	
	public Frota getFrota() {
		return frota;
	}
	
	@Override
	public Cliente getCliente() {
		return cliente;
	}

	@Override
	public String toString() {
		return super.toString() + "\nFrota: [" + frota + "]";
	}
	
	
}
