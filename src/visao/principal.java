package visao;
import java.util.Scanner;
import dominio.Cliente;
import dominio.PessoaFisica;
import dominio.PessoaJuridica;
import dominio.Produto;
import dominio.Usuario;
import dominio.Venda;
import persistencia.ClienteDAO;
import persistencia.PessoaFisicaDAO;
import persistencia.PessoaJuridicaDAO;
import persistencia.ProdutoDAO;
import persistencia.UsuarioDAO;
import persistencia.VendaDAO;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
           Scanner op = new Scanner(System.in);
           Scanner op2 = new Scanner(System.in);
           int opcao,opcao4 ;
           String opcao2,id, opcao3;
           Cliente pessoa;
           int resp ;
           Cliente cli;
           ClienteDAO Clidao = new ClienteDAO();
           PessoaFisicaDAO pfDAO= new PessoaFisicaDAO();
           PessoaFisica pf ;
           PessoaJuridicaDAO Pjdao= new PessoaJuridicaDAO();
           PessoaJuridica pj;
           Venda venda;
           Produto p ;
           Usuario u;
           long data;
           long H;
           VendaDAO vDAO= new VendaDAO();
           UsuarioDAO uDAO= new UsuarioDAO();
           boolean produtoV=false;
           ProdutoDAO pDAO =new ProdutoDAO();           
           LocalDateTime agora = LocalDateTime.now();
		
        //MENU PRINCIPAL   
        do {
			
		System.out.println("---------------------");
		System.out.println("   Menu Principal    ");
		System.out.println("---------------------");
		System.out.println("1-Cadastrar venda");
		System.out.println("2-Cadastrar Produtos");
		System.out.println("3-Cadastrar Cliente");
		System.out.println("4-Acessar o historico de vendas por data");
		System.out.println("5-Sair");
	    opcao=op.nextInt();
		
	    
	    switch(opcao){
		case 1:
			venda = new Venda();
			p = new Produto();
			cli= new Cliente();
			System.out.println("Cadastrando as vendas...");	
		    op.nextLine();
			//CODIGO DA BUSCA DO CLIENTE
		     System.out.println("Digite o CNPJ ou o CPF:");
		     id=op.nextLine();
		     cli.setId(id);
		     cli=Clidao.buscar(id);
		     if(cli==null) {
			   System.out.println("Cliente PRECISA ser cadastrado");
			 }else{
			
			     //CODIGO DA BUSCA DO FUNCIONARIO
				u=new Usuario();
				System.out.println("Digite seu codigo de funcionario: ");
				opcao=op.nextInt();
				u.setId(opcao);
				u=uDAO.buscarUsuario(opcao);
				if(u==null) {
				  System.out.println("Codigo de Funcionario invalido");
				break;
					//CODIGO DA BUSCA DO PRODUTO
				}else {
					
				  do{
  					  System.out.println("Digite o codigo do produto a ser inserido:");
					  opcao4=op.nextInt();
					  p.setCodigoProduto(opcao4);
					  p=pDAO.buscarProduto(opcao4);
					 if(p==null) { 
					   	System.out.println("Produto PRECISA ser cadastrado");	
					   	produtoV=false;
					   	break;
					 }else{
					      op.nextLine();
					      produtoV=true;
						  venda.setFk_produto(opcao4);
					      System.out.println("Tudo certo");
					      System.out.println("Digite a quantidade que deseja adicionar do produto na venda:");
					      venda.setQnt(op.nextInt());
					      System.out.println("deseja inserir mais produtos?1-s 2-n");
				    	  resp=op.nextInt();
					}		    
			     }while(resp!=2 && produtoV!=false);
				  
					if(produtoV==true){
						DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
						String dataFormatada = formatterData.format(agora);
						System.out.println("DATA da realização da venda:"+dataFormatada);
						DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
						String horaFormatada = formatterHora.format(agora);
						System.out.println("HORA da realização da venda:"+horaFormatada);
						//SimpleDateFormat formato = new SimpleDateFormat(dataFormatada);
					  venda.setData_venda(dataFormatada);
//		    		  DateTimeFormatter Hora= DateTimeFormatter.ofPattern(horaFormatada);
   				      venda.setHorario_venda(horaFormatada);	
                      venda.setId(7);

   				      venda.setFk_usuario(opcao);
		              venda.setFk_cliente(id);
				      vDAO.inserirVenda(venda);
							
				  	  System.out.println("Venda sucedida com sucesso!!!"); 
				  }
				}	
			 }
		    	System.out.println("---------------------");
				System.out.println("Menu Secundario De VENDA");
				System.out.println("---------------------");
				System.out.println("1-Cancelar Venda(Excluir venda)");
				System.out.println("2-voltar ao menu principal");
				System.out.println("Digite a opcao desejada");
				opcao=op2.nextInt();
				switch(opcao) {
				case 1:
					System.out.println("Cancelando a venda...");
					
			    break;
    	}while(opcao!=2);
			
		break;
		case 2:
			Produto  p1 = new Produto();
			System.out.println("Cadastrando os produtos");
			System.out.println("Digite o codigo do produto");
			 opcao4=op.nextInt();
			  p1=pDAO.buscarProduto(opcao4);
			
			if(p1==null){

				  p1.setCodigoProduto(opcao4);
				System.out.println("Digite o nome do Produto:");
				opcao2=op.nextLine();
				p1.setNomeProduto(opcao2);
			System.out.println("Digite o valor do produto:");
			    p1.setValorProduto(op.nextFloat());
			System.out.println("Categoria do produto:");
			   p1.setCategoria(op.nextLine());
			   
			   pDAO.inserir(p1);
			}else { 
				System.out.println("Produto ja EXISTE");	
			
			}
			
			
			
			
			do {
				
				System.out.println("---------------------");
				System.out.println("   Menu Secundario    ");
				System.out.println("---------------------");
				System.out.println("1-");
				System.out.println("2-Atualizar Venda(Excluir produtos da venda)");
				System.out.println("3-voltar ao menu principal");
				System.out.println("Digite a opcao desejada");
				opcao=op2.nextInt();
				switch(opcao) {
				case 1:
					System.out.println("Cancelando a venda...");
					
			    break;
				case 2:
					System.out.println("Atualizando a venda");
										
				break;
				default:
					System.out.println("invalido");
			    break;
			    
				}
				
				
			}while(opcao!=3);
		break;
		case 3:
			do {
			op.nextLine();
			System.out.println("Cadastrando cliente...");
			  pj= new PessoaJuridica();
			  pf= new PessoaFisica();
	    	  cli= new Cliente();
	    	  System.out.println("1-Pessoa fisica ou 2-Pessoa juridica?");
	    	  opcao=op.nextInt();
	       if(opcao==1) {  
	    	  System.out.println("Digite o CPF:");
	    	  opcao2=op.nextLine();
	    	  pf.setCpf(opcao2);
	    	  op.nextLine();
	    	  cli=Clidao.buscar(opcao2);
	         if(cli==null) {
	    		 
		    	   System.out.println("Digite o nome:");
		    	   pf.setNome(op.nextLine());
		    	   System.out.println("Digite o Endereço:");
		    	   cli.setEndereco(op.nextLine());
		    	   System.out.println("Digite o Tel:");
		    	   cli.setTelefone(op.nextInt());
		    	   cli.setId(opcao2);
		    	   Clidao.cadastrar(cli);
		    	   pfDAO.Cadastrar(pf);
	    	  }else {
	    		  System.out.println("Pessoa ja cadastrada");
	    	  }
	       }else {
	    		  op.nextLine();
	    		  System.out.println("Digite o CNPJ:");
		    	   opcao3=op.nextLine();
		    	   pj.setCnpj(opcao3);
		    	   cli=Clidao.buscar(opcao3);
		     if(cli==null) {
		    	   System.out.println("Digite o nome social:");
		    	   pj.setNome_social(op.nextLine());
		    	   System.out.println("Digite o Endereço:");
		    	   cli.setEndereco(op.nextLine());
		    	   System.out.println("Digite o Tel:");
		    	   cli.setTelefone(op.nextInt());
		    	   cli.setId(opcao3);
		    	   Clidao.cadastrar(cli);
		    	   Pjdao.Cadastrar(pj);
		     }else {
		    	 System.out.println("Pessoa ja cadastrada");
		     }
	    	   
	    	  
	    	  }
	        System.out.println("---------------------");
			System.out.println("Menu Secundario CLIENTE");
			System.out.println("---------------------");
			System.out.println("1-Atualizar Cadastro do Cliente");
			System.out.println("2-Excluir Cliente");
			System.out.println("3-voltar ao menu principal");
			System.out.println("Digite a opcao desejada");
			opcao=op2.nextInt();
	        
			switch(opcao) {
			case 1:
				System.out.println("Atualizando o cadastro do Cliente..");
				
		    break;
			case 2:
				System.out.println("Excluindo Cliente");
									
			break;
			default:
				System.out.println("invalido");
		    break;
		    
			}
			
			}while(opcao!=2);
			
    	break;
		case 4:
			System.out.println("Acessando o historico de vendas por data...");
			
		break;
    	
    	default:
    		System.out.println("opcao invalida");
    	break;
		    
		
	    
	}
  }while(opcao!=5);
	

 }
}
