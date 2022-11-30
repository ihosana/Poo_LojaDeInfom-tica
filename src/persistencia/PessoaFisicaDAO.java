package persistencia;
import java.sql.PreparedStatement;
import dominio.PessoaFisica;


public class PessoaFisicaDAO {
 private String Inserir="INSERT INTO pessoafisica VALUES (?,?)";
 private Conexao c;	
	
 public PessoaFisicaDAO() {
	 c=new Conexao("jdbc:postgresql://127.0.0.1:5432/POO_projeto","postgres","ihosanaassis");
 }
 
 
  public void Cadastrar( PessoaFisica pf) {
    try {
	  c.conectar();
	  PreparedStatement instrucao =c.getConexao().prepareStatement(Inserir);
	  instrucao.setString(1,pf.getCpf());
	  instrucao.setString(2,pf.getNome());
	instrucao.execute();
	}catch(Exception e) {
	   System.out.println("ERRO EM Cadastrar cliente em pessoa fisica"+ e.getMessage());
	}
		
     c.desconectar();
  }
  
}
