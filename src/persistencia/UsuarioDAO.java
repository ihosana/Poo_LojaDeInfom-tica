package persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dominio.Usuario;

public class UsuarioDAO {

	private Conexao c;
	private String InserirUsuario="INSERT INTO Produto(codigoproduto,nome_produto,valorproduto, qntvendidas, categoria) VALUES (?,?,?) ";
	private String BuscarUsuario= "SELECT*FROM Usuario where id=?";
	
	

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
			
			u=new Usuario(r.getString("nomefuncionario"),r.getString("endereco"),r.getInt("telefone"),r.getInt("numerocarteira")) ;
		}
		
		
		c.desconectar();
	}catch(Exception e) {
		System.out.println("Erro na busca do USUARIO"+e.getMessage());
	}
	return u;
}

	
	
}
