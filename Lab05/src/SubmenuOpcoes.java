/*
 * Define as constantes dos submenus
 */
public enum SubmenuOpcoes {
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_FROTA("Cadastrar frota"),
	LISTAR_CLIENTES("Listar clientes"),
	LISTAR_SINISTROS("Listar sinistros"),
	LISTAR_VEICULOS("Listar veiculos"),
	LISTAR_FROTAS("Listar frotas"),
	LISTAR_SEGUROS_CLIENTES("Listar seguros por cliente"),
	LISTAR_SINISTROS_CLIENTES("Listar sinistros por cliente"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_VEICULO("Excluir veiculo"),
	EXCLUIR_FROTA("Excluir frota"),
	VOLTAR("Voltar");
	
	//atributo
	private final String descricao;
	
	//Construtor
	SubmenuOpcoes(String descricao){
		this.descricao = descricao;
	}
	
	//getter
	public String getDescricao() {
		return descricao;
	}
}
