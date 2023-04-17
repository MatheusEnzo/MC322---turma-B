import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date nascimento = sdf.parse("20/03/2002");
		Date fundacao = sdf.parse("21/05/2016");
		
		
		ClientePF cliente_fisico = new ClientePF("Joao", "Rua 1", new Date(), "Medio", "Masculino", "Media", new ArrayList<Veiculo>() , "355.373.390-05", nascimento);
		if(cliente_fisico.validarCPF(cliente_fisico.getCpf()))
		{
			seguradora.cadastrarCliente(cliente_fisico);
		}
		ClientePF cliente_fisico_invalido = new ClientePF("Jose", "Rua 1", new Date(), "Medio", "Masculino", "Media", new ArrayList<Veiculo>() , "423.123.421-12", nascimento);
		if(cliente_fisico_invalido.validarCPF(cliente_fisico_invalido.getCpf()))
		{
			seguradora.cadastrarCliente(cliente_fisico_invalido);
		}
		
		ClientePJ cliente_juridico = new ClientePJ("Inc", "Rua 1", new Date(), "Fundamental", "Masculino", "Alta", new ArrayList<Veiculo>() , "22.945.190/0001-52", fundacao);
		if(cliente_juridico.validar(cliente_juridico.getCnpj()))
		{
			seguradora.cadastrarCliente(cliente_juridico);
		}
		ClientePJ cliente_juridico_invalido = new ClientePJ("Inc2", "Rua 1", new Date(), "Fundamental", "Masculino", "Alta", new ArrayList<Veiculo>() , "22223333111144", fundacao);
		if(cliente_juridico_invalido.validar(cliente_juridico_invalido.getCnpj()))
		{
			seguradora.cadastrarCliente(cliente_juridico_invalido);
		}
		
		cliente_fisico.getListaVeiculos().add(veiculo1);
		cliente_fisico.getListaVeiculos().add(veiculo2);
		cliente_juridico.getListaVeiculos().add(veiculo3);
		cliente_juridico.getListaVeiculos().add(veiculo4);
		cliente_juridico.getListaVeiculos().add(veiculo5);
		cliente_juridico.getListaVeiculos().add(veiculo6);
		
		Scanner entrada = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("Opções de operações:\n1.Visualizar dados da seguradora\n2.Listar clientes da seguradora\n3.Consultar a existência de sinistros de um cliente\n4.Terminar o programa");
			System.out.print("Número da opção desejada: ");
			int leitura = entrada.nextInt();
			
			if(leitura==1)
			{
				System.out.println(seguradora.toString());
			}
			else if(leitura==2)
			{
				System.out.println("Tipos de clientes:\n1.Pessoa Física\n2.Pessoa Jurídica");
				System.out.print("Número da opção desejada: ");
				int tipo = entrada.nextInt();
				if(tipo==1)
				{
					List<Cliente> pf = seguradora.listarClientes("1");
					for(int i=0; i<pf.size();i++)
					{
						System.out.println(pf.get(i).toString());
					}
				}
				else if (tipo==2)
				{
					List<Cliente> pj = seguradora.listarClientes("2");
					for(int i=0; i<pj.size();i++)
					{
						System.out.println(pj.get(i).toString());
					}
				}
			}
			else if (leitura==3)
			{
				System.out.print("Digite o nome do cliente: ");
				String name = entrada.next();
				if(seguradora.vizualizarSinistro(name))
				{
					System.out.println("Existem sinistros relacionados ao cliente");
				}
				else
				{
					System.out.println("Não existem sinistros relacionados ao cliente");
				}
			}
			else if(leitura==4)
			{
				break;
			}
		}
	}

}
