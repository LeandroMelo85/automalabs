package br.com.automalabs.estacio.projetoAV1;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args){

		
		Cliente clientes[] = new Cliente[Funcoes.MAX_CLIENTES] ; //aloco a memória necessária

		for (int i=0; i<Funcoes.MAX_CLIENTES; i++) {
			clientes[i] = new Cliente(); //instancio os objetos
			
		}
		
		Funcoes.carregarClientes(clientes);	
		
		//TODO: Para não desperdiçar memória alocando uma quantidade extra de elementos
		//(MAX_VEICULOS = 30 neste momento)
		//para acomodar o cadastro de novos veículos eu deveria usar alocação dinâmica de memória
		//mas aparentemente o único modo de fazer isso em Java é usar arraylists, que apesar de 
		//provavelmente ser mais fácil que usar ponteiros não foi o que o professor pediu.
		Carro carros[] = new Carro[Funcoes.MAX_VEICULOS] ; //aloco a memória necessária

		for (int i=0; i<Funcoes.MAX_VEICULOS; i++) {
			carros[i] = new Carro(); //instancio os objetos
			
		}
		
		Funcoes.carregarVeiculos(carros);	
		
		//DEBUG
		//System.out.println(new File(".").getAbsoluteFile());
		//Funcoes.salvarClientes(Funcoes.ARQ_CLIENTES, clientes);
		String opcao="";
		Scanner scan = new Scanner(System.in);
		do{
			System.out.println("Digite a opcao desejada seguida de ENTER:\n");
			System.out.println("1 - Listar frota");
			System.out.println("2 - Listar clientes com os respectivos carros alugados");
			System.out.println("3 - Adicionar cliente");
			System.out.println("4 - Alugar veículo");	
			System.out.println("5 - Listar veículos alugados");	
			System.out.println("6 - Encerrar aluguel");				
			System.out.println("0 - Sair do programa");

		opcao = scan.nextLine();
		while ("0123456".indexOf(opcao) == -1) {
			System.out.println("Opção inválida. Tente novamente");	
			opcao = scan.nextLine();
		}

		switch(opcao){
		case "0":			
			System.out.println("Encerrado pelo usuário.");
			break;
		case "1":
			Funcoes.imprimirFrota(carros, true);
			Funcoes.waitForEnter(scan, true);
			break;
		case "2":
			Funcoes.imprimirClientes(clientes, carros);
			Funcoes.waitForEnter(scan, true);
			break;
		case "3":
			Funcoes.adicionarCliente(clientes, scan);
			Funcoes.salvarClientes(clientes);
			Funcoes.waitForEnter(scan, true);
			break;
		case "4":
			Funcoes.alugarVeiculo(carros, clientes, scan);
			Funcoes.salvarVeiculos (carros);
			Funcoes.waitForEnter(scan, true);
			break;	
		case "5":
			Funcoes.imprimirAlugados(carros, clientes);
			Funcoes.waitForEnter(scan, true);
			break;	
		case "6":
			Funcoes.encerrarAluguel(carros, clientes, scan);
			Funcoes.salvarVeiculos (carros);			
			Funcoes.waitForEnter(scan, true);
			break;				

		}
		}while(!opcao.contentEquals("0"));
		System.out.println("Programa encerrado.");
		scan.close();
		
		}
}