import java.text.SimpleDateFormat;
import java.util.List;
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
	private static void executarOpcaoMenuExterno(MenuOperacoes op) {
		switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op);
				break;
			case GERAR_SINISTRO:
				List<Seguradora> seguradoras = Seguradora.getListaSeguradora();
				Scanner entrada = new Scanner(System.in);
				System.out.println("Lista de seguradoras:");
				for(int i=0; i<seguradoras.size(); i++)
				{
					System.out.println(i+1 + " - " + seguradoras.get(i).getNome());
				}
				System.out.print("Selecione a seguradora desejada: ");
				int opcaoSeg = entrada.nextInt();
				
				System.out.print("Digite o CPF ou CNPJ do cliente: ");
				String identificacao = entrada.nextLine();
				System.out.print("Digite a placa do veículo associado ao sinistro: ");
				String placa = entrada.nextLine();
				System.out.print("Digite a data do sinistro: ");
				String data = entrada.nextLine();
				System.out.print("Digite o endereco em que ocorreu o sinistro: ");
				String endereco = entrada.nextLine();
				
				if(seguradoras.get(opcaoSeg-1).gerarSinistro(data, endereco, placa, identificacao))
				{
					System.out.println("Sinistro gerado com sucesso");
				}
				else
				{
					System.out.println("Não foi possível gerar o sinistro");
				}
				
				break;
			case TRANSFERIR_SEGURO:
				System.out.println("Executar metodo tranferir seguro");
				break;
			case CALCULAR_RECEITA:
				for(Seguradora i : Seguradora.getListaSeguradora())
				{
					System.out.println("Nome da Seguradora: " + i.getNome() + " - Receita: "  + i.calcularReceita());
				}
				break;
			//case SAIR:
		}
	}
	
	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu) {
		Scanner entrada = new Scanner(System.in);
		List<Seguradora> seguradoras = Seguradora.getListaSeguradora();
		int tipo;
		switch(opSubmenu) {
		case CADASTRAR_CLIENTE:
			Scanner scanner = new Scanner(System.in);
			System.out.println("Tipos de clientes:\n1.Pessoa Física\n2.Pessoa Jurídica");
			System.out.print("Número da opção desejada: ");
			tipo = scanner.nextInt();
			
			if(tipo==1)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				System.out.print("Digite o nome do cliente");
				String name = scanner.nextLine();
				if(!Validacao.validarNome(name))
				{
					System.out.println("Nome inválido");
					break;
				}
				
				System.out.print("Digite o CPF do cliente: ");
				String cpf = scanner.nextLine();
				if(!Validacao.validarCPF(cpf))
				{
					System.out.println("CPF inválido");
					break;
				}
				System.out.print("Digite a data de nascimento do cliente no formato dd/mm/aaaa: ");
				
			}
			break;
		case CADASTRAR_VEICULO:
			System.out.println("Chamar metodo cadastrar veiculo");
			break;
		case CADASTRAR_SEGURADORA:
			System.out.println("Digite os dados da seguradora a ser cadastrada");
			System.out.print("Nome: ");
			String nome = entrada.nextLine();
			System.out.print("Telefone: ");
			String telefone = entrada.nextLine();
			System.out.print("Email: ");
			String email = entrada.nextLine();
			System.out.print("Endereço: ");
			String endereco = entrada.nextLine();
			
			new Seguradora(nome, telefone, email, endereco);
			break;
		case LISTAR_CLIENTES:
			// Seleção do tipo de cliente
			System.out.println("Tipos de clientes:\n1.Pessoa Física\n2.Pessoa Jurídica");
			System.out.print("Número da opção desejada: ");
			tipo = entrada.nextInt();
			
			for(Seguradora i : seguradoras)
			{
				System.out.println("\n" + i.getNome() + ":");
				if(tipo==1)
				{
					System.out.println();
					List<Cliente> pf = i.listarClientes("1");
					for(int j=0; j<pf.size();j++)
					{
						System.out.println(pf.get(j) + "\n");
					}
				}
				else if (tipo==2)
				{
					System.out.println();
					List<Cliente> pj = i.listarClientes("2");
					for(int j=0; j<pj.size();j++)
					{
						System.out.println(pj.get(j) + "\n");
					}
				}
			}
			break;
		case LISTAR_SINISTROS:
			// Selecao do tipo de listagem
			System.out.println("Listagem:\n1.Por seguradora\n2.Por cliente");
			System.out.print("Número da opção desejada: ");
			tipo = entrada.nextInt();
			
			if(tipo==1)
			{
				for (Seguradora i : seguradoras)
				{
					System.out.println("\n"+ i.getNome());
					for(Sinistro j : i.getListaSinistros())
					{
						System.out.println("[" + j + "]");
					}
				}
			}
			else if(tipo==2)
			{
				for(Seguradora i : seguradoras)
				{
					for(Cliente c : i.getListaClientes())
					{
						if(i.vizualizarSinistro(c.getIdentificacao()))
						{
							if(c.getClass() == ClientePF.class)
							{
								System.out.println("\nNome: " + c.getNome() + ", CPF: " + c.getIdentificacao());
							}
							else
							{
								System.out.println("\nNome: " + c.getNome() + ", CNPJ: " + c.getIdentificacao());
							}
							for(Sinistro s : i.getListaSinistros())
							{
								if(c == s.getCliente())
								{
									System.out.println("[" + s + "]");
								}
							}
						}
					}
					
				}
			}
			break;
		case LISTAR_VEICULOS:
			// Selecao do tipo de listagem
			System.out.println("Listagem:\n1.Por seguradora\n2.Por cliente");
			System.out.print("Número da opção desejada: ");
			tipo = entrada.nextInt();
			
			if(tipo==1)
			{
				for(Seguradora i : seguradoras)
				{
					System.out.println("\n"+ i.getNome());
					for(Cliente c : i.getListaClientes())
					{
						for(Veiculo carro : c.getListaVeiculos())
						{
							System.out.println("[" + carro + "]");
						}
					}
				}
			}
			else if(tipo==2)
			{
				for(Seguradora i : seguradoras)
				{
					for(Cliente c : i.getListaClientes())
					{
						if(c.getClass() == ClientePF.class)
						{
							System.out.println("\nNome: " + c.getNome() + ", CPF: " + c.getIdentificacao());
						}
						else
						{
							System.out.println("\nNome: " + c.getNome() + ", CNPJ: " + c.getIdentificacao());
						}
						for(Veiculo carro : c.getListaVeiculos())
						{
							System.out.println("[" + carro + "]");
						}
					}
				}
			}
			break;
		case EXCLUIR_CLIENTE:
			System.out.println("Chamar metodo excluir cliente");
			break;
		case EXCLUIR_VEICULO:
			System.out.println("Chamar metodo excluir veiculo");
			break;
		case EXCLUIR_SINISTRO:
			System.out.println("Chamar metodo excluir sinistro");
			break;
		//case VOLTAR:
		//	break;
		}
	}
	
	//executa os submenus: exibição do menu, leitura da opção e execução dos métodos
	private static void executarSubmenu(MenuOperacoes op) {
		SubmenuOpcoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(opSubmenu);
		}while(opSubmenu != SubmenuOpcoes.VOLTAR);
	}
	
	//executa o menu externo: exibição do menu, leitura da opção e execução da opção
	public static void main(String[] args) {
		MenuOperacoes op;
		do {
			exibirMenuExterno();
			op = lerOpcaoMenuExterno();
			executarOpcaoMenuExterno(op);
		}while(op != MenuOperacoes.SAIR);
		System.out.println("Saiu do sistema");
	}

}
