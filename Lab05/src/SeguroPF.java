import java.text.ParseException;
import java.util.Date;

public class SeguroPF extends Seguro
{
	public Veiculo veiculo;
	public ClientePF cliente;
	
	SeguroPF(Seguradora seguradora, Veiculo veiculo, ClientePF cliente) throws ParseException
	{
		super(seguradora);
		
		this.veiculo = veiculo;
		this.cliente = cliente;
		
		this.valorMensal = this.calcularValor();
	}
	
	@Override
	public boolean autorizarCondutor(Condutor condutor)
	{
		listaCondutores.add(condutor);
		valorMensal = calcularValor();
		return true;
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
				valorMensal = calcularValor();
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
		int idade = Validacao.calcularIdade(cliente.getDataNascimento());
		
		double base = CalcSeguro.VALOR_BASE.getFator() * (1 + 1/(cliente.getListaVeiculos().size() +2)) * (2 + listaSinistros.size() /10);
		int quantidade = 0;
		for(Condutor i: listaCondutores)
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

	public ClientePF getCliente() {
		return cliente;
	}
	
}