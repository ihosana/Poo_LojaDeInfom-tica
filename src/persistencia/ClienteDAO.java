package persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dominio.Cliente;


public class ClienteDAO {
private Conexao c;
private Cliente cliente;
private String Inserir="INSERT INTO Cliente(endereco,telefone) VALUES (?,?) ";


  public void INSERIR(int endereco, int telefone){
   try {
 	c.conectar();
	 PreparedStatement instrucao =c.getConexao().prepareStatement(Inserir);
	 instrucao.setString(endereco,cliente.getEndereco());
	 instrucao.setString(telefone,cliente.getTelefone());
	 
    }catch(Exception e) {
	   System.out.println("ERRO EM INSERIR"+ e.getMessage());
    }
   
   
   
   }


}
