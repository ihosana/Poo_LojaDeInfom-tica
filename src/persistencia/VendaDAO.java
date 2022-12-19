package persistencia;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dominio.Cliente;
import dominio.Produto;
import dominio.Usuario;
import dominio.Venda;

public class VendaDAO {
	private Conexao c;
	private ClienteDAO cliente;
	private UsuarioDAO udao;
	private String TodasVendas="SELECT*FROM Venda";
	private String Inserir="Insert into Venda (datavenda,horavenda,qntProduto,fk_usuario,fk_produto,fk_cliente) VALUES (?,?,?,?,?,?); ";
	private String Buscar= " SELECT* from Venda where id=?";
	private String Deletar="Delete from Venda where id=?";
	private String ExcluirP="Delete from Venda where fk_cliente=? ";
	private String BuscarP= "SELECT* FROM venda where fk_cliente=? "; 
	private String BuscarProduto="SELECT*FROM venda where fk_produto=?";
	private String ExcluirProduto="Delete from venda where fk_produto=? ";
	private String AtualizarVenda ="UPDATE venda set fk_cliente=? where fk_cliente=? ";
	private String BuscarVendaPorUser="select*from venda where fk_usuario=?";

	private String ExcluirUser="Delete from Venda where fk_usuario=?";
	
	//select venda por data

	public VendaDAO() {
		 c=new Conexao("jdbc:postgresql://127.0.0.1:5432/POO_projeto","postgres","ihosanaassis");
	}
	 public void inserirVenda(Venda venda){
		   try {
		 	c.conectar();
			 PreparedStatement instrucao =c.getConexao().prepareStatement(Inserir);
			 instrucao.setString(1,venda.getData_venda());
			 instrucao.setString(2,venda.getHorario_venda());
			 instrucao.setInt(3,venda.getQnt());
			 instrucao.setInt(4,venda.getFk_usuario().getId());
			 instrucao.setInt(5,venda.getFk_produto());
			 instrucao.setString(6,venda.getFk_cliente().getId());
			 instrucao.execute();
		    }catch(Exception e) {
			   System.out.println("ERRO EM Cadastrar VENDAAAA"+ e.getMessage());
		    }
		   
		   c.desconectar();
		   
		   }
	 public Venda buscarVenda(int id) {
		  Venda v= null ;
		  Cliente cli;
		  cliente= new ClienteDAO();
		  udao= new UsuarioDAO() ;
	        Usuario user;
		 try {
			 c.conectar();
			 PreparedStatement instrucao= c.getConexao().prepareStatement(Buscar);
			 instrucao.setInt(1,id);
			
			 ResultSet result= instrucao.executeQuery();
			 if(result.next()) {
					v= new Venda(result.getString("datavenda"),result.getString("horavenda"),result.getInt("qntproduto"),result.getInt("fk_produto"));
					cli= cliente.buscar(result.getString("fk_cliente"));
		            v.setFk_cliente(cli);
		            user= udao.buscarUsuario(result.getInt("fk_usuario"));
		            v.setFk_usuario(user);
		        		
				}
				
			 
			 c.desconectar();
			
			 
		 }catch(Exception e) {
			 System.out.println("erro na BUSCA" + e.getMessage());
		 }
		 return v;
	 }
	 
	 
	 public void excluirVenda(int id) {
      try {
    	  c.conectar();
    	  PreparedStatement instrucao= c.getConexao().prepareStatement(Deletar);
			 instrucao.setInt(1,id);
			 instrucao.execute();
    	  
    	  
    	  c.desconectar();
	  }catch(Exception e) {
			 System.out.println("erro na Exclusão da VENDA" + e.getMessage());
		 }
	 }
	 public void excluirVendaPorPessoa(String id) {
	      try {
	    	  c.conectar();
	    	  PreparedStatement instrucao= c.getConexao().prepareStatement(ExcluirP);
				 instrucao.setString(1,id);
				 instrucao.execute();
	    	  
	    	  
	    	  c.desconectar();
		  }catch(Exception e) {
				 System.out.println("erro na Exclusão de VENDA por PESSOA: " + e.getMessage());
			 }
		 }
   public Venda buscarVendaPorPessoa(String pessoa) {
	   Venda v=null;
	   Cliente cli;
       Usuario user;
       cliente= new ClienteDAO();
		  udao= new UsuarioDAO() ;
	   try {
	    	  c.conectar();
	    	  PreparedStatement instrucao= c.getConexao().prepareStatement(BuscarP);
			  instrucao.setString(1,pessoa);

			  ResultSet result= instrucao.executeQuery();
			  if(result.next()) {
				v= new Venda(result.getString("datavenda"),result.getString("horavenda"),result.getInt("qntproduto"),result.getInt("fk_produto"));
				cli= cliente.buscar(result.getString("fk_cliente"));
	            v.setFk_cliente(cli);
	            user= udao.buscarUsuario(result.getInt("fk_usuario"));
	            v.setFk_usuario(user);
                
			  }
	    	  
	    	  c.desconectar();
		  }catch(Exception e) {
				 System.out.println("erro na BUSCA VENDA POR PESSOA " + e.getMessage());
			 }
	   return v;
   }
	 public ArrayList<Venda> buscarVendaPorP(String pessoa) {
	        ArrayList<Venda> lista = new ArrayList<>();
	        Cliente cli;
	        Usuario user;
	        Venda v;
	        cliente= new ClienteDAO();
			  udao= new UsuarioDAO() ;
	        try{
	        	 c.conectar();
	            PreparedStatement instrucao = c.getConexao().prepareStatement(BuscarP);
	            instrucao.setString(1, pessoa);
	            ResultSet result = instrucao.executeQuery();
	            while(result.next()){
	                v = new Venda(result.getString("datavenda"),result.getString("horavenda"),result.getInt("qntproduto"),result.getInt("fk_produto"));
	                cli= cliente.buscar(result.getString("fk_cliente"));
	                v.setFk_cliente(cli);
	                user= udao.buscarUsuario(result.getInt("fk_usuario"));
		            v.setFk_usuario(user);

	        		cli.setVenda(lista);
	                lista.add(v);
	            }
	           
	            c.desconectar();
	        }catch(Exception e){
	            System.out.println("Erro no relat�rio: "+e.getMessage());
	        }
	        return lista;
	    }
	 
   public Venda buscarVendaPorProduto(int id) {
	   Venda v= null;
	   Cliente cli;
       Usuario user;
       cliente= new ClienteDAO();
		  udao= new UsuarioDAO() ;
	   try {
		   c.conectar();
		   
		   PreparedStatement instrucao= c.getConexao().prepareStatement(BuscarProduto);
           instrucao.setInt(1,id);		   
		   ResultSet result = instrucao.executeQuery();
		   if(result.next()) {
			   v= new Venda(result.getString("datavenda"),result.getString("horavenda"),result.getInt("qntproduto"),result.getInt("fk_produto"));
			   cli= cliente.buscar(result.getString("fk_cliente"));
               v.setFk_cliente(cli);
               user= udao.buscarUsuario(result.getInt("fk_usuario"));
	            v.setFk_usuario(user);

		   }
		   c.desconectar();
	   }catch(Exception a) {
		   System.out.println("ERRO NA BUSCA DE VENDA POR PRODUTO: "+a.getMessage());
	   }
	   return v;
   }
   public void excluirVendaPorProduto(int id) {
	      try {
	    	  c.conectar();
	    	  PreparedStatement instrucao= c.getConexao().prepareStatement(ExcluirProduto);
				 instrucao.setInt(1,id);
				 instrucao.execute();
	    	  
	    	  
	    	  c.desconectar();
		  }catch(Exception e) {
				 System.out.println("erro na Exclusão de VENDA por PRODUTO: " + e.getMessage());
			 }
		 }
   public void atualizar(String id_novo, String id_antigo) {
	      try {
	    	  c.conectar();
	    	  PreparedStatement instrucao= c.getConexao().prepareStatement(AtualizarVenda);
				 instrucao.setString(1,id_novo);
				 instrucao.setString(2,id_antigo);
				 instrucao.execute();
	    	  
	    	  
	    	  c.desconectar();
		  }catch(Exception e) {
				 System.out.println("erro na ATUALIZAÇÃO DA VENDA por PESSOA: " + e.getMessage());
			 }
		 }
   public ArrayList<Venda> emitirVenda() {
	   Venda v;
	   Cliente cli;
       Usuario user;
       cliente= new ClienteDAO();
	   udao= new UsuarioDAO() ;
	ArrayList<Venda> lista= new ArrayList<Venda>();
		try {
			c.conectar();
			Statement instrucao= c.getConexao().createStatement();
			ResultSet result= instrucao.executeQuery(TodasVendas);
			
			//DESMONTANDO O RESULT SET
			while(result.next()) {
				   v = new Venda(result.getString("datavenda"),result.getString("horavenda"),result.getInt("qntproduto"),result.getInt("fk_produto"));
	                cli= cliente.buscar(result.getString("fk_cliente"));
	                v.setFk_cliente(cli);
	                user= udao.buscarUsuario(result.getInt("fk_usuario"));
		            v.setFk_usuario(user);

	        		cli.setVenda(lista);
				
				lista.add(v);
			}
		
				c.desconectar();
		}catch(Exception e) {
			System.out.println("ERRO EM EMITIR RELATORIO VENDA:"+ e.getMessage());
		}

		return lista;
	}
   
  
   public Venda buscarVendaPorUser(int usuario) {
	   Venda v=null;
	   Cliente cli;
       Usuario user;
       cliente= new ClienteDAO();
	  udao= new UsuarioDAO() ;
	   try {
	    	  c.conectar();
	    	  PreparedStatement instrucao= c.getConexao().prepareStatement(BuscarVendaPorUser);
			  instrucao.setInt(1,usuario);

			  ResultSet result= instrucao.executeQuery();
			  if(result.next()) {
				v= new Venda(result.getString("datavenda"),result.getString("horavenda"),result.getInt("qntproduto"),result.getInt("fk_produto"));
				cli= cliente.buscar(result.getString("fk_cliente"));
	            v.setFk_cliente(cli);
	            user= udao.buscarUsuario(result.getInt("fk_usuario"));
	            v.setFk_usuario(user);
                
			  }
	    	  
	    	  c.desconectar();
		  }catch(Exception e) {
				 System.out.println("erro na BUSCA VENDA POR PESSOA " + e.getMessage());
			 }
	   return v;
   }
   public ArrayList<Venda> buscarVendaPorU(int usuario) {
	   Venda v=null;
	   Cliente cli;
       Usuario user;
       ArrayList<Venda>ListaVenda=new ArrayList<Venda>();
       cliente= new ClienteDAO();
	  udao= new UsuarioDAO() ;
	   try {
	    	  c.conectar();
	    	  PreparedStatement instrucao= c.getConexao().prepareStatement(BuscarVendaPorUser);
			  instrucao.setInt(1,usuario);

			  ResultSet result= instrucao.executeQuery();
			  if(result.next()) {
				v= new Venda(result.getString("datavenda"),result.getString("horavenda"),result.getInt("qntproduto"),result.getInt("fk_produto"));
				cli= cliente.buscar(result.getString("fk_cliente"));
	            v.setFk_cliente(cli);
	            user= udao.buscarUsuario(result.getInt("fk_usuario"));
	            v.setFk_usuario(user);
                ListaVenda.add(v);
			  }
	    	  
	    	  c.desconectar();
		  }catch(Exception e) {
				 System.out.println("erro na BUSCA VENDA POR PESSOA " + e.getMessage());
			 }
	   return ListaVenda;
   }
   
   public void excluirVendaPoruUser(int id) {
	      try {
	    	  c.conectar();
	    	  PreparedStatement instrucao= c.getConexao().prepareStatement(ExcluirUser);
				 instrucao.setInt(1,id);
				 instrucao.execute();
	    	  
	    	  
	    	  c.desconectar();
		  }catch(Exception e) {
				 System.out.println("erro na Exclusão de VENDA por USER: " + e.getMessage());
			 }
		 }
   
   
}
