package visao;

import java.util.Scanner;

public class principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
           Scanner op = new Scanner(System.in);
           int opcao;
		do {

		System.out.println("---------------------");
		System.out.println("   Menu Principal    ");
		System.out.println("---------------------");
		System.out.println("1-Cadastrar venda");
		System.out.println("2-Excluir produtos da venda");
		System.out.println("3-Relatorio das vendas");
		System.out.println("4-Cadastrar Produtos");
		System.out.println("5-Excluir Produtos");
		System.out.println("6-Cadastrar Cliente");
	
		System.out.println("---------------------");
		System.out.println("   Menu secundario   ");
		System.out.println("---------------------");
		System.out.println("1-Buscar venda por data");
		System.out.println("2-Buscar venda pelo nome do cliente ");
		System.out.println("3-Voltar ao /Menu Principal/ ");
		System.out.println("Dgite a opcao desejada🥰🥰 ");	
	    opcao=op.nextInt();
		switch(opcao) {
		case 1:
			System.out.println("Cadastrando as vendas...");
			
		break;
		case 2:
			System.out.println("Excluindo produtos da venda...");
		break;
		case 3:
			System.out.println("Exibindo o relatorio das vendas...");
		break;
		case 4:
			System.out.println("Cadastrando os produtos...");
	    break;
		case 5:
			System.out.println("Excluindo o produto...");
		break;
		case 6:
			System.out.println("Cadastrando cliente...");
    	break;
    	default:
    		System.out.println("opcao invalida");
    	break;
		    
		}
	    
		}while(opcao!=6);	
  }

}
