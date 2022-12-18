package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.Statement;
import dominio.Cliente;
import dominio.Usuario;
import dominio.Venda;

public class UsuarioDAO {

	private Conexao c;
	private String InserirUsuario="INSERT INTO Usuario(id,nomefuncionario,endereco,telefone,numerocarteira) VALUES (?,?,?,?,?) ";
	private String BuscarUsuario= "SELECT*FROM Usuario where id=? ";
	private String ExcluirUsuario="DELETE FROM Usuario WHERE id=?";
	private String BuscarTodosUser="SELECT*FROM usuario";
	

public UsuarioDAO() {
	 c=new Conexao("jdbc:postgresql://127.0.0.1:5432/POO_projeto","postgres","ihosanaassis");
}

public Usuario buscarUsuario(int id) {
	Usuario u=null;
	try {
	
		c.conectar();
		PreparedStatement instrucao= c.getConexao().prepareStatement(BuscarUsuario);
		instrucao.setInt(1,id);
		
		ResultSet r=instrucao.executeQuery();
		
		if(r.next()) {
			
			u=new Usuario(r.getInt("id"),r.getString("nomefuncionario"),r.getString("endereco"),r.getInt("telefone"),r.getInt("numerocarteira")) ;
		}
		
		
		c.desconectar();
	}catch(Exception e) {
		System.out.println("Erro na busca do USUARIO"+e.getMessage());
	}
	return u;
}
public void incluirUsuario(Usuario usuario) {
	try {
	 	c.conectar();
		 PreparedStatement instrucao =c.getConexao().prepareStatement(InserirUsuario);
		 instrucao.setInt(1,usuario.getId());
		 instrucao.setString(2,usuario.getNome_funcionario());
		 instrucao.setString(3,usuario.getEndereco());
		 instrucao.setInt(4,usuario.getTelefone());
		 instrucao.setInt(5,usuario.getN_carteira());
		 instrucao.execute();
	    }catch(Exception e) {
		   System.out.println("ERRO EM INCLUIR funcionario : "+ e.getMessage());
	    }
	   
	   c.desconectar();
	   
	   }
public void excluirUsuario(int id) {
    try {
    	c.conectar();
    	PreparedStatement instrucao =c.getConexao().prepareStatement(ExcluirUsuario); 
        instrucao.setInt(1, id);	
        instrucao.execute();
    	c.desconectar();
    	
    }catch(Exception e) {
    	System.out.println("ERRO em EXCLUIR USUARIO" +e.getMessage());
    }
}

public ArrayList<Usuario> buscarTodos() {
	  Usuario u= null ;
	  ArrayList<Usuario>lista = new ArrayList<Usuario>();
	 try {
		 c.conectar();
		    Statement instrucao= c.getConexao().createStatement();
			ResultSet result= instrucao.executeQuery(BuscarTodosUser);
			
			//DESMONTANDO O RESULT SET
			while(result.next()) {
				   u = new Usuario(result.getInt("id"),result.getString("nomefuncionario"),result.getString("endereco"),result.getInt("telefone"),result.getInt("numerocarteira"));
				  lista.add(u);
			}
		 
		 c.desconectar();
		
		 
	 }catch(Exception e) {
		 System.out.println("erro na BUSCA dos TODOS OS FUNCIONARIOS" + e.getMessage());
	 }
	 return lista;
}


}
