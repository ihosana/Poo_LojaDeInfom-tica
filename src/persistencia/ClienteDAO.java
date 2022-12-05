package persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Cliente;

public class ClienteDAO {
private Conexao c;
private String Inserir="INSERT INTO Cliente(id,endereco,telefone) VALUES (?,?,?) ";
private String Buscar= " SELECT* from Cliente where id=? ";
private String Excluir=" Delete from Cliente WHERE id=? ";
private String AtualizarCliente ="UPDATE cliente set id=? where id=? ";


//ALTERAR CPF/CNPJ

public ClienteDAO() {
	 c=new Conexao("jdbc:postgresql://127.0.0.1:5432/POO_projeto","postgres","ihosanaassis");
}

  public void cadastrar(Cliente cliente){
   try {
 	c.conectar();
	 PreparedStatement instrucao =c.getConexao().prepareStatement(Inserir);
	 instrucao.setString(1,cliente.getId());
	 instrucao.setString(2,cliente.getEndereco());
	 instrucao.setInt(3,cliente.getTelefone());
	 instrucao.execute();
    }catch(Exception e) {
	   System.out.println("ERRO EM Cadastrar cliente"+ e.getMessage());
    }
   
   c.desconectar();
   
   }
  public Cliente buscar(String id){
	  Cliente cliente= null;
	  try {
		  c.conectar();
		  PreparedStatement instrucao= c.getConexao().prepareStatement(Buscar);
		  instrucao.setString(1, id);
		  ResultSet r=instrucao.executeQuery();
		  
		  if(r.next()){
			  cliente= new Cliente(r.getString("id"),r.getString("endereco"),r.getInt("telefone"));
		  }
		  c.desconectar();
		  
	  }catch(Exception e) {
		  System.out.println("ERRO em BUSCAR" +e.getMessage());
	  }
	  return cliente;
  }
  public void excluirCliente(String id) {
	  try {
		  c.conectar();
		  PreparedStatement instrucao= c.getConexao().prepareStatement(Excluir);
		  instrucao.setString(1, id);
		  instrucao.execute();
		  c.desconectar();
	  }catch(Exception e) {
		  System.out.println("Erro em EXCLUIR CLIENTE "+e.getMessage());
	  }
  }
  public void atualizarCliente(String id_novo, String id_antigo) {
      try {
    	  c.conectar();
    	  PreparedStatement instrucao= c.getConexao().prepareStatement(AtualizarCliente);
			 instrucao.setString(1,id_novo);
			 instrucao.setString(2,id_antigo);
			 instrucao.execute();
    	  
    	  
    	  c.desconectar();
	  }catch(Exception e) {
			 System.out.println("erro na ATUALIZAÇÃO Do CLIENTE por PESSOA: " + e.getMessage());
		 }
	 }
  
//  public Cliente buscar( int id) {
//	  Cliente cliente;
//	  try {
//		  c.conectar();
//		  PreparedStatement Ps= c.getConexao().prepareStatement(Buscar);
//		  Ps.setInt(1,id);
//		  ResultSet result= Ps.executeQuery();
//		  if(result.next()) {
//				cliente= new Cliente(result.getInt("id"),result.getString("endereco"),result.getInt("telefone"));
//			}
//			
//		  
//		  c.desconectar();
//	  }catch(Exception e) {
//		  System.out.println("erro em buscar"+ e.getMessage());
//	  }
//	  
//	  return cliente;
//  }
//BUSCAR POR CNPJ OU CPF
  //BUSCAR POR NOME DO CLIENTE

}
