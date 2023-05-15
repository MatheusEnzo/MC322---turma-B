public class Validacao
{
	// Metodo para validar CPF, retornando falso se for invalido e true se for valido
	public static boolean validarCPF(String cpf)
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
	
	// Metodo para validar CNPJ, retornando false se for invalido e true se for valido
		public static boolean validar(String cnpj)
		{
		    // Verifica se a string possui 14 caracteres
		    if (cnpj.length() != 14)
		    {
		    	return false;
		    }

		    // Verifica se todos os caracteres são iguais
		    if (cnpj.matches("(\\d)\\1{13}"))
		    {
		        return false;
		    }

		    // Calcula o primeiro dígito verificador
		    int soma = 0;
		    int peso = 2;
		    for (int i = 11; i >= 0; i--)
		    {
		        int digito = Integer.parseInt(cnpj.substring(i, i + 1));
		        soma += digito * peso;
		        peso = (peso == 9) ? 2 : peso + 1;
		    }
		    int dv1 = 11 - (soma % 11);
		    if (dv1 > 9)
		    {
		        dv1 = 0;
		    }

		    // Calcula o segundo dígito verificador
		    soma = 0;
		    peso = 2;
		    for (int i = 12; i >= 0; i--)
		    {
		        int digito = Integer.parseInt(cnpj.substring(i, i + 1));
		        soma += digito * peso;
		        peso = (peso == 9) ? 2 : peso + 1;
		    }
		    int dv2 = 11 - (soma % 11);
		    if (dv2 > 9)
		    {
		        dv2 = 0;
		    }

		    // Verifica se os dígitos verificadores estão corretos
		    return cnpj.substring(12).equals("" + dv1 + dv2);
		}
}
