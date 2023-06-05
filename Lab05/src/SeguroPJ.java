import java.text.ParseException;
import java.util.Date;

public class SeguroPJ extends Seguro
{
	public Frota frota;
	public ClientePJ cliente;
	
	SeguroPJ(Seguradora seguradora, Frota frota, ClientePJ cliente) throws ParseException
	{
		super(seguradora);
		
		this.frota = frota;
		this.cliente = cliente;
		
		this.valorMensal = this.calcularValor();
	}
	
	@Override
	public boolean autorizarCondutor(Condutor condutor)
	{
		return listaCondutores.add(condutor);
	}
	
	@Override
	public boolean desautorizarCondutor(String cpf)
	{
		cpf.replaceAll("[^0-9]", "");
		
		for(int i=0;i<listaCondutores.size();i++)
		{
			if(listaCondutores.get(i).getCpf().equals(cpf))
			{
				listaCondutores.remove(i);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean gerarSinistro(Date data, String endereco, String cpf)
	{
		for(int i=0;i<listaCondutores.size();i++)
		{
			if(listaCondutores.get(i).getCpf().equals(cpf))
			{
				Sinistro sinistro = new Sinistro(data, endereco, listaCondutores.get(i), this);
				listaSinistros.add(sinistro);
				listaCondutores.get(i).adicionarSinistro(sinistro);
				valorMensal = calcularValor();
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
						(1 + 1/(frota.getListaVeiculos().size() +2)) * (1 + 1/(idade +2) ) * (2 + listaSinistros.size() /10);
		
		int quantidade = 0;
		for(Condutor i : listaCondutores)
		{
			quantidade += i.getListaSinistros().size();
		}
		
		return base * (5 + quantidade /10);
	}
	
	public Frota getFrota() {
		return frota;
	}

	public ClientePJ getCliente() {
		return cliente;
	}
	
	
}
