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
			case ATUALIZAR:
				executarSubmenu(op);
				break;
			case GERAR_SINISTRO:
				System.out.print("Digite o CPF do condutor: ");
				cpf = entrada.nextLine();
				cpf = cpf.replaceAll("[^0-9]", "");
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
							System.out.println("Sinistro gerado.\n");
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
					System.out.println("Nenhum condutor com tal CPF.\n");
				}

				break;
			case GERAR_SEGURO:
				System.out.print("Digite o CPF/CNPJ do cliente: ");
				identificacao = entrada.nextLine();
				identificacao = identificacao.replaceAll("[^0-9]", "");
				
				flag=false;
				if(identificacao.length() == 11)
				{
					System.out.print("Digite a placa do veículo: ");
					String placa = entrada.nextLine();
					for(Seguradora i : seguradoras)
					{
						if(i.gerarSeguro(placa, identificacao))
						{
							System.out.println("Seguro gerado.\n");
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
							System.out.println("Seguro gerado.\n");
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
					System.out.println("Não foi possível gerar o seguro.\n");
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
							System.out.println("Seguro cancelado.\n");
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
							System.out.println("Seguro cancelado.\n");
							flag=true;
							break;
						}
					}
				}
				if(flag==false)
				{
					System.out.println("Não foi possível cancelar o seguro.\n");
				}
				
				break;
			case AUTORIZAR_CONDUTOR:
				System.out.print("Digite o CPF do condutor: ");
				cpf = entrada.nextLine();
				cpf = cpf.replaceAll("[^0-9]", "");
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
				
				if(Validacao.validarCPF(cpf) && Validacao.validarNome(nome) && Validacao.validarIdade(dataNascimento))
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
		boolean flag;
		int code;
		String cpf, cnpj;
		
		switch(opSubmenu) {
		case CADASTRAR_CLIENTE:
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("Tipos de clientes:\n1 - Pessoa Física\n2 - Pessoa Jurídica");
			System.out.print("Número da opção desejada: ");
			String opcao = entrada.nextLine();
			
			System.out.println("Lista de seguradoras:");
			for(int i=0; i<seguradoras.size(); i++)
			{
				System.out.println(i + " - " + seguradoras.get(i).getNome());
			}
			System.out.print("Selecione em qual seguradora deseja cadastrar: ");
			int opcaoSeg = entrada.nextInt();
			entrada.nextLine();
			
			if(opcao.equals("1"))
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
				cpf = entrada.nextLine();
				cpf = cpf.replaceAll("[^0-9]", "");
				if(!Validacao.validarCPF(cpf))
				{
					System.out.println("CPF inválido");
					break;
				}
				System.out.print("Telefone: ");
				String telefone = entrada.nextLine();
				System.out.print("Endereço: ");
				String endereco = entrada.nextLine();
				System.out.print("Email: ");
				String email = entrada.nextLine();
				System.out.print("Gênero: ");
				String genero = entrada.nextLine();
				System.out.print("Educação: ");
				String educacao = entrada.nextLine();
				System.out.print("Data de nascimento (dd/mm/aaaa): ");
				Date dataNascimento = sdf.parse(entrada.nextLine());
				
				ClientePF cliente = new ClientePF(nome, telefone, endereco, email, cpf, genero, educacao, dataNascimento);
				if(seguradoras.get(opcaoSeg).cadastrarCliente(cliente))
				{
					System.out.println("Cliente cadastrado.\n");
				}
				else
				{
					System.out.println("Nao foi possivel cadastrar o cliente.\n");
				}
			}
			else if(opcao.equals("2"))
			{
				System.out.print("CNPJ: ");
				cnpj = entrada.nextLine();
				cnpj = cnpj.replaceAll("[^0-9]", "");
				if(!Validacao.validarCNPJ(cnpj))
				{
					System.out.println("CNPJ inválido");
					break;
				}
				
				System.out.print("Data de fundação (dd/mm/aaaa): ");
				String dataF = entrada.nextLine();
				Date dataFundacao = sdf.parse(dataF);
				if(!Validacao.validarFundacao(dataFundacao))
				{
					System.out.println("Data de fundação inválida");
					break;
				}
				System.out.print("Nome: ");
				String nome = entrada.nextLine();
				System.out.print("Telefone: ");
				String telefone = entrada.nextLine();
				System.out.print("Endereço: ");
				String endereco = entrada.nextLine();
				System.out.print("Email: ");
				String email = entrada.nextLine();
				System.out.print("Quantidade de funcionários: ");
				int qtde = entrada.nextInt();
				entrada.nextLine();
				
				ClientePJ cliente = new ClientePJ(nome, telefone, endereco, email, cnpj, dataFundacao, qtde);
				if(seguradoras.get(opcaoSeg).cadastrarCliente(cliente))
				{
					System.out.println("Cliente cadastrado.\n");
				}
				else
				{
					System.out.println("Nao foi possivel cadastrar o cliente.\n");
				}
				
			}

			break;
		case CADASTRAR_VEICULO:
			System.out.print("Digite o CPF do cliente desejado: ");
			cpf = entrada.nextLine();
			cpf = cpf.replaceAll("[^0-9]", "");
			
			loop:
			for(Seguradora i: seguradoras)
			{
				for(int j=0; j<i.getListaClientes().size(); j++)
				{
					if(i.getListaClientes().get(j).getIdentificacao().equals(cpf))
					{
						System.out.print("Digite a placa do veiculo: ");
						String placa = entrada.nextLine();
						System.out.print("Digite a marca do veiculo: ");
						String marca = entrada.nextLine();
						System.out.print("Digite o modelo do veiculo: ");
						String modelo = entrada.nextLine();
						System.out.print("Digite o ano de fabricação do veiculo: ");
						int ano = entrada.nextInt();
						entrada.nextLine();
						
						if(((ClientePF) i.getListaClientes().get(j)).cadastrarVeiculo(placa, marca, modelo, ano))
						{
							System.out.print("Veiculo cadastrado.\n");
							for(int k=0; k<i.getListaSeguros().size(); k++)
							{
								if(i.getListaSeguros().get(k).getCliente().equals(i.getListaClientes().get(j)))
								{
									i.getListaSeguros().get(k).setValorMensal(i.getListaSeguros().get(k).calcularValor());
								}
							}
							break loop;
						}
						else
						{
							System.out.print("Nao foi possivel cadastrar o veiculo.\n");
						}
					}
				}
			}
			break;

		case CADASTRAR_FROTA:
			System.out.print("Digite o CNPJ do cliente desejado: ");
			cnpj = entrada.nextLine();
			cnpj = cnpj.replaceAll("[^0-9]", "");
			Frota frota = new Frota();
			flag=false;
			for(Seguradora i : seguradoras)
			{
				for(int j=0; j<i.getListaClientes().size(); j++)
				{
					if(i.getListaClientes().get(j).getIdentificacao().equals(cnpj))
					{
						((ClientePJ) i.getListaClientes().get(j)).cadastrarFrota(frota);
						System.out.println("Frota cadastrada, código da frota: " + frota.getCode());
						System.out.println("Para adicionar veiculos a frota, utilize a operação de atualizar frota\n");
						flag=true;
						break;
					}
				}
				if(flag==true)
				{
					break;
				}
			}
			
			
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
			identificacao = identificacao.replaceAll("[^0-9]", "");
			
			for(Seguradora i : seguradoras)
			{
				ArrayList<Seguro> seguros = i.getSegurosPorCliente(identificacao);
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
			identificacao1 = identificacao1.replaceAll("[^0-9]", "");
			
			for(Seguradora i : seguradoras)
			{
				ArrayList<Sinistro> sinistros = i.getSinistrosPorCliente(identificacao1);
				if(sinistros.size()==0)
				{
					System.out.println("Não existem sinistros associados ao cliente.\n");
				}
				else
				{
					for(Sinistro j : sinistros)
					{
						System.out.println(j);
					}
					System.out.print("\n");
				}
			}
			break;
			
		case LISTAR_VEICULOS_FROTA:
			System.out.print("Digite o codigo da frota: ");
			code = entrada.nextInt();
			entrada.nextLine();
			
			loop:
			for(Seguradora i : seguradoras)
			{
				for(Cliente j : i.getListaClientes())
				{
					if(j.getClass() == ClientePJ.class)
					{
						for(Frota k: ((ClientePJ) j).getListaFrota())
						{
							if(k.getCode() == code)
							{
								System.out.println(k);
								break loop;
							}
						}
					}
				}
			}
			break;
			
		case EXCLUIR_CLIENTE:
			System.out.print("Digite o CPF ou CNPJ do cliente que deseja remover: ");
			identificacao = entrada.nextLine();
			identificacao = identificacao.replaceAll("[^0-9]", "");
			
			System.out.println("Lista de seguradoras:");
			for(int i=0; i<seguradoras.size(); i++)
			{
				System.out.println(i + " - " + seguradoras.get(i).getNome());
			}
			System.out.print("Selecione em qual seguradora deseja cadastrar: ");
			int opcaoSeg1 = entrada.nextInt();
			entrada.nextLine();

			if(seguradoras.get(opcaoSeg1).removerCliente(identificacao))
			{
				System.out.println("Cliente removido.\n");
			}
			else
			{
				System.out.println("Cliente não encontrado.\n");
			}
			break;
			
		case EXCLUIR_VEICULO:
			System.out.print("Digite o CPF do cliente desejado: ");
			cpf = entrada.nextLine();
			cpf = cpf.replaceAll("[^0-9]", "");
			System.out.print("Digite a placa do veiculo: ");
			String placa = entrada.nextLine();
			
			loop:
			for(int i=0; i<seguradoras.size(); i++)
			{
				for(int j=0; j<seguradoras.get(i).getListaClientes().size(); j++)
				{
					if(seguradoras.get(i).getListaClientes().get(j).getIdentificacao().equals(cpf))
					{
						if(((ClientePF) seguradoras.get(i).getListaClientes().get(j)).removerVeiculo(placa))
						{
							System.out.print("Veiculo removido.\n");
							for(int k=0; k<seguradoras.get(i).getListaSeguros().size(); k++)
							{
								if(seguradoras.get(i).getListaSeguros().get(k).getCliente().equals(seguradoras.get(i).getListaClientes().get(j)))
								{
									if(((SeguroPF) seguradoras.get(i).getListaSeguros().get(k)).getVeiculo().getPlaca().equals(placa))
									{
										seguradoras.get(i).getListaSeguros().remove(k);
									}
									else
									{
										seguradoras.get(i).getListaSeguros().get(k).setValorMensal(seguradoras.get(i).getListaSeguros().get(k).calcularValor());
									}
								}
							}
							break loop;
						}
						else
						{
							System.out.print("Nao foi possivel remover o veiculo.\n");
						}
						
					}
				}
			}
			break;
			
		case ADICIONAR_VEICULO:
			System.out.print("Digite o CNPJ do cliente desejado: ");
			cnpj = entrada.nextLine();
			cnpj = cnpj.replaceAll("[^0-9]", "");
			System.out.print("Digite o codigo da frota: ");
			code = entrada.nextInt();
			entrada.nextLine();
			
			loop:
			for(int i=0; i<seguradoras.size(); i++)
			{
				for(int j=0; j<seguradoras.get(i).getListaClientes().size(); j++)
				{
					if(seguradoras.get(i).getListaClientes().get(j).getIdentificacao().equals(cnpj))
					{
						System.out.print("Digite a placa do veiculo: ");
						String placa1 = entrada.nextLine();
						System.out.print("Digite a marca do veiculo: ");
						String marca = entrada.nextLine();
						System.out.print("Digite o modelo do veiculo: ");
						String modelo = entrada.nextLine();
						System.out.print("Digite o ano de fabricação do veiculo: ");
						int ano = entrada.nextInt();
						entrada.nextLine();
						if(Validacao.verificarPlaca(placa1))
						{
							Veiculo veiculo = new Veiculo(placa1, marca, modelo, ano);
							if(((ClientePJ) seguradoras.get(i).getListaClientes().get(j)).atualizarFrota(code, 0, veiculo))
							{
								System.out.println("Novo veiculo cadastrado.\n");
								for(int k=0; k<seguradoras.get(i).getListaSeguros().size(); k++)
								{
									if(seguradoras.get(i).getListaSeguros().get(k).getCliente().equals(seguradoras.get(i).getListaClientes().get(j)))
									{
										if(((SeguroPJ) seguradoras.get(i).getListaSeguros().get(k)).getFrota().getCode() == code)
										{
											seguradoras.get(i).getListaSeguros().get(k).setValorMensal(seguradoras.get(i).getListaSeguros().get(k).calcularValor());
											break loop;
										}
									}
								}
							}
						}
						else
						{
							System.out.println("Placa invalida.\n");
						}
					}
				}
			}
			break;
			
		case REMOVER_VEICULO:
			System.out.print("Digite o CNPJ do cliente desejado: ");
			cnpj = entrada.nextLine();
			cnpj = cnpj.replaceAll("[^0-9]", "");
			System.out.print("Digite o codigo da frota: ");
			code = entrada.nextInt();
			entrada.nextLine();
			
			loop:
			for(int i=0; i<seguradoras.size(); i++)
			{
				for(int j=0; j<seguradoras.get(i).getListaClientes().size(); j++)
				{
					if(seguradoras.get(i).getListaClientes().get(j).getIdentificacao().equals(cnpj))
					{
						System.out.print("Digite a placa do veiculo: ");
						String placa1 = entrada.nextLine();
						System.out.print("Digite a marca do veiculo: ");
						String marca = entrada.nextLine();
						System.out.print("Digite o modelo do veiculo: ");
						String modelo = entrada.nextLine();
						System.out.print("Digite o ano de fabricação do veiculo: ");
						int ano = entrada.nextInt();
						entrada.nextLine();
						
						Veiculo veiculo = new Veiculo(placa1, marca, modelo, ano);
						if(((ClientePJ) seguradoras.get(i).getListaClientes().get(j)).atualizarFrota(code, 1, veiculo))
						{
							System.out.println("Veiculo removido.\n");
							for(int k=0; k<seguradoras.get(i).getListaSeguros().size(); k++)
							{
								if(seguradoras.get(i).getListaSeguros().get(k).getCliente().equals(seguradoras.get(i).getListaClientes().get(j)))
								{
									if(((SeguroPJ) seguradoras.get(i).getListaSeguros().get(k)).getFrota().getCode() == code)
									{
										seguradoras.get(i).getListaSeguros().get(k).setValorMensal(seguradoras.get(i).getListaSeguros().get(k).calcularValor());
										break loop;
									}
								}
							}
						}
					}
				}
			}
			break;
			
		case EXCLUIR_FROTA:
			System.out.print("Digite o CNPJ do cliente desejado: ");
			cnpj = entrada.nextLine();
			cnpj = cnpj.replaceAll("[^0-9]", "");
			System.out.print("Digite o codigo da frota: ");
			code = entrada.nextInt();
			entrada.nextLine();
			
			loop:
			for(int i=0; i<seguradoras.size(); i++)
			{
				for(int j=0; j<seguradoras.get(i).getListaClientes().size(); j++)
				{
					if(seguradoras.get(i).getListaClientes().get(j).getIdentificacao().equals(cnpj))
					{
						if(((ClientePJ) seguradoras.get(i).getListaClientes().get(j)).atualizarFrota(code, 3, null))
						{
							System.out.println("Frota removido.\n");
							for(int k=0; k<seguradoras.get(i).getListaSeguros().size(); k++)
							{
								if(seguradoras.get(i).getListaSeguros().get(k).getCliente().equals(seguradoras.get(i).getListaClientes().get(j)))
								{
									if(((SeguroPJ) seguradoras.get(i).getListaSeguros().get(k)).getFrota().getCode() == code)
									{
										seguradoras.get(i).getListaSeguros().remove(k);
										break loop;
									}
								}
							}
						}
					}
				}
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
		
		// Testes manuais
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Seguradora seguradora = new Seguradora("04.596.800/0001-78", "Seguradora Teste", "(19)1234-5678", "Rua", "abc@email.com");
		Veiculo veiculo4 = new Veiculo("DDD4444", "Marca 4", "Modelo 4", 2014);
		Veiculo veiculo5 = new Veiculo("EEE5555", "Marca 5", "Modelo 5", 2015);
		Veiculo veiculo6 = new Veiculo("FFF6666", "Marca 6", "Modelo 6", 2016);
		Veiculo veiculo7 = new Veiculo("GGG7777", "Marca 7", "Modelo 7", 2017);
		Veiculo veiculo8 = new Veiculo("HHH8888", "Marca 8", "Modelo 8", 2018);
		Veiculo veiculo9 = new Veiculo("III9999", "Marca 9", "Modelo 9", 2019);
		ClientePF fisico1 = new ClientePF("Cliente fisico1 teste", "(11)11111111", "Endereco fisico1","email@fisico1.com", "17329230006", "Masculino", "Superior", sdf.parse("05/10/1996"));
		ClientePF fisico2 = new ClientePF("Cliente fisico2 teste", "(33)3333-3333", "Endereco fisico2","email@fisico2.com", "222.479.870-95", "Masculino", "Superior", sdf.parse("05/10/1988"));
		ClientePJ juridico1 = new ClientePJ("Empresa1 teste", "(22)22222222", "Endereco juridico1", "email@juridico1.com", "22.945.190/0001-52", sdf.parse("21/05/2016"), 50);
		ClientePJ juridico2 = new ClientePJ("Empresa1 teste2", "(44)4444-4444", "Endereco juridico2", "email@juridico2.com", "30161849000108", sdf.parse("18/06/1972"), 300);
		Frota frota1= new Frota();
		Frota frota2= new Frota();
		
		seguradora.cadastrarCliente(fisico1);
		seguradora.cadastrarCliente(fisico2);
		seguradora.cadastrarCliente(juridico1);
		seguradora.cadastrarCliente(juridico2);
		fisico1.cadastrarVeiculo("AAA1111", "Marca 1", "Modelo 1", 2011);
		fisico2.cadastrarVeiculo("BBB2222", "Marca 2", "Modelo 2", 2012);
		fisico2.cadastrarVeiculo("CCC3333", "Marca 3", "Modelo 3", 2013);
		juridico1.cadastrarFrota(frota1);
		juridico2.cadastrarFrota(frota2);
		juridico1.atualizarFrota(frota1.getCode(), 0, veiculo4);
		juridico1.atualizarFrota(frota1.getCode(), 0, veiculo5);
		juridico2.atualizarFrota(frota2.getCode(), 0, veiculo6);
		juridico2.atualizarFrota(frota2.getCode(), 0, veiculo7);
		juridico2.atualizarFrota(frota2.getCode(), 0, veiculo8);
		juridico2.atualizarFrota(frota2.getCode(), 0, veiculo9);
		
		seguradora.gerarSeguro("AAA1111", "17329230006");
		seguradora.gerarSeguro("BBB2222", "222.479.870-95");
		seguradora.gerarSeguro("CCC3333", "222.479.870-95");
		seguradora.gerarSeguro(frota1.getCode(), "22.945.190/0001-52");
		seguradora.gerarSeguro(frota2.getCode(), "30161849000108");
		
		
		Condutor condutor1 = new Condutor("17329230006", "Cliente fisico1 teste", "(11)11111111", "Endereco fisico1", "email@fisico1.com", sdf.parse("05/10/1996"));
		Condutor condutor2 = new Condutor("222.479.870-95", "Cliente fisico2 teste", "(33)3333-3333", "Endereco fisico2", "email@fisico2.com", sdf.parse("05/10/1988"));
		seguradora.getListaSeguros().get(0).autorizarCondutor(condutor1);
		seguradora.getListaSeguros().get(3).autorizarCondutor(condutor2);
		seguradora.getListaSeguros().get(4).autorizarCondutor(condutor2);
		
		seguradora.getListaSeguros().get(0).gerarSinistro(new Date(), "Rua Sinistro 1", "17329230006");
		seguradora.getListaSeguros().get(3).gerarSinistro(new Date(), "Rua Sinistro 4", "222.479.870-95");
		seguradora.getListaSeguros().get(3).gerarSinistro(new Date(), "Rua Sinistro 5", "222.479.870-95");
		seguradora.getListaSeguros().get(3).gerarSinistro(new Date(), "Rua Sinistro 6", "222.479.870-95");
		seguradora.getListaSeguros().get(4).gerarSinistro(new Date(), "Rua Sinistro 2", "222.479.870-95");
		seguradora.getListaSeguros().get(4).gerarSinistro(new Date(), "Rua Sinistro 3", "222.479.870-95");
		
		
		
		System.out.println("************************CLIENTES FISICOS************************");
		ArrayList<Cliente> clientes_fisicos = seguradora.listarClientes("1");
		for(Cliente i: clientes_fisicos)
		{
			System.out.println(i + "\n");
		}
		System.out.println("************************CLIENTES JURIDICOS************************");
		ArrayList<Cliente> clientes_juridicos = seguradora.listarClientes("2");
		for(Cliente i: clientes_juridicos)
		{
			System.out.println(i + "\n");
		}
		
		System.out.println("************************ANTES DE CANCELAR SEGURO************************");
		for(Seguro i : seguradora.getListaSeguros())
		{
			System.out.println(i + "\n");
		}
		System.out.println(seguradora.calcularReceita() + "\n");
		
		System.out.println("************************APOS CANCELAR SEGURO************************");
		seguradora.cancelarSeguro("AAA1111");
		for(Seguro i : seguradora.getListaSeguros())
		{
			System.out.println(i + "\n");
		}
		System.out.println(seguradora.calcularReceita() + "\n");
		
		System.out.println("************************LISTA DE CLIENTES APOS REMOVER UM CLIENTE JURIDICO************************");
		seguradora.removerCliente("30161849000108");
		ArrayList<Cliente> clientes_juridicos_remocao = seguradora.listarClientes("2");
		for(Cliente i: clientes_juridicos_remocao)
		{
			System.out.println(i + "\n");
		}
		System.out.println("************************LISTA DE SEGUROS APOS REMOVER UM CLIENTE JURIDICO************************");
		for(Seguro i : seguradora.getListaSeguros())
		{
			System.out.println(i + "\n");
		}
		System.out.println(seguradora.calcularReceita() + "\n");
		
		System.out.println("************************LISTA DE SEGUROS POR CLIENTE************************");
		ArrayList<Seguro> SeguroPorCliente = seguradora.getSegurosPorCliente("222.479.870-95");
		for(Seguro i : SeguroPorCliente)
		{
			System.out.println(i + "\n");
		}
		
		System.out.println("************************LISTA DE SINISTROS POR CLIENTE************************");
		ArrayList<Sinistro> SinistroPorCliente = seguradora.getSinistrosPorCliente("22.945.190/0001-52");
		for(Sinistro i : SinistroPorCliente)
		{
			System.out.println(i + "\n");
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
