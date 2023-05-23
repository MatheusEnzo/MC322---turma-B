import java.util.ArrayList;
import java.util.List;

public class Frota
{
	private final int code;
	private List<Veiculo> listaVeiculos;
	
	private static int codigo=1;
	
	public Frota()
	{
		this.code = codigo++;
		this.listaVeiculos = new ArrayList<Veiculo>();
	}

	public int getCode() {
		return code;
	}

	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(List<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}

	@Override
	public String toString()
	{
		String s = "Código: " + code + "Veículos:";
		for(Veiculo i:listaVeiculos)
		{
			s += " [" + i + "]";
		}
		return s;
	}
	
	public boolean addVeiculo()
	{
		// Ver se recebe veiculo ou parametros do veiculo como argumento
	}
	
	public boolean removeVeiculo()
	{
		// Ver se recebe veiculo ou parametros do veiculo como argumento
	}
}
