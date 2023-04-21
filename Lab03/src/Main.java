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
		// Instanciação da seguradora
		Seguradora seguradora = new Seguradora("Seguradora1","(19)1234-5478","abc@email.com","Rua A");
		
		// Instaciação de veículos
		Veiculo veiculo1 = new Veiculo("1", "Marca A", "Modelo A", 2011);	
		Veiculo veiculo2 = new Veiculo("2", "Marca B", "Modelo B", 2012);
		Veiculo veiculo3 = new Veiculo("3", "Marca C", "Modelo C", 2013);
		Veiculo veiculo4 = new Veiculo("4", "Marca D", "Modelo D", 2014);
		Veiculo veiculo5 = new Veiculo("5", "Marca E", "Modelo E", 2015);
		Veiculo veiculo6 = new Veiculo("6", "Marca F", "Modelo F", 2016);
		Veiculo veiculo7 = new Veiculo("7", "Marca G", "Modelo G", 2017);
		Veiculo veiculo8 = new Veiculo("8", "Marca H", "Modelo H", 2018);
		Veiculo veiculo9 = new Veiculo("9", "Marca I", "Modelo I", 2019);
		Veiculo veiculo10 = new Veiculo("10", "Marca J", "Modelo J", 2020);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		// Instaciação de clientes, caso o CPF ou CNPJ seja inválido, o cliente não é cadastrado na seguradora
		ClientePF dummy = new ClientePF("Jose", "Rua 1", new Date(), "Medio", "Masculino", "Media", new ArrayList<Veiculo>() , "423.123.421-12", sdf.parse("20/03/2002"));
		
		ClientePF cliente_fisico_invalido = new ClientePF("Jose", "Rua 1", new Date(), "Medio", "Masculino", "Media", new ArrayList<Veiculo>() , "423.123.421-12", sdf.parse("20/03/2002"));
		if(cliente_fisico_invalido.validarCPF(cliente_fisico_invalido.getCpf()))
		{
			seguradora.cadastrarCliente(cliente_fisico_invalido);
		}
		ClientePF cliente_fisico = new ClientePF("Matheus Andre", "Rua 1", new Date(), "Medio Completo", "Masculino", "Media", new ArrayList<Veiculo>() , "355.373.390-05", sdf.parse("20/03/2002"));
		if(cliente_fisico.validarCPF(cliente_fisico.getCpf()))
		{
			seguradora.cadastrarCliente(cliente_fisico);
		}
		ClientePF cliente_fisico2 = new ClientePF("Matheus Enzo", "Rua 2", new Date(), "Superior Completo", "Masculino", "Media", new ArrayList<Veiculo>() , "17329230006", sdf.parse("05/10/1989"));
		if(cliente_fisico2.validarCPF(cliente_fisico2.getCpf()))
		{
			seguradora.cadastrarCliente(cliente_fisico2);
		}
		ClientePF cliente_fisico3 = new ClientePF("Laura", "Rua 3", new Date(), "Superior Incompleto", "Feminino", "Alta", new ArrayList<Veiculo>() , "199.328.510-51", sdf.parse("14/08/1964"));
		if(cliente_fisico3.validarCPF(cliente_fisico3.getCpf()))
		{
			seguradora.cadastrarCliente(cliente_fisico3);
		}
		
		ClientePJ cliente_juridico_invalido = new ClientePJ("Roberto", "Rua A", new Date(), "Fundamental", "Masculino", "Alta", new ArrayList<Veiculo>() , "22223333111144", sdf.parse("21/05/2016"));
		if(cliente_juridico_invalido.validar(cliente_juridico_invalido.getCnpj()))
		{
			seguradora.cadastrarCliente(cliente_juridico_invalido);
		}
		ClientePJ cliente_juridico = new ClientePJ("Lucas", "Rua A", new Date(), "Fundamental", "Masculino", "Alta", new ArrayList<Veiculo>() , "22.945.190/0001-52", sdf.parse("21/05/2016"));
		if(cliente_juridico.validar(cliente_juridico.getCnpj()))
		{
			seguradora.cadastrarCliente(cliente_juridico);
		}
		ClientePJ cliente_juridico2 = new ClientePJ("Maria", "Rua B", new Date(), "Superior Completo", "Feminino", "Alta", new ArrayList<Veiculo>() , "58352387000125", sdf.parse("24/04/2020"));
		if(cliente_juridico2.validar(cliente_juridico2.getCnpj()))
		{
			seguradora.cadastrarCliente(cliente_juridico2);
		}
		
		// Adição de veículos para os clientes
		cliente_fisico.getListaVeiculos().add(veiculo1);
		cliente_fisico.getListaVeiculos().add(veiculo2);
		cliente_fisico2.getListaVeiculos().add(veiculo3);
		cliente_fisico3.getListaVeiculos().add(veiculo4);
		cliente_juridico.getListaVeiculos().add(veiculo5);
		cliente_juridico.getListaVeiculos().add(veiculo6);
		cliente_juridico.getListaVeiculos().add(veiculo7);
		cliente_juridico.getListaVeiculos().add(veiculo8);
		cliente_juridico2.getListaVeiculos().add(veiculo9);
		cliente_juridico2.getListaVeiculos().add(veiculo10);
		
		// Geração de sinistros, testando casos inválidos de adição
		seguradora.gerarSinistro("Dia 10", "Avenida A", veiculo1, dummy);
		seguradora.gerarSinistro("Dia 12", "Avenida B", veiculo1, cliente_fisico);
		seguradora.gerarSinistro("Dia 12", "Avenida B", veiculo3, cliente_fisico);
		seguradora.gerarSinistro("Dia 15", "Avenida C", veiculo3, cliente_fisico2);
		seguradora.gerarSinistro("Dia 20", "Avenida T", veiculo5, cliente_juridico);
		seguradora.gerarSinistro("Dia 21", "Avenida U", veiculo5, cliente_juridico);
		seguradora.gerarSinistro("Dia 22", "Avenida V", veiculo6, cliente_juridico);
		seguradora.gerarSinistro("Dia 23", "Avenida W", veiculo8, cliente_juridico);
		seguradora.gerarSinistro("Dia 30", "Avenida X", veiculo10, cliente_juridico2);
		
		Scanner entrada = new Scanner(System.in);
		
		while(true)
		{
			// Menu de entrada
			System.out.println("Opções de operações:\n1.Visualizar dados da seguradora\n2.Listar clientes da seguradora"
					+ "\n3.Consultar a existência de sinistros de um cliente\n4.Listar todos sinistros\n5.Descadastrar cliente\n6.Terminar o programa");
			System.out.print("Número da opção desejada: ");
			int leitura = entrada.nextInt();
			entrada.nextLine();
			
			// Operações dependendo da leitura da entrada (caso aconteça entraada de caracteres não numéricos ocorre um erro de execução)
			if(leitura==1)
			{
				System.out.println(seguradora.toString() + "\n");
			}
			else if(leitura==2)
			{
				// Seleção do tipo de cliente
				System.out.println("Tipos de clientes:\n1.Pessoa Física\n2.Pessoa Jurídica");
				System.out.print("Número da opção desejada: ");
				int tipo = entrada.nextInt();
				
				if(tipo==1)
				{
					List<Cliente> pf = seguradora.listarClientes("1");
					for(int i=0; i<pf.size();i++)
					{
						System.out.println(pf.get(i).toString() + "\n");
					}
				}
				else if (tipo==2)
				{
					List<Cliente> pj = seguradora.listarClientes("2");
					for(int i=0; i<pj.size();i++)
					{
						System.out.println(pj.get(i).toString() + "\n");
					}
				}
			}
			else if (leitura==3)
			{
				System.out.print("Digite o nome do cliente: ");
				String name = entrada.nextLine();
				
				if(seguradora.vizualizarSinistro(name))
				{
					System.out.println("Existem sinistros relacionados ao cliente.\n");
				}
				else
				{
					System.out.println("Não existem sinistros relacionados ao cliente.\n");
				}
			}
			else if(leitura==4)
			{
				List<Sinistro> sinistros = seguradora.listarSinistros();
				
				for(int i=0;i<sinistros.size();i++)
				{
					System.out.println("[" + sinistros.get(i).toString() + "]\n");
				}
			}
			else if(leitura==5)
			{
				System.out.print("Digite o nome do cliente: ");
				String name = entrada.nextLine();
				
				if(seguradora.removerCliente(name))
				{
					System.out.println("O cliente foi descadastrado.\n");
				}
				else
				{
					System.out.println("Cliente não cadastrado na seguradora.\n");
				}
				
			}
			else if(leitura==6)
			{
				break;
			}
			else
			{
				System.out.println("Selecione uma opção válida.\n");
			}
		}
	}
}
