package persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
public class Conexao {
	private String usuario;
	private String senha;
	private String caminho;
	private Connection conect; 

	public Conexao(String caminho,String usuario, String senha) {
		this.caminho=caminho;
		this.usuario=usuario;
		this.senha=senha;
		
	}

	public void conectar() {
		try{
			Class.forName("org.postgresql.Driver");
			conect=DriverManager.getConnection(caminho,usuario,senha);
		}catch(Exception e) {
			System.out.println("ERRO NA CONEXÃO");
		}
	}
	public void desconectar() {
		try {
			conect.close();
		}catch(Exception a) {
			System.out.println("ERRO NA DESCONEXÃO");
		}
	}

	public Connection getConexao() {
		return conect;
	}
	}


