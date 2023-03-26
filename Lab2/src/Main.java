public class Main
{
	public static void main(String[] args)
	{
		Veiculo veiculo1 = new Veiculo("ABC1234", "Nissan", "March");
		System.out.println(veiculo1 + "\n");
		
		Seguradora seguradora1 = new Seguradora("Seguro1", "(19) 54687355", "seguro@email.com", "Rua A, 1235");
		System.out.println(seguradora1 + "\n");
		
		Sinistro sinistro1 = new Sinistro("22/03/23", "Rua B, 12");
		System.out.println(sinistro1 + "\n");
		
		Sinistro sinistro2 = new Sinistro("23/03/23", "Rua C, 23");
		System.out.println(sinistro2 + "\n");
		
		Sinistro sinistro3 = new Sinistro("24/03/23", "Rua D, 34");
		System.out.println(sinistro3 + "\n");
		
		Cliente cliente1 =  new Cliente("Cliente 1", "36742225157", "23/04/1999", 24, "Rua AB, 41");
		if(cliente1.validarCPF(cliente1.getCpf()))
		{
			System.out.println(cliente1 + "\n");
		}
		else
		{
			System.out.println("CPF invalido" + "\n");
		}
		
		Cliente cliente2 =  new Cliente("Cliente Dois", "367.422.251-57", "23/04/99", 22, "Rua BC, 42");
		if(cliente2.validarCPF(cliente2.getCpf()))
		{
			System.out.println(cliente2 + "\n");
		}
		else
		{
			System.out.println("CPF invalido" + "\n");
		}
		
		Cliente cliente3 =  new Cliente("Cliente3 ", "44444444444", "23/04/2000", 23, "Rua CD, 43");
		if(cliente3.validarCPF(cliente3.getCpf()))
		{
			System.out.println(cliente3 + "\n");
		}
		else
		{
			System.out.println("CPF invalido" + "\n");
		}
		
		Cliente cliente4 =  new Cliente("Cliente4 ", "44444", "23/04/00", 24, "Rua DE, 44");
		if(cliente4.validarCPF(cliente4.getCpf()))
		{
			System.out.println(cliente4 + "\n");
		}
		else
		{
			System.out.println("CPF invalido" + "\n");
		}
		
		Cliente cliente5 =  new Cliente("Cliente Cin Co", "12345678910", "23/04/00", 24, "Rua DE, 44");
		if(cliente5.validarCPF(cliente5.getCpf()))
		{
			System.out.println(cliente5 + "\n");
		}
		else
		{
			System.out.println("CPF invalido" + "\n");
		}
		
		Cliente cliente6 =  new Cliente("Cliente Seis", "123.456.789-10", "23/04/00", 24, "Rua DE, 44");
		if(cliente6.validarCPF(cliente6.getCpf()))
		{
			System.out.println(cliente6 + "\n");
		}
		else
		{
			System.out.println("CPF invalido" + "\n");
		}
		
		Cliente cliente7 =  new Cliente("Cliente S e t e", "123.456.78a-90", "23/04/00", 24, "Rua DE, 44");
		if(cliente6.validarCPF(cliente7.getCpf()))
		{
			System.out.println(cliente7 + "\n");
		}
		else
		{
			System.out.println("CPF invalido" + "\n");
		}
	}
}
