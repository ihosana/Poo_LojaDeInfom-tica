package visao;
import java.util.ArrayList;
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

	@SuppressWarnings("null")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
           Scanner op = new Scanner(System.in);
           Scanner op2 = new Scanner(System.in);
           int opcao,opcao4 ;
           String opcao2,id, opcao3;
           Cliente pessoa;
           int resp ;
           float preco;
           Cliente cli;
           ClienteDAO Clidao = new ClienteDAO();
           PessoaFisicaDAO pfDAO= new PessoaFisicaDAO();
           PessoaFisica pf ;
           PessoaJuridicaDAO Pjdao= new PessoaJuridicaDAO();
           PessoaJuridica pj;
           Venda venda;
           Produto p ;
           Produto p1;
           Usuario u;
           VendaDAO vDAO= new VendaDAO();
           UsuarioDAO uDAO= new UsuarioDAO();
           boolean produtoV=false, vendaV=false;
           ProdutoDAO pDAO =new ProdutoDAO();           
           LocalDateTime agora = LocalDateTime.now();
           ArrayList<Produto> produtos;
           int i;
		
        //MENU PRINCIPAL   
        do {
		
		System.out.println("---------------------");
		System.out.println("   Menu Principal    ");
		System.out.println("---------------------");
		System.out.println("1-venda");
		System.out.println("2-Produtos");
		System.out.println("3-Cliente");
		System.out.println("4-Acessar o historico de vendas por data");
		System.out.println("5-Sair");
	    opcao=op.nextInt();
		
	    
	    switch(opcao){
		case 1:
		  do {
			    	System.out.println("---------------------");
					System.out.println("Menu Secundario De VENDA");
					System.out.println("---------------------");
					System.out.println("1-Cadastrar Venda");
					System.out.println("2-Cancelar Venda(Excluir venda)");
					System.out.println("3-voltar ao menu principal");
					System.out.println("Digite a opcao desejada");
					opcao=op2.nextInt();
			 switch(opcao) {
			    case 1:
						
				 venda = new Venda();
				 p = new Produto();
				 cli= new Cliente();
				 produtos=new ArrayList<Produto>();
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
				    if(u!=null) {
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
					           produtos.add(p);
					        }		
							if(produtoV==true){
							   DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
							   String dataFormatada = formatterData.format(agora);
							   System.out.println("DATA da realização da venda:"+dataFormatada);
							   DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
							   String horaFormatada = formatterHora.format(agora);
							   System.out.println("HORA da realização da venda:"+horaFormatada);
							   venda.setData_venda(dataFormatada);
				   			   venda.setHorario_venda(horaFormatada);	
				   			   venda.setFk_usuario(opcao);
						       venda.setFk_cliente(id);
							   vDAO.inserirVenda(venda);
							   System.out.println("Venda sucedida com sucesso!!!"); 
						   }
							 //ADICIONA NO ARRAYLIST DE "PRODUTOS"
							//Enche o ARRAYLIST 
							
							  System.out.println("deseja inserir mais produtos?1-s 2-n");
							  resp=op.nextInt();
					 }while(resp!=2 && produtoV!=false);
						   if(produtoV==true) {
							   venda.setP(produtos);//MANDA O ARRAYLIST PARA o setP
							   
							   for(i=0;i<=produtos.size();i++) {
//								System.out.println("*********Comprovante!!!!*********");								
//								System.out.println("---------PRODUTO-"+(i+1)+"--------");
								System.out.println("produto:"+venda.getP());
//								System.out.println("Codigo do produto: "+produtos.get(i).getCodigoProduto());
//								System.out.println("Nome: "+produtos.get(i).getNomeProduto());	
//								System.out.println("Valor: "+produtos.get(i).getValorProduto());
//								System.out.println("Categoria: "+produtos.get(i).getCategoria());
//							  
							   
							   }
							   System.out.println("Venda sucedida com sucesso!!!"); 		  
						   }
					        
			      }else {
						System.out.println("Codigo de Funcionario invalido");
				  }	
				}
					   
			 break;	
			 case 2:
				do {
				  venda = new Venda(); 
				  System.out.println("Cancelando a venda...");
				  System.out.println("Digite o codigo da venda");
				  opcao=op.nextInt();
				  venda.setId(opcao);
				  venda= vDAO.buscarVenda(opcao);
				 if(venda==null) {
				     //ERRO
				 	vendaV=false;
				 	System.out.println("codigo da Venda INVALIDA!!!");
				 	break;
			 	
			 	 }else{
				   //EXCLUI
				   vendaV=true;
				   System.out.println("EXCLUINDO a venda......");
				   vDAO.excluirVenda(opcao);
				   	System.out.println("Venda excluida com sucesso");
				   	System.out.println("deseja Excluir mais produtos?1-s 2-n");
				    resp=op.nextInt();
				  }
			   }while(resp!=2 && vendaV==true);
				if(vendaV==true){
					System.out.println("Venda excluida com sucesso");
				 }
		  };
				    
	     }while(opcao!=3);		
		break;
		case 2:
			
		  do {
				
				System.out.println("---------------------");
				System.out.println("Menu Secundario de PRODUTO");
				System.out.println("---------------------");
				System.out.println("1-Cadastrar o PRODUTO");
				System.out.println("2-Alterar o valor do PRODUTO");
				System.out.println("3-Excluir produto");
				System.out.println("4-voltar ao menu principal");
				System.out.println("Digite a opcao desejada");
				opcao=op2.nextInt();
				switch(opcao) {
				case 1:
					op.nextLine();
				    System.out.println("Digite o codigo do produto a ser inserido:");
				    opcao4=op.nextInt();
				    p1=pDAO.buscarProduto(opcao4);
					   if(p1==null){ 
						   p1 =new Produto();
						   p1.setCodigoProduto(opcao4); 
						   op2.nextLine();
						   System.out.println("Digite o nome do Produto:");		 
						   p1.setNomeProduto(op2.nextLine());
						   System.out.println("Digite o valor do produto:");
						   p1.setValorProduto(op2.nextFloat());
						   op2.nextLine();
						   System.out.println("Categoria do produto:");
						   p1.setCategoria(op2.nextLine());
						   pDAO.inserir(p1);
						   System.out.println("Produto cadastrado com sucesso!");
					   }else{
					       System.out.println("Produto ja cadastrado");
					    }
			    break;
				case 2:
					
					System.out.println("Alterando o valor do PRODUTO....");
					System.out.println("Qual o produto deseja alterar o preço? digite o codigo");
					opcao=op.nextInt();		
					p1=pDAO.buscarProduto(opcao);
					if(p1!=null){ 
						System.out.println("Quanto o produto vai custar?");
						preco=op.nextFloat();	
						pDAO.alterar(preco, opcao);
						System.out.println("Produto ALTERADO com sucesso!");
					}else{
					   System.out.println("Produto NÃO cadastrado");
					}
				break;
				case 3:
					System.out.println("Excluindo o produto....");
					System.out.println("Qual o produto deseja excluir? digite o codigo");
					opcao=op.nextInt();		
					p1=pDAO.buscarProduto(opcao);
					if(p1!=null){ 
						pDAO.excluirPrduto(opcao);
						System.out.println("Produto EXCLUIDO com sucesso!");
					}else{
					    System.out.println("Produto NÃO cadastrado");
					}
				break;
			 }
		   }while(opcao!=4);
		break;
		case 3:
			do {
				    System.out.println("---------------------");
					System.out.println("Menu Secundario CLIENTE");
					System.out.println("---------------------");
					System.out.println("1-Cadastrar Cliente");
					System.out.println("2-Excluir Cliente");
					System.out.println("3-Alterar o CPF/CNPJ do Cliente");
					System.out.println("4-voltar ao menu principal");
					System.out.println("Digite a opcao desejada");
					opcao=op2.nextInt();
			        
					switch(opcao) {
					case 1:
						System.out.println("Cadastrando cliente...");
						 
				    	  System.out.println("1-Pessoa fisica ou 2-Pessoa juridica?");
				    	  opcao=op.nextInt();
				    	  op.nextLine();
				       if(opcao==1) {  
				    	  System.out.println("Digite o CPF:");
				    	  opcao2=op.nextLine();
				    	 
				    	  cli=Clidao.buscar(opcao2);
				         if(cli==null) {
				        	 cli= new Cliente();
				        	 pf= new PessoaFisica();
				        	 pf.setCpf(opcao2);
					    	   System.out.println("Digite o nome:");
					    	   pf.setNome(op.nextLine());
					    	   System.out.println("Digite o Endereço:");
					    	   cli.setEndereco(op.nextLine());
					    	   System.out.println("Digite o Tel:");
					    	   cli.setTelefone(op.nextInt());
					    	   cli.setId(opcao2);
					    	   Clidao.cadastrar(cli);
					    	   pfDAO.Cadastrar(pf);
					    	   System.out.println("Pessoa cadastrada com SUCESSO!");
				    	  }else {
				    		  System.out.println("Pessoa ja cadastrada");
				    	  }
				       }else {
				    		  op.nextLine();
				    		 
				    		
				    		  System.out.println("Digite o CNPJ:");
					    	   opcao3=op.nextLine();
					    	  
					    	   cli=Clidao.buscar(opcao3);
					     if(cli==null) { 
					    	 pj= new PessoaJuridica();
					    	 cli= new Cliente();
					    	 pj.setCnpj(opcao3);
					    	   System.out.println("Digite o nome social:");
					    	   pj.setNome_social(op.nextLine());
					    	   System.out.println("Digite o Endereço:");
					    	   cli.setEndereco(op.nextLine());
					    	   System.out.println("Digite o Tel:");
					    	   cli.setTelefone(op.nextInt());
					    	   cli.setId(opcao3);
					    	   Clidao.cadastrar(cli);
					    	   Pjdao.Cadastrar(pj);
					    	   System.out.println("Pessoa cadastrada com SUCESSO!");
					     }else {
					    	 System.out.println("Pessoa ja cadastrada");
					     }
				      }
				    break;
					case 2:
						op.nextLine();
						System.out.println("Excluindo Cliente....");
						System.out.println("Digite o cod Cliente:");
				    	opcao2=op.nextLine();
				    	cli=Clidao.buscar(opcao2);
                         
                         
				        if(cli!=null) {
				        	 //BUSCAR EM PESSOA FISICA
				        	 pf= pfDAO.buscarPessoaF(opcao2);
				           if(pf!=null){

		                        venda=vDAO.buscarVendaPorPessoa(opcao2);
                               if(venda!=null) {
								 vDAO.excluirVendaPorPessoa(opcao2);
								 Clidao.excluirCliente(opcao2); 
                            	 pfDAO.excluirPessoaF(opcao2); 
								 System.out.println("Cliente EXCLUIDO com SUCESSO!");
                                }else {
                                	Clidao.excluirCliente(opcao2); 
                                	 pfDAO.excluirPessoaF(opcao2);
    				        		 
    				        		 System.out.println("Cliente EXCLUIDO com SUCESSO!Mas n possui nenhuma venda"); 
                                }
								
				        		
				            }else{
				        	  System.out.println("Cliente não é uma pessoa fisica.");
				        	  pj=Pjdao.buscarPessoaJ(opcao2);
				        	  if(pj!=null) {
	                              venda=vDAO.buscarVendaPorPessoa(opcao2);
	                              if(venda!=null) {
	                            	  vDAO.excluirVendaPorPessoa(opcao2);
	                            	  Clidao.excluirCliente(opcao2); 
	                            	  Pjdao.excluirPessoaJ(opcao2);
	                            	  System.out.println("Pessoa Juridica completa excluida");
	                              }else {
	                            	  Clidao.excluirCliente(opcao2); 
	                            	  Pjdao.excluirPessoaJ(opcao2);
	                            	  System.out.println("Pessoa Juridica sem venda excluida");
	 	                             
	                               }
				        	  }else{
				        		  vDAO.excluirVendaPorPessoa(opcao2);
					        	  Clidao.excluirCliente(opcao2);
					        	  System.out.println("Cliente sem especificação");
					          }
//				        		  System.out.println("Apenas cliente");
				        	
				            }
				         
				         
				        }else {
							System.out.println("Cliente NÃO existe!!!");
				        }
				        
				        
				        
				    break;
					case 3:
					break;
					
					
			    }			
					
				    
						
			     
		}while(opcao!=4);
					
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
