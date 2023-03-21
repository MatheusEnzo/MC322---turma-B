public class Main
{
	public static void main(String[] args)
	{
		Veiculo veiculo1 = new Veiculo("ABC1234", "Nissan", "March");
		System.out.println(veiculo1 + "\n");
		
		Seguradora seguradora1 = new Seguradora("Seguro1", "(19) 54687355", "seguro@email.com", "Rua A, 1235");
		System.out.println(seguradora1);
	}
}
