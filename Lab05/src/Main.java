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
		
		switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op);
				break;
			case GERAR_SINISTRO:
				System.out.print("Digite o CPF do condutor: ");
				String cpf = entrada.nextLine();
				cpf.replaceAll("[^0-9]", "");
				System.out.print("Digite a data (dd/mm/aaa): ");
				Date data = sdf.parse(entrada.nextLine());
				System.out.print("Digite o endereco: ");
				String endereco = entrada.nextLine();
				
				boolean flag = false;
				for(int i=0; i<seguradoras.get(0).getListaSeguros().size(); i++)
				{
					if(seguradoras.get(0).getListaSeguros().get(i).gerarSinistro(data, endereco, cpf))
					{
						System.out.println("Sinistro gerado com sucesso.");
						flag = true;
						break;
					}
				}
				if(flag==false)
				{
					System.out.println("Nenhum condutor com tal CPF.");
				}

				break;
			case GERAR_SEGURO:
				
				break;
			
			case CANCELAR_SEGURO:
				
				break;
			case AUTORIZAR_CONDUTOR:
				
				break;
			
			case DESAUTORIZAR_CONDUTOR:
				
				break;
			
			case CALCULAR_RECEITA:
				
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
			
			entrada.nextLine();
			break;
			
		case LISTAR_SINISTROS:
			
			break;
			
		case LISTAR_VEICULOS:
			
			break;
			
		case LISTAR_FROTAS:
			
			break;
			
		case LISTAR_SEGUROS_CLIENTES:
			
			break;
			
		case LISTAR_SINISTROS_CLIENTES:
			
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
