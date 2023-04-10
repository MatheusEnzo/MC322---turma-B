public class ClientePF extends Cliente
{
	private String cpf ;
	private Date dataNascimento ;

	public ClientePF ( String nome , String endereco , Date dataLicenca ,
	String educacao , String genero , String classeEconomica ,
	List < Veiculo > listaVeiculos , String cpf , Date dataNascimento )
	{
		// chama o construtor da superclasse
		super ( nome , endereco , dataLicenca , educacao , genero , classeEconomica , listaVeiculos );
		this . cpf = cpf ;
		this . dataNascimento = dataNascimento ;
	}

	// TO DO:
	// metodos getters e setters para cpf e dataNascimento
	// ...
	
	@Override
	public String toString ()
	{
		// ...
	}
	public boolean validarCPF ( String cpf )
	{
		// ...
	}
}