/*
 * Define as constantes dos submenus
 */
public enum SubmenuOpcoes {
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO("Cadastrar veiculo (pessoa fisica)"),
	CADASTRAR_FROTA("Cadastrar frota"),
	LISTAR_CLIENTES("Listar clientes"),
	LISTAR_SEGUROS_CLIENTES("Listar seguros por cliente"),
	LISTAR_SINISTROS_CLIENTES("Listar sinistros por cliente"),
	LISTAR_VEICULOS_FROTA("Listar veiculos por frota"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_VEICULO("Excluir veiculo (pessoa fisica)"),
	ADICIONAR_VEICULO("Adcionar veiculo"),
	REMOVER_VEICULO("Excluir veiculo"),
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
