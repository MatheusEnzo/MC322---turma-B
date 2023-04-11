import java.util.ArrayList;
import java.util.Date;

public class ClientePJ extends Cliente
{
	private final String cnpj;
	private Date dataFundacao;
	
	public ClientePJ (String nome, String endereco, Date dataLicensa,
	String educacao, String genero, String classeEconomica,
	ArrayList <Veiculo> listaVeiculos, String cnpj, Date dataFundacao)
	{
		super (nome, endereco, dataLicensa, educacao, genero, classeEconomica, listaVeiculos);
		
		this.dataFundacao = dataFundacao;
		this.cnpj = cnpj.replaceAll("[^0-9]", "");
	}

	public String getCnpj()
	{
		return cnpj;
	}

	public Date getDataFundacao()
	{
		return dataFundacao;
	}
	public void setDataFundacao(Date dataFundacao)
	{
		this.dataFundacao = dataFundacao;
	}
	
	public boolean validar(String cnpj)
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
	
	@Override
	public String toString()
	{
		return super.toString() + "\nCNPJ: " + cnpj + "\nData de Fundação: " + dataFundacao;	
	}
}
