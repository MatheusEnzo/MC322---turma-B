import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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
		List<Seguradora> seguradoras = Seguradora.getListaSeguradora();
		Scanner entrada = new Scanner(System.in);
		switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op);
				break;
			case GERAR_SINISTRO:
				System.out.println("Lista de seguradoras:");
				for(int i=0; i<seguradoras.size(); i++)
				{
					System.out.println(i + " - " + seguradoras.get(i).getNome());
				}
				System.out.print("Selecione a seguradora desejada: ");
				int opcaoSeg = entrada.nextInt();
				entrada.nextLine();
				
				System.out.print("Digite o CPF ou CNPJ do cliente: ");
				String identificacao = entrada.nextLine();
				identificacao = identificacao.replaceAll("[^0-9]", "");
				System.out.print("Digite a placa do veículo associado ao sinistro: ");
				String placa = entrada.nextLine();
				placa = placa.replaceAll("\\s+", "").replaceAll("-", "");
				System.out.print("Digite a data do sinistro: ");
				String data = entrada.nextLine();
				System.out.print("Digite o endereco em que ocorreu o sinistro: ");
				String endereco = entrada.nextLine();
				
				if(seguradoras.get(opcaoSeg).gerarSinistro(data, endereco, placa, identificacao))
				{
					System.out.println("Sinistro gerado com sucesso");
				}
				else
				{
					System.out.println("Não foi possível gerar o sinistro");
				}
				
				break;
			case TRANSFERIR_SEGURO:
				Scanner leitura = new Scanner(System.in);
				System.out.print("Digite o CPF ou CNPJ do cliente que deseja transferir o seguro: ");
				String id_emissor = leitura.nextLine();
				System.out.print("Digite o CPF ou CNPJ do cliente que irá receber o seguro: ");
				String id_receptor = leitura.nextLine();
				int existe_emissor = 0, existe_receptor = 0;
				for(Seguradora i: seguradoras)
				{
					existe_emissor = 0;
					existe_receptor = 0;
					for(Cliente j : i.getListaClientes())
					{
						if(j.getIdentificacao() == id_emissor)
						{
							existe_emissor = 1;
						}
						if(j.getIdentificacao() == id_receptor)
						{
							existe_receptor = 1;
						}
					}
					if(existe_emissor==1 && existe_receptor==1)
					{
						i.transferencia(id_emissor, id_receptor);
						System.out.println("Transferência realizada");
						break;
					}
				}
				if(existe_emissor==0 || existe_receptor==0)
				{
					System.out.println("Os clientes não estão cadastrados na mesma seguradora");
				}
				
				break;
			case CALCULAR_RECEITA:
				for(Seguradora i : seguradoras)
				{
					System.out.println("Nome da Seguradora: " + i.getNome() + " - Receita: "  + i.calcularReceita());
				}
				break;
			//case SAIR:
		}
	}
	
	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu) throws ParseException {
		Scanner entrada = new Scanner(System.in);
		List<Seguradora> seguradoras = Seguradora.getListaSeguradora();
		int tipo;
		switch(opSubmenu) {
		case CADASTRAR_CLIENTE:
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("Tipos de clientes:\n1 - Pessoa Física\n2 - Pessoa Jurídica");
			System.out.print("Número da opção desejada: ");
			tipo = entrada.nextInt();
			entrada.nextLine();
			
			System.out.println("Lista de seguradoras:");
			for(int i=0; i<seguradoras.size(); i++)
			{
				System.out.println(i + " - " + seguradoras.get(i).getNome());
			}
			System.out.print("Selecione em qual seguradora deseja cadastrar: ");
			int opcaoSeg = entrada.nextInt();
			entrada.nextLine();
			
			if(tipo==1)
			{
				System.out.println("Digite as informações necessárias para o cadastro:");
				System.out.print("Nome: ");
				String nome = entrada.nextLine();
				if(!Validacao.validarNome(nome))
				{
					System.out.println("Nome inválido");
					break;
				}
				
				System.out.print("CPF: ");
				String cpf = entrada.nextLine();
				cpf = cpf.replaceAll("[^0-9]", "");
				if(!Validacao.validarCPF(cpf))
				{
					System.out.println("CPF inválido");
					break;
				}
				
				System.out.print("Data de nascimento no formato dd/mm/aaaa: ");
				String dataN = entrada.nextLine();
				Date dataNascimento = sdf.parse(dataN);
				if(!Validacao.validarIdade(dataNascimento))
				{
					System.out.println("Data de nascimento inválida");
					break;
				}
				
				System.out.print("Data da licensa no formato dd/mm/aaaa: ");
				String dataL = entrada.nextLine();
				Date dataLicensa = sdf.parse(dataL);
				System.out.print("Endereço: ");
				String endereco = entrada.nextLine();
				System.out.print("Gênero: ");
				String genero = entrada.nextLine();
				System.out.print("Educação: ");
				String educacao = entrada.nextLine();
				System.out.print("Classe Econômica: ");
				String classe = entrada.nextLine();
				ClientePF novo = new ClientePF(nome, endereco, cpf, genero, dataLicensa, educacao, dataNascimento, classe);
				
				int opcao = 1;
				do
				{
					System.out.println("Cadastro de veículo:");
					System.out.print("Placa: ");
					String placa = entrada.nextLine();
					placa = placa.replaceAll("\\s+", "").replaceAll("-", "");
					if(!Validacao.verificarPlaca(placa))
					{
						System.out.println("Placa inválida");
						System.out.println("Deseja tentar novamente?\n1 - Sim\n2 - Não");
						opcao = entrada.nextInt();
						entrada.nextLine();
						if(opcao==1)
						{
							continue;
						}
						else if(opcao==2)
						{
							break;
						}
					}
					System.out.print("Marca: ");
					String marca = entrada.nextLine();
					System.out.print("Modelo: ");
					String modelo = entrada.nextLine();
					System.out.print("Ano de fabricação: ");
					int ano = entrada.nextInt();
					entrada.nextLine();
					Veiculo v = new Veiculo(placa, marca, modelo, ano);
					novo.getListaVeiculos().add(v);
					System.out.println("Veículo cadastrado com sucesso");
					System.out.println("Deseja cadastrar outro veículo?\n1 - Sim\n2 - Não");
					opcao = entrada.nextInt();
					entrada.nextLine();
				}while(opcao!=2);
				if(seguradoras.get(opcaoSeg).cadastrarCliente(novo))
				{
					novo.setValorSeguro(seguradoras.get(opcaoSeg).calcularPrecoSeguroCliente(novo));
					System.out.println("Cliente cadastrado com sucesso");
				}
				else
				{
					System.out.println("CPF já cadastrado");
				}
			}
			else if(tipo==2)
			{
				System.out.println("Digite as informações necessárias para o cadastro:");
				System.out.print("Nome: ");
				String nome = entrada.nextLine();
				
				System.out.print("CNPJ: ");
				String cnpj = entrada.nextLine();
				cnpj = cnpj.replaceAll("[^0-9]", "");
				if(!Validacao.validarCNPJ(cnpj))
				{
					System.out.println("CNPJ inválido");
					break;
				}
				
				System.out.print("Data de fundação no formato dd/mm/aaaa: ");
				String dataF = entrada.nextLine();
				Date dataFundacao = sdf.parse(dataF);
				if(!Validacao.validarFundacao(dataFundacao))
				{
					System.out.println("Data de fundação inválida");
					break;
				}
				
				System.out.print("Quantidade de funcionários: ");
				int qtde = entrada.nextInt();
				entrada.nextLine();
				System.out.print("Endereço: ");
				String endereco = entrada.nextLine();
				ClientePJ novo = new ClientePJ(nome, endereco, cnpj, dataFundacao, qtde);
				
				int opcao = 1;
				do
				{
					System.out.println("Cadastro de veículo:");
					System.out.print("Placa: ");
					String placa = entrada.nextLine();
					placa = placa.replaceAll("\\s+", "").replaceAll("-", "");
					if(!Validacao.verificarPlaca(placa))
					{
						System.out.println("Placa inválida");
						System.out.println("Deseja tentar novamente?\n1 - Sim\n2 - Não");
						opcao = entrada.nextInt();
						entrada.nextLine();
						if(opcao==1)
						{
							continue;
						}
						else if(opcao==2)
						{
							break;
						}
					}
					System.out.print("Marca: ");
					String marca = entrada.nextLine();
					System.out.print("Modelo: ");
					String modelo = entrada.nextLine();
					System.out.print("Ano de fabricação: ");
					int ano = entrada.nextInt();
					entrada.nextLine();
					Veiculo v = new Veiculo(placa, marca, modelo, ano);
					novo.getListaVeiculos().add(v);
					System.out.println("Veículo cadastrado com sucesso");
					System.out.println("Deseja cadastrar outro veículo?\n1 - Sim\n2 - Não");
					opcao = entrada.nextInt();
					entrada.nextLine();
				}while(opcao!=2);
				if(seguradoras.get(opcaoSeg).cadastrarCliente(novo))
				{
					novo.setValorSeguro(seguradoras.get(opcaoSeg).calcularPrecoSeguroCliente(novo));
					System.out.println("Cliente cadastrado com sucesso");
				}
				else
				{
					System.out.println("CNPJ já cadastrado");
				}
					
			}
			break;
		case CADASTRAR_VEICULO:
			System.out.println("Lista de seguradoras:");
			for(int i=0; i<seguradoras.size(); i++)
			{
				System.out.println(i + " - " + seguradoras.get(i).getNome());
			}
			System.out.print("Selecione em qual seguradora deseja cadastrar: ");
			int qual = entrada.nextInt();
			entrada.nextLine();
			
			System.out.print("Digite o CPF ou CNPJ do cliente associado ao carro: ");
			String id = entrada.nextLine();
			id = id.replaceAll("[^0-9]", "");
			int test=0;
			for(Cliente i : seguradoras.get(qual).getListaClientes())
			{
				if(i.getIdentificacao().equals(id))
				{
					test=1;
					System.out.println("Cadastro de veículo:");
					System.out.print("Placa: ");
					String placa = entrada.nextLine();
					placa = placa.replaceAll("\\s+", "").replaceAll("-", "");
					if(!Validacao.verificarPlaca(placa))
					{
						System.out.println("Placa inválida");
						break;
					}
					System.out.print("Marca: ");
					String marca = entrada.nextLine();
					System.out.print("Modelo: ");
					String modelo = entrada.nextLine();
					System.out.print("Ano de fabricação: ");
					int ano = entrada.nextInt();
					entrada.nextLine();
					Veiculo v = new Veiculo(placa, marca, modelo, ano);
					if(i.getListaVeiculos().add(v))
					{
						System.out.println("Veículo cadastrado com sucesso");
					}
					else
					{
						System.out.println("Placa já cadastrada");
					}
					break;
				}
			}
			if(test==0)
			{
				System.out.print("Cliente não encontrado na seguradora");
			}
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
			System.out.println("Seguradora cadastrada com sucesso");
			break;
		case LISTAR_CLIENTES:
			// Seleção do tipo de cliente
			System.out.println("Tipos de clientes:\n1 - Pessoa Física\n2 - Pessoa Jurídica");
			System.out.print("Número da opção desejada: ");
			tipo = entrada.nextInt();
			entrada.nextLine();
			
			for(Seguradora i : seguradoras)
			{
				if(i.getListaClientes().size()==0)
				{
					System.out.println("\nNome da seguradora: " + i.getNome() + ":");
					System.out.println("Não há clientes cadastrados");
					continue;
				}
				System.out.println("\nNome da seguradora: " + i.getNome());
				if(tipo==1)
				{
					List<Cliente> pf = i.listarClientes("1");
					for(int j=0; j<pf.size();j++)
					{
						System.out.println(pf.get(j) + "\n");
					}
				}
				else if (tipo==2)
				{
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
			entrada.nextLine();
			
			if(tipo==1)
			{
				for (Seguradora i : seguradoras)
				{
					System.out.println("\nNome da seguradora: " + i.getNome());
					System.out.println(i.listarSinistros());
				}
			}
			else if(tipo==2)
			{
				for(Seguradora i : seguradoras)
				{
					for(Cliente c : i.getListaClientes())
					{
						if(i.visualizarSinistro(c.getIdentificacao()))
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
								if(c.equals(s.getCliente()))
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
			System.out.println("Listagem:\n1 - Por seguradora\n2 - Por cliente");
			System.out.print("Número da opção desejada: ");
			tipo = entrada.nextInt();
			entrada.nextLine();
			
			if(tipo==1)
			{
				for(Seguradora i : seguradoras)
				{
					System.out.println("\nNome da seguradora: "+ i.getNome());
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
			System.out.print("Digite o CPF ou CNPJ do cliente que deseja remover: ");
			String identificacao = entrada.nextLine();
			identificacao = identificacao.replaceAll("[^0-9]", "");
			int flag = 0;
			for(Seguradora i : seguradoras)
			{
				if(i.removerCliente(identificacao))
				{
					flag = 1;
				}
			}
			if(flag==1)
			{
				System.out.println("Cliente removido com sucesso de todas as seguradoras");
			}
			else
			{
				System.out.println("Cliente não encontrado em nenhuma seguradora");
			}
			break;
		case EXCLUIR_VEICULO:
			System.out.print("Digite a placa do carro que deseja remover: ");
			String placa = entrada.nextLine();
			placa = placa.replaceAll("\\s+", "").replaceAll("-", "");
			int flag2=0;
			for(Seguradora i : seguradoras)
			{
				if(i.removerVeiculo(placa))
				{
					flag2 = 1;
					break;
				}
			}
			if(flag2==1)
			{
				System.out.println("Veículo removido com sucesso");
			}
			else
			{
				System.out.println("Veículo não encontrado");
			}
			break;
		case EXCLUIR_SINISTRO:
			System.out.print("Digite o ID do sinistro que deseja remover: ");
			int sinistro = entrada.nextInt();
			int flag3 = 0;
			for(Seguradora i: seguradoras)
			{
				if(i.removerSinistro(sinistro))
				{
					flag3 = 1;
					break;
				}
			}
			if(flag3==1)
			{
				System.out.println("Sinistro removido com sucesso");
			}
			else
			{
				System.out.println("Sinistro não encontrado");
			}
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		// Operacoes iniciais
		Seguradora seguradora = new Seguradora("Melhor Guincho","(19)1234-5478","abc@email.com","Rua A");
		Veiculo veiculo1 = new Veiculo("ABC1234", "Marca A", "Modelo A", 2011);	
		Veiculo veiculo2 = new Veiculo("EFG5678", "Marca B", "Modelo B", 2012);
		ClientePF cliente_fisico = new ClientePF("Matheus Enzo", "Rua 2", "17329230006", "Masculino", new Date(), "Superior Completo", sdf.parse("05/10/1989"), "Media");
		ClientePJ cliente_juridico = new ClientePJ("Empresa Campinas", "Barao Geraldo", "22.945.190/0001-52", sdf.parse("21/05/2016"), 115);
		seguradora.cadastrarCliente(cliente_fisico);
		seguradora.cadastrarCliente(cliente_juridico);
		seguradora.cadastrarVeiculo(cliente_fisico, veiculo1);
		seguradora.cadastrarVeiculo(cliente_juridico, veiculo2);
		cliente_fisico.setValorSeguro(seguradora.calcularPrecoSeguroCliente(cliente_fisico));
		cliente_juridico.setValorSeguro(seguradora.calcularPrecoSeguroCliente(cliente_juridico));
		
		System.out.println("*****************Antes de gerar sinistros Sinistros*****************");
		System.out.println("Sinistros da seguradora: \n" + seguradora.listarSinistros());
		System.out.println("Receita da seguradora: "+ seguradora.calcularReceita());
		seguradora.gerarSinistro("10/02/2023", "IFGW", "ABC1234", "17329230006");
		seguradora.gerarSinistro("26/04/2023", "IMECC", "ABC1234", "17329230006");
		System.out.println("*****************Apos gerar sinistros Sinistros*****************");
		System.out.println("Sinistros da seguradora: \n" + seguradora.listarSinistros());
		System.out.println("Receita da seguradora: " + seguradora.calcularReceita() + "\n");
		
		List<Cliente> pf = seguradora.listarClientes("1");
		for(int i=0; i<pf.size();i++)
		{
			System.out.println(pf.get(i) + "\n");
		}
		List<Cliente> pj = seguradora.listarClientes("2");
		for(int i=0; i<pj.size();i++)
		{
			System.out.println(pj.get(i) + "\n");
		}
		if(seguradora.visualizarSinistro("17329230006"))
		{
			System.out.println("Existem sinistros relacionados ao cliente");
		}
		else
		{
			System.out.println("Nao existem sinistros relacionados ao cliente");
		}
		if(seguradora.visualizarSinistro("22.945.190/0001-52"))
		{
			System.out.println("Existem sinistros relacionados ao cliente");
		}
		else
		{
			System.out.println("Nao existem sinistros relacionados ao cliente");
		}
		
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
