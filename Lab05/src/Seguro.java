import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class Seguro
{
	private final int id;
	private Date dataInicio;
	private Date dataFim;
	private Seguradora seguradora;
	private ArrayList<Sinistro> listaSinistros;
	private ArrayList<Condutor> listaCondutores;
	private double valorMensal;
	
	private static int id_seguro = 1;

	public Seguro(Seguradora seguradora) throws ParseException
	{
		this.seguradora = seguradora;
		this.listaSinistros = new ArrayList<Sinistro>();
		this.listaCondutores = new ArrayList<Condutor>();
		this.id = id_seguro++;
	
		Date dataAtual = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.dataInicio = sdf.parse(sdf.format(dataAtual));
	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataAtual);
		calendar.add(Calendar.YEAR, 1);
		this.dataFim = sdf.parse(sdf.format(calendar.getTime()));
	}
	
	public abstract boolean autorizarCondutor(Condutor condutor);
	
	public abstract boolean desautorizarCondutor(String cpf);
	
	public abstract boolean gerarSinistro(Date data, String endereco, String cpf);
	
	public abstract double calcularValor();
	
	public abstract Cliente getCliente();

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public double getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(double valorMensal) {
		this.valorMensal = valorMensal;
	}

	public int getId() {
		return id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public ArrayList<Condutor> getListaCondutores() {
		return listaCondutores;
	}

	@Override
	public String toString() {
		String s = "ID: " + id + "\nData de Inicio: " + dataInicio + "\nData de Fim: " + dataFim + "\nSeguradora: " + seguradora
				+ "\nLista de Sinistros:";
				
		for(Sinistro i: listaSinistros)
		{
			s += " ["+ i + "]";
		}
		s += "\nLista de Condutores:";
		for(Condutor i: listaCondutores)
		{
			s += " ["+ i + "]";
		}
		s += "\nValor Mensal: "+ valorMensal;
		return s;
	}
	
}

