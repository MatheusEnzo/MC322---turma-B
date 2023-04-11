import java.util.Date;
import java.util.ArrayList;
public class Main
{
	public static void main(String[] args)
	{
		Veiculo veiculo1 = new Veiculo("ABC", "Marca X", "Modelo X", 2022);
		System.out.println(veiculo1);
		
		Veiculo veiculo2 = new Veiculo("ABCD", "Marca XY", "Modelo XY", 2022);
		System.out.println(veiculo2);
		
		ArrayList<Veiculo> carros = new ArrayList<Veiculo>();
		carros.add(veiculo1);
		carros.add(veiculo2);
		
		
		Date now = new Date(System.currentTimeMillis());
		Date nascimento = new Date();
		
		Cliente fisica = new ClientePF("Matheus", "Rua 1", now, "Medio", "Masculino", "Media", carros , "46220543882", nascimento);
		System.out.println(fisica);
		System.out.println(fisica.getClass());
	}

}
