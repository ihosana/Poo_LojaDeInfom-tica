package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dominio.Cliente;
import dominio.Venda;

public class VendaDAO {
	private Conexao c;
	private String Inserir="Insert into Venda (datavenda,horavenda,qntProduto,fk_produto,fk_usuario,fk_cliente) VALUES (?,?,?,?,?,?); ";
	private String Buscar= " SELECT* from Venda where id=?";
	private String Deletar="Delete from Venda where id=?";
	private String ExcluirP="Delete from Venda where fk_cliente=? ";
	private String BuscarP= "SELECT*FROM venda where fk_cliente='?'"; 
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
			 instrucao.setInt(4,venda.getFk_produto());
			 instrucao.setInt(5,venda.getFk_usuario());
			 instrucao.setString(6,venda.getFk_cliente());
			 instrucao.execute();
		    }catch(Exception e) {
			   System.out.println("ERRO EM Cadastrar VENDAAAA"+ e.getMessage());
		    }
		   
		   c.desconectar();
		   
		   }
	 public Venda buscarVenda(int id) {
		  Venda v= null ;
		 try {
			 c.conectar();
			 PreparedStatement instrucao= c.getConexao().prepareStatement(Buscar);
			 instrucao.setInt(1,id);
			 
			 ResultSet result= instrucao.executeQuery();
			 if(result.next()) {
					v= new Venda(result.getString("datavenda"),result.getString("horavenda"),result.getInt("qntproduto"),result.getInt("fk_usuario"),result.getInt("fk_produto"),result.getString("fk_cliente"));
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
			 System.out.println("erro na Exclusão" + e.getMessage());
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
				 System.out.println("erro na Exclusão" + e.getMessage());
			 }
		 }
   public Venda buscarVendaPorPessoa(String pessoa) {
	   Venda v=null;
	   try {
	    	  c.conectar();
	    	  PreparedStatement instrucao= c.getConexao().prepareStatement(BuscarP);
			  instrucao.setString(1,pessoa);

			  ResultSet result= instrucao.executeQuery();
			  if(result.next()) {
				v= new Venda(result.getString("datavenda"),result.getString("horavenda"),result.getInt("qntproduto"),result.getInt("fk_usuario"),result.getInt("fk_produto"),result.getString("fk_cliente"));
			  }
	    	  
	    	  c.desconectar();
		  }catch(Exception e) {
				 System.out.println("erro na EXCLUSÃO VENDA" + e.getMessage());
			 }
	   return v;
   }

}
