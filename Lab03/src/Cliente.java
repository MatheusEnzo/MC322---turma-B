public class Cliente
{
	private String nome;
	private String endereco;
	// private String cpf;
	private Date dataLicensa;
	private String educacao;
	private String genero;
	private String classeEconomica;
	private List<Veiculo> listaVeiculos;
	
	// Constructor
	public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco)
	{
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.endereco = endereco;
		this.cpf = cpf.replaceAll("[^0-9]", "");
	}
	
	// Metodo para validar CPF, retornando falso se for invalido
	public boolean validarCPF(String cpf)
	{
		// Verifica se o cpf possui 11 digitos
		if(cpf.length() != 11)
		{
			return false;
		}
		
		// Verifica se todos os digitos sao iguais 
		char c = cpf.charAt(0);
		boolean diferente = false;
	    for(int i=1; i<11; i++)
	    {
	        if(cpf.charAt(i)!=c)
	        {
	            diferente = true;
	            break;
	        }
	    }
		if(!diferente)
		{
			return false;
		}
		
		// Calcula o primeiro dígito verificador
	    int soma = 0;
	    for(int i=0; i<9; i++)
	    {
	        int num = cpf.charAt(i) - '0';
	        soma += num * (10 - i);
	    }
	    int digito1 = 11 - (soma % 11);
	    if(digito1 > 9)
	    {
	        digito1 = 0;
	    }
	    
	    // Calcula o segundo dígito verificador
	    soma = 0;
	    for (int i=0; i<10; i++)
	    {
	        int num = cpf.charAt(i) - '0';
	        soma += num * (11 - i);
	    }
	    int digito2 = 11 - (soma % 11);
	    if(digito2>9)
	    {
	        digito2 = 0;
	    }
	    
	    // Verifica se os dígitos verificadores são iguais aos do CPF
	    return cpf.charAt(9) - '0' == digito1 && cpf.charAt(10) - '0' == digito2;
	}

	// Getters e setters
	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getCpf()
	{
		return cpf;
	}

	public void setCpf(String cpf)
	{
		this.cpf = cpf;
	}

	public String getDataNascimento()
	{
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento)
	{
		this.dataNascimento = dataNascimento;
	}

	public int getIdade()
	{
		return idade;
	}

	public void setIdade(int idade)
	{
		this.idade = idade;
	}

	public String getEndereco()
	{
		return endereco;
	}

	public void setEndereco(String endereco)
	{
		this.endereco = endereco;
	}
	
	// Devolve a string no formato para impressao
	public String toString()
	{
		return "Nome: " + nome + "\nCPF: " + cpf + "\nData de Nascimento: " + dataNascimento + "\nIdade: " + idade + "\nEndereco: " + endereco;
	}
}