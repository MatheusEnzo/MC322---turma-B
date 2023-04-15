import java.util.List;
import java.util.Date;

public class ClientePF extends Cliente
{
	// Atributos especificos para cliente fisico
	private final String cpf;
	private Date dataNascimento;

	public ClientePF (String nome, String endereco, Date dataLicenca,
	String educacao, String genero, String classeEconomica,
	List <Veiculo> listaVeiculos, String cpf, Date dataNascimento)
	{
		// Chama o construtor da superclasse
		super (nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos);
		
		this.dataNascimento = dataNascimento;
		this.cpf = cpf.replaceAll("[^0-9]", "");
	}

	// Getters e setters
	public String getCpf()
	{
		return cpf;
	}

	public Date getDataNascimento()
	{
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento)
	{
		this.dataNascimento = dataNascimento;
	}
	
	// Metodo para validar CPF, retornando falso se for invalido e true se for valido
	public boolean validarCPF(String cpf)
	{
		// Verifica se o cpf possui 11 digitos
		if(cpf.length() != 11)
		{
			return false;
		}
		
		// Verifica se todos os digitos sao iguais 
		if (cpf.matches("(\\d)\\1{10}"))
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
	

	@Override
	// Devolve a string com todos atributos no formato para impressao
	public String toString()
	{
		return super.toString() + "\nCPF: " + cpf + "\nData de Nascimento: " + dataNascimento;
		
	}
}