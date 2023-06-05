import java.util.ArrayList;

public class Frota
{
	private final int code;
	private ArrayList<Veiculo> listaVeiculos;
	
	private static int codigo=1;
	
	public Frota()
	{
		this.code = codigo++;
		this.listaVeiculos = new ArrayList<Veiculo>();
	}

	public boolean addVeiculo(Veiculo veiculo)
	{
		return listaVeiculos.add(veiculo);
	}
	
	public boolean removeVeiculo(Veiculo veiculo)
	{
		if(listaVeiculos.contains(veiculo))
		{
			return listaVeiculos.remove(veiculo);
		}
		return false;
	}
	
	public int getCode() {
		return code;
	}

	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
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
	
}
