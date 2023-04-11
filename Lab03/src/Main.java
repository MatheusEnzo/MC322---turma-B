import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main
{
	public static void main(String[] args) throws ParseException
	{
		Seguradora seguradora = new Seguradora("Seguradora1","(19)1234-5478","abc@email.com","Rua A");
		
		Veiculo veiculo1 = new Veiculo("1", "Marca A", "Modelo A", 2011);	
		Veiculo veiculo2 = new Veiculo("2", "Marca B", "Modelo B", 2012);
		Veiculo veiculo3 = new Veiculo("3", "Marca C", "Modelo C", 2013);
		Veiculo veiculo4 = new Veiculo("4", "Marca D", "Modelo D", 2014);
		Veiculo veiculo5 = new Veiculo("5", "Marca E", "Modelo E", 2015);
		Veiculo veiculo6 = new Veiculo("6 F", "Marca F", "Modelo F", 2016);
		
		ArrayList<Veiculo> veiculos_clienteFisico = new ArrayList<Veiculo>();
		veiculos_clienteFisico.add(veiculo1);
		veiculos_clienteFisico.add(veiculo2);
		
		ArrayList<Veiculo> veiculos_clienteJuridico = new ArrayList<Veiculo>();
		veiculos_clienteJuridico.add(veiculo3);
		veiculos_clienteJuridico.add(veiculo4);
		veiculos_clienteJuridico.add(veiculo5);
		veiculos_clienteJuridico.add(veiculo6);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date nascimento = sdf.parse("20/03/2002");
		Date fundacao = sdf.parse("20/03/2016");
		
		
		Cliente cliente_fisico = new ClientePF("Joao", "Rua 1", new Date(), "Medio", "Masculino", "Media", veiculos_clienteFisico , "355.373.390-05", nascimento);
		
		Cliente cliente_juridico = new ClientePF("Inc", "Rua 1", new Date(), "Fundamental", "Masculino", "Alta", veiculos_clienteFisico , "22.945.190/0001-52", fundacao);
	}

}
