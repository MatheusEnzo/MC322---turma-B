import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
	
	//exibir menu externo
	private static void exibirMenuExterno() {
		MenuOperacoes menuOperacoes[] = MenuOperacoes.values();
		System.out.println("Menu principal");
		for(MenuOperacoes op: menuOperacoes) {
			System.out.println(op.ordinal() + " - " + op.getDescricao());
		}
	}
	
	/* exibir submenus
	 * se a lista de constantes do submenu for percorrida da mesma forma que o meu externo, a opção Voltar
	 * é printada com a posição que está na lista do enum (9 - Voltar). Por isso, a lista é percorrida 
	 * de forma diferente, tendo i como o inteiro correspondente. Assim, para listar o submenu de cadastros,
	 * por exemplo, vai ser printado "3 - Voltar".
	 */
	private static void exibirSubmenu(MenuOperacoes op) {
		SubmenuOpcoes[] submenu = op.getSubmenu();
		System.out.println(op.getDescricao());
		for(int i=0; i<submenu.length; i++) {
			System.out.println(i +" - " + submenu[i].getDescricao());
		}
	}
	
	//ler opções do menu externo
	private static MenuOperacoes lerOpcaoMenuExterno() {
		Scanner scanner = new Scanner(System.in);
		int opUsuario;
		MenuOperacoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = scanner.nextInt();
		}while(opUsuario < 0 || opUsuario > MenuOperacoes.values().length - 1);
		opUsuarioConst = MenuOperacoes.values()[opUsuario];
		return opUsuarioConst;
	}
	
	//ler opção dos submenus
	private static SubmenuOpcoes lerOpcaoSubmenu(MenuOperacoes op) {
		Scanner scanner = new Scanner(System.in);
		int opUsuario;
		SubmenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = scanner.nextInt();
		}while(opUsuario < 0 || opUsuario > op.getSubmenu().length - 1);
		opUsuarioConst = op.getSubmenu()[opUsuario];
		return opUsuarioConst;
	}
	
	//executar opções do menu externo
	private static void executarOpcaoMenuExterno(MenuOperacoes op) throws ParseException {
		Scanner entrada = new Scanner(System.in);
		ArrayList<Seguradora> seguradoras = Seguradora.getListaSeguradora();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		boolean flag;
		String identificacao;
		String cpf;
		
		switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op);
				break;
			case GERAR_SINISTRO:
				System.out.print("Digite o CPF do condutor: ");
				cpf = entrada.nextLine();
				cpf.replaceAll("[^0-9]", "");
				System.out.print("Digite a data (dd/mm/aaa): ");
				Date data = sdf.parse(entrada.nextLine());
				System.out.print("Digite o endereco: ");
				String endereco = entrada.nextLine();
				
				flag = false;
				for(Seguradora i : seguradoras)
				{
					for(int j=0; j<i.getListaSeguros().size(); j++)
					{
						if(i.getListaSeguros().get(j).gerarSinistro(data, endereco, cpf))
						{
							System.out.println("Sinistro gerado com sucesso.");
							flag = true;
							break;
						}
					}
					if(flag==true)
					{
						break;
					}
				}
				if(flag==false)
				{
					System.out.println("Nenhum condutor com tal CPF.");
				}

				break;
			case GERAR_SEGURO:
				System.out.print("Digite o CPF/CNPJ do cliente: ");
				identificacao = entrada.nextLine().replaceAll("[^0-9]", "");
				
				flag=false;
				if(identificacao.length() == 11)
				{
					System.out.print("Digite a placa do veículo: ");
					String placa = entrada.nextLine();
					for(Seguradora i : seguradoras)
					{
						if(i.gerarSeguro(placa, identificacao))
						{
							flag=true;
							break;
						}
					}
				}
				else if(identificacao.length() == 14)
				{
					System.out.print("Digite o código da frota: ");
					int codigo = entrada.nextInt();
					entrada.nextLine();
					for(Seguradora i : seguradoras)
					{
						if(i.gerarSeguro(codigo, identificacao))
						{
							flag=true;
							break;
						}
					}
				}
				else
				{
					System.out.println("CPF/CNPJ inválido.\n");
				}
				if(flag==false)
				{
					System.out.println("Não foi possível gerar o seguro.");
				}
				
				break;
			
			case CANCELAR_SEGURO:
				System.out.println("Seguro de:\n1 - Pessoa Física\n2 - Pessoa Jurídica");
				System.out.print("Digite uma opção: ");
				String tipo = entrada.nextLine();
				
				flag=false;
				if(tipo.equals("1"))
				{
					System.out.print("Digite a placa do veículo: ");
					String placa = entrada.nextLine();
					for(Seguradora i : seguradoras)
					{
						if(i.cancelarSeguro(placa))
						{
							flag=true;
							break;
						}
					}
				}
				else if(tipo.equals("2"))
				{
					System.out.print("Digite o código da frota: ");
					int codigo = entrada.nextInt();
					entrada.nextLine();
					for(Seguradora i : seguradoras)
					{
						if(i.cancelarSeguro(codigo))
						{
							flag=true;
							break;
						}
					}
				}
				if(flag==false)
				{
					System.out.println("Não foi possível cancelar o seguro.");
				}
				
				break;
			case AUTORIZAR_CONDUTOR:
				System.out.print("Digite o CPF do condutor: ");
				cpf = entrada.nextLine();
				System.out.print("Digite o nome do condutor: ");
				String nome = entrada.nextLine();
				System.out.print("Digite o telefone do condutor: ");
				String telefone = entrada.nextLine();
				System.out.print("Digite o endereço do condutor: ");
				String endereco1 = entrada.nextLine();
				System.out.print("Digite o email do condutor: ");
				String email = entrada.nextLine();
				System.out.print("Digite a data de nascimento do condutor (dd/mm/aaaa): ");
				Date dataNascimento = sdf.parse(entrada.nextLine());
				
				if(Validacao.validarCPF(cpf.replaceAll("[^0-9]", "")) && Validacao.validarNome(nome) && Validacao.validarIdade(dataNascimento))
				{
					Condutor condutor = new Condutor(cpf, nome, telefone, endereco1, email, dataNascimento);
					System.out.print("Digite o ID do seguro: ");
					int id = entrada.nextInt();
					entrada.nextLine();
					
					for(int i=0; i<seguradoras.size(); i++)
					{
						for(int j=0; j<seguradoras.get(i).getListaSeguros().size(); j++)
						{
							if(seguradoras.get(i).getListaSeguros().get(j).getId() == id)
							{
								if(seguradoras.get(i).getListaSeguros().get(j).getListaCondutores().contains(condutor))
								{
									System.out.println("Não foi possível autorizar condutor.\n");
								}
								else
								{
									seguradoras.get(i).getListaSeguros().get(j).autorizarCondutor(condutor);
									System.out.println("Condutor autorizado.\n");
								}
							}
						}
					}
				}
				else
				{
					System.out.println("Não foi possível autorizar condutor.\n");
				}
				break;
			
			case DESAUTORIZAR_CONDUTOR:
				System.out.print("Digite o CPF do condutor: ");
				cpf = entrada.nextLine();
				System.out.print("Digite o ID do seguro: ");
				int id = entrada.nextInt();
				entrada.nextLine();
				
				for(int i=0; i<seguradoras.size(); i++)
				{
					for(int j=0; j<seguradoras.get(i).getListaSeguros().size(); j++)
					{
						if(seguradoras.get(i).getListaSeguros().get(j).getId() == id)
						{
							if(seguradoras.get(i).getListaSeguros().get(j).desautorizarCondutor(cpf))
							{
								System.out.println("Condutor desautorizado.\n");
							}
							else
							{
								System.out.println("Condutor não encontrado.\n");
							}
						}
					}
				}
				break;
			
			case CALCULAR_RECEITA:
				for(Seguradora i : seguradoras)
				{
					System.out.println(i + "\nReceita: " + i.calcularReceita() + "\n");
				}
				break;
			//case SAIR:
		}
	}
	
	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu) throws ParseException {
		Scanner entrada = new Scanner(System.in);
		ArrayList<Seguradora> seguradoras = Seguradora.getListaSeguradora();
		
		switch(opSubmenu) {
		case CADASTRAR_CLIENTE:
			
			break;
		case CADASTRAR_VEICULO:
			
			break;

		case CADASTRAR_FROTA:
			
			break;
			
		case LISTAR_CLIENTES:
			System.out.println("Tipos de clientes:\n1 - Pessoa Física\n2 - Pessoa Jurídica");
			System.out.print("Digite uma opção: ");
			String tipo = entrada.nextLine();
			
			for(Seguradora i : seguradoras)
			{
				if(tipo.equals("1"))
				{
					ArrayList<Cliente> pf = i.listarClientes("1");
					for(int j=0; j<pf.size();j++)
					{
						System.out.println(pf.get(j) + "\n");
					}
				}
				else if (tipo.equals("2"))
				{
					ArrayList<Cliente> pj = i.listarClientes("2");
					for(int j=0; j<pj.size();j++)
					{
						System.out.println(pj.get(j) + "\n");
					}
				}
			}
			break;
			
		case LISTAR_SEGUROS_CLIENTES:
			System.out.print("Digite o CPF/CNPJ do cliente desejado: ");
			String identificacao = entrada.nextLine();
			
			for(Seguradora i : seguradoras)
			{
				ArrayList<Seguro> seguros = i.getSegurosPorCliente(identificacao.replaceAll("[^0-9]", ""));
				if(seguros.size()==0)
				{
					System.out.println("Cliente não cadastrado.\n");
				}
				else
				{
					for(Seguro j : seguros)
					{
						System.out.println(j + "\n");
					}
				}
			}
			break;
			
		case LISTAR_SINISTROS_CLIENTES:
			System.out.print("Digite o CPF/CNPJ do cliente desejado: ");
			String identificacao1 = entrada.nextLine();
			
			for(Seguradora i : seguradoras)
			{
				ArrayList<Sinistro> sinistros = i.getSinistrosPorCliente(identificacao1.replaceAll("[^0-9]", ""));
				if(sinistros.size()==0)
				{
					System.out.println("Não existem sinistros associados ao cliente.\n");
				}
				else
				{
					for(Sinistro j : sinistros)
					{
						System.out.println(j + "\n");
					}
				}
			}
			break;
			
		case EXCLUIR_CLIENTE:
			
			break;
			
		case EXCLUIR_VEICULO:
			
			break;
			
		case EXCLUIR_FROTA:
			
			break;
		
		//case VOLTAR:
		//	break;
		}
	}
	
	//executa os submenus: exibição do menu, leitura da opção e execução dos métodos
	private static void executarSubmenu(MenuOperacoes op) throws ParseException {
		SubmenuOpcoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(opSubmenu);
		}while(opSubmenu != SubmenuOpcoes.VOLTAR);
	}
	
	
	public static void main(String[] args) throws ParseException {
		
		Seguradora seguradora = new Seguradora("22.945.190/0001-52", "Teste", "(19)1234-5678", "Rua", "abc@email.com");
		
		
		//executa o menu externo: exibição do menu, leitura da opção e execução da opção		
		MenuOperacoes op;
		do {
			exibirMenuExterno();
			op = lerOpcaoMenuExterno();
			executarOpcaoMenuExterno(op);
		}while(op != MenuOperacoes.SAIR);
		System.out.println("Saiu do sistema");
	}

}
