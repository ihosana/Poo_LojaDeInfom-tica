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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
           Scanner op = new Scanner(System.in);
           Scanner op2 = new Scanner(System.in);
           int opcao,opcao4, usuario,tel ;
           String opcao2,id, opcao3, endereco, ATUAL;
           Cliente pessoa;
           int resp ;
           float preco;
           Cliente cli,clienteAtual;
           ClienteDAO Clidao = new ClienteDAO();
           PessoaFisicaDAO pfDAO= new PessoaFisicaDAO();
           PessoaFisica pf ;
           PessoaJuridicaDAO Pjdao= new PessoaJuridicaDAO();
           PessoaJuridica pj;
           Venda venda;
           Produto p ;
           Produto p1;
           ArrayList<Produto> prod;
           Usuario u;
           VendaDAO vDAO= new VendaDAO();
           UsuarioDAO uDAO= new UsuarioDAO();
           boolean produtoV=false, vendaV=false;
           ProdutoDAO pDAO =new ProdutoDAO();           
           LocalDateTime agora = LocalDateTime.now();
          
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
				 ArrayList<Produto> produtos=new ArrayList<Produto>();
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
				    usuario=op.nextInt();
				    u.setId(usuario);
				    u=uDAO.buscarUsuario(usuario);
				    if(u!=null) {
					  do{
				  		   System.out.println("Digite o codigo do produto a ser inserido:");
						   opcao4=op.nextInt();
						   p=pDAO.buscarProduto(opcao4);
						   if(p==null) { 
						   	  System.out.println("Produto PRECISA ser cadastrado");	
						   	  produtoV=false;
						      break;
						   }else{
						       op.nextLine();
						       produtoV=true;
						       p.setCodigoProduto(opcao4);
						       venda.setFk_produto(opcao4);
						       produtos.add(p);
					           System.out.println("Tudo certo");
					           System.out.println("Digite a quantidade que deseja adicionar do produto na venda:");
					           venda.setQnt(op.nextInt());
					           //DATA E HORA LOCAL
					    	   DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
							   String dataFormatada = formatterData.format(agora);
							   System.out.println("DATA da realização da venda:"+dataFormatada);
							   DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
							   String horaFormatada = formatterHora.format(agora);
							   System.out.println("HORA da realização da venda:"+horaFormatada);
							   venda.setData_venda(dataFormatada);
				   			   venda.setHorario_venda(horaFormatada);	
				   			   venda.setFk_usuario(usuario);
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
							   venda.setP(produtos);
							   System.out.println("*********Comprovante!!!!*********");						
							   for(i=0;i<venda.getP().size();i++) {
								System.out.println("---------PRODUTO-"+(i+1)+"--------");
//								System.out.println("produto:"+venda.getP());
								System.out.println("Codigo do produto: "+venda.getP().get(i).getCodigoProduto());
								System.out.println("Nome: "+venda.getP().get(i).getNomeProduto());	
								System.out.println("Valor: "+venda.getP().get(i).getValorProduto());
								System.out.println("Categoria: "+venda.getP().get(i).getCategoria());
							  
							   
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
				   	System.out.println("deseja Excluir mais alguma venda?1-s 2-n");
				    resp=op.nextInt();
				  }
			   }while(resp!=2);
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
					
						venda=vDAO.buscarVendaPorProduto(opcao);
						if(venda!=null) {
							vDAO.excluirVendaPorProduto(opcao);
							pDAO.excluirPrduto(opcao);
							System.out.println("Produto EXCLUIDO com sucesso!");
							System.out.println("Produto ESTAR em alguma venda");
						}else {
							pDAO.excluirPrduto(opcao);
							System.out.println("Produto EXCLUIDO com sucesso!");
							System.out.println("Produto NÃO estar em nenhuma venda");
						}
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
				   }else{
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
				    //BUSCAR SE O CLIENTE EXISTE
				  if(cli!=null) {
				      //BUSCAR EM PESSOA FISICA
				      pf= pfDAO.buscarPessoaF(opcao2);
				      venda= vDAO.buscarVendaPorPessoa(opcao2);
				      if(pf!=null){
					    System.out.println("PESSOAFISICA");
                        if(venda!=null) {
						   vDAO.excluirVendaPorPessoa(opcao2);
						   pfDAO.excluirPessoaF(opcao2); 

						   Clidao.excluirCliente(opcao2); 
                           System.out.println("PessoaFISICA possui VENDA"); 
                         }else {
                           pfDAO.excluirPessoaF(opcao2);
                           Clidao.excluirCliente(opcao2); 
                           System.out.println("PessoaFISICA NÃO possui VENDA"); 
    				      }
				        		
				      }else{
				        	
				        pj=Pjdao.buscarPessoaJ(opcao2);
				        if(pj!=null) {
				       	  System.out.println("PESSOAJURIDICA.");
                          venda=vDAO.buscarVendaPorPessoa(opcao2);
	                      if(venda!=null) {
	                         vDAO.excluirVendaPorPessoa(opcao2);
	                         Pjdao.excluirPessoaJ(opcao2);
	                         Clidao.excluirCliente(opcao2);
	                         System.out.println("PESSOAJURIDICA possui VENDA.");
	                        }else {
	                            
	                           Pjdao.excluirPessoaJ(opcao2);
	                           Clidao.excluirCliente(opcao2);
                               System.out.println("PESSOAJURIDICA NÃO possui VENDA.");
	                        }
				        		 
				        }else{
					        System.out.println("CLIENTE !!");
					        venda=vDAO.buscarVendaPorPessoa(opcao2);
	                        if(venda==null) {
  	  				          Clidao.excluirCliente(opcao2);
                              System.out.println("CLIENTE NÃO possui VENDA.");
	                        }else {
	                          Clidao.excluirCliente(opcao2);
							       
	                          vDAO.excluirVendaPorPessoa(opcao2);
						      
                               System.out.println("CLIENTE  possui VENDA.");
	                        }
					   }
				     }
				    }else{
					   System.out.println("Cliente NÃO existe!!!");
				    }
				break;
				case 3:
				  op.nextLine();
				  System.out.println("Alterando o CPF/CNPJ do CLIENTE.....");
				  System.out.println("Digite o CPF/CNPJ antigo: ");
				  opcao2=op.nextLine();
				  cli=new Cliente();
				  cli=Clidao.buscar(opcao2);
			      if(cli!=null) {
			    	  //MOSTRAR CLIENTE
			    	   System.out.println("----------CLIENTE EXISTENTE--------------------");
		    		  
			    	   System.out.println("ID: "+cli.getId());
		    		   System.out.println("Endereço: "+cli.getEndereco());
		    		   endereco=cli.getEndereco();
		    		   System.out.println("Tel: "+cli.getTelefone());
		    		   tel=cli.getTelefone();
		    		   pf = new PessoaFisica();
//		    		   cli.setTelefone(cli.getTelefone());
					   pf= pfDAO.buscarPessoaF(opcao2);
					  if(pf!=null){
						  System.out.println("----------PessoaFISICA IDENTIFICADA---------");
						  System.out.println("Nome: "+pf.getNome());
						  venda= new Venda();
						  venda=vDAO.buscarVendaPorPessoa(opcao2);
						  if(venda!=null) {
//				    		 for(i=0;i< venda.getP().size();i++) {
				    			 System.out.println("------------------------------");
								  System.out.println("PessoFISICA POSSUI venda");
								  System.out.println("Data da venda: "+ venda.getData_venda());
								  System.out.println("Horario da venda: "+ venda.getHorario_venda());
								  System.out.println("Qtd: "+ venda.getQnt());
								  System.out.println("Funcionario: "+venda.getFk_usuario());
								  System.out.println("Produto: "+venda.getFk_produto()); 
								  
//				    		 }
				    	 
						  
						 do {
							  System.out.println("Digite o NOVO CPF:");
							  ATUAL=op.nextLine();
	                          clienteAtual=Clidao.buscar(ATUAL);
						 }while(clienteAtual!=null);
						 
						 clienteAtual=new Cliente();
						 clienteAtual.setId(ATUAL);
						 clienteAtual.setEndereco(endereco);
						 clienteAtual.setTelefone(tel);
                         Clidao.cadastrar(clienteAtual);
                         
//                         pf.setCpf(cpfATUAL);
//                         pf.setNome(pf.getNome());
                         pfDAO.atualizarPF(ATUAL, opcao2);
                         vDAO.atualizar(ATUAL, opcao2);
                        
//                         venda.setData_venda(venda.getData_venda());
//                         venda.setHorario_venda(venda.getHorario_venda());
//                         venda.setFk_produto(venda.getFk_produto());
//                         venda.setFk_usuario(venda.getFk_usuario());
//                         venda.setFk_cliente(cpfATUAL);
//                         vDAO.inserirVenda(venda);

//                         pfDAO.excluirPessoaF(opcao2);

//                         vDAO.excluirVendaPorPessoa(opcao2);
                         Clidao.excluirCliente(opcao2);
                         System.out.println("CPF alterado com SUCESSO");
                      
                           
						  }else {
							System.out.println("****PessoFISICA NÃO possui venda");
	                       
	                        do {
								  System.out.println("Digite o NOVO CPF:");
								  ATUAL=op.nextLine();
		                          clienteAtual=Clidao.buscar(ATUAL);
							 }while(clienteAtual!=null);
							 
							 clienteAtual=new Cliente();
							 clienteAtual.setId(ATUAL);
							 clienteAtual.setEndereco(endereco);
							 clienteAtual.setTelefone(tel);
	                         Clidao.cadastrar(clienteAtual);
	                         pfDAO.atualizarPF(ATUAL, opcao2);
	                         Clidao.excluirCliente(opcao2);
	                         System.out.println("CPF alterado com SUCESSO");
	                 
                          }
                           
	                  }else {
	                	  pj= new PessoaJuridica();
	                	 pj=Pjdao.buscarPessoaJ(opcao2);
					     if(pj!=null) {
					    	 System.out.println("----------PessoaFISICA IDENTIFICADA---------");
							 System.out.println("Nome: "+pj.getNome_social());
					         venda=vDAO.buscarVendaPorPessoa(opcao2);
					       if(venda!=null) {
					    	   System.out.println("------------------------------");
								  System.out.println("PessoaJURIDICA IDENTIFICADA");
								  System.out.println("Data da venda: "+ venda.getData_venda());
								  System.out.println("Horario da venda: "+ venda.getHorario_venda());
								  System.out.println("Qtd: "+ venda.getQnt());
								  System.out.println("Funcionario: "+venda.getFk_usuario());
								  System.out.println("Produto: "+venda.getFk_produto()); 
								  
		                       do {
									  System.out.println("Digite o NOVO CPF:");
									  ATUAL=op.nextLine();
			                          clienteAtual=Clidao.buscar(ATUAL);
								 }while(clienteAtual!=null);
								 
								 clienteAtual=new Cliente();
								 clienteAtual.setId(ATUAL);
								 clienteAtual.setEndereco(endereco);
								 clienteAtual.setTelefone(tel);
		                         Clidao.cadastrar(clienteAtual);
		                         Pjdao.atualizarPJ(ATUAL, opcao2);
		                         vDAO.atualizar(ATUAL, opcao2);
		                         Clidao.excluirCliente(opcao2);
		                         System.out.println("CNPJ alterado com SUCESSO");
		                      
						   }else {
							   System.out.println("PessoaJURIDICA NÃO possui venda !!");
							   do {
									  System.out.println("Digite o NOVO CPF:");
									  ATUAL=op.nextLine();
			                          clienteAtual=Clidao.buscar(ATUAL);
								 }while(clienteAtual!=null);
								 
								 clienteAtual=new Cliente();
								 clienteAtual.setId(ATUAL);
								 clienteAtual.setEndereco(endereco);
								 clienteAtual.setTelefone(tel);
		                         Clidao.cadastrar(clienteAtual);
		                         Pjdao.atualizarPJ(ATUAL, opcao2);
		                         Clidao.excluirCliente(opcao2);
		                         System.out.println("CNPJ alterado com SUCESSO");
							   
		                   }
					     }else {
					        	System.out.println("Pessoa não IDENTIFICADA como PJ ou PF!!");
					            System.out.println("É um CLIENTE!");
					            //MOSTRAR CLIENTE
						    	   System.out.println("----------CLIENTE EXISTENTE--------------------");
					    		  
						    	   System.out.println("ID: "+cli.getId());
					    		   System.out.println("Endereço: "+cli.getEndereco());
					    		   endereco=cli.getEndereco();
					    		   System.out.println("Tel: "+cli.getTelefone());
					    		   tel=cli.getTelefone();
					               venda=vDAO.buscarVendaPorPessoa(opcao2);
							       if(venda!=null) {
							    	   System.out.println("------------------------------");
									   System.out.println("Data da venda: "+ venda.getData_venda());
									   System.out.println("Horario da venda: "+ venda.getHorario_venda());
									   System.out.println("Qtd: "+ venda.getQnt());
									   System.out.println("Funcionario: "+venda.getFk_usuario());
									   System.out.println("Produto: "+venda.getFk_produto()); 
										  
				                       do {
											  System.out.println("Digite o NOVO CPF:");
											  ATUAL=op.nextLine();
					                          clienteAtual=Clidao.buscar(ATUAL);
										 }while(clienteAtual!=null);
										 
										 clienteAtual=new Cliente();
										 clienteAtual.setId(ATUAL);
										 clienteAtual.setEndereco(endereco);
										 clienteAtual.setTelefone(tel);
				                         Clidao.cadastrar(clienteAtual);
				                         vDAO.atualizar(ATUAL, opcao2);
				                         Clidao.excluirCliente(opcao2);
				                         System.out.println("Chave do CLIENTE alterado com SUCESSO");
							       }else {
							    	   System.out.println("CLIENTE sEM VENDA");
									    
							    	   do {
											  System.out.println("Digite o NOVO CPF:");
											  ATUAL=op.nextLine();
					                          clienteAtual=Clidao.buscar(ATUAL);
										 }while(clienteAtual!=null);
										 
										 clienteAtual=new Cliente();
										 clienteAtual.setId(ATUAL);
										 clienteAtual.setEndereco(endereco);
										 clienteAtual.setTelefone(tel);
				                         Clidao.cadastrar(clienteAtual);
				                         Clidao.excluirCliente(opcao2);
				                         System.out.println("Chave do CLIENTE alterado com SUCESSO");
										    
							       }
					     }
	                       
	    		       }
				  }else {
					  System.out.println("Cliente NÃO existe!!!!");
				  }	
				break;
					
					
	       }		
		}while(opcao!=4);
					
    	break;
		case 4:
			
			
		break;
    	
    	default:
    		System.out.println("opcao invalida");
    	break;
		    
		
	    
	}
  }while(opcao!=5);
	

 }
}
