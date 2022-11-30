package persistencia;
import java.sql.PreparedStatement;
import dominio.PessoaJuridica;

public class PessoaJuridicaDAO {
private Conexao c;
private String Inserir="INSERT INTO pessoajuridica VALUES (?,?);";
private String Buscar="";

public PessoaJuridicaDAO() {
	 c=new Conexao("jdbc:postgresql://127.0.0.1:5432/POO_projeto","postgres","ihosanaassis");
}


public void Cadastrar( PessoaJuridica pj) {
    try {
	  c.conectar();
	  PreparedStatement instrucao =c.getConexao().prepareStatement(Inserir);
	  instrucao.setString(1,pj.getCnpj());
	  instrucao.setString(2,pj.getNome_social());
      instrucao.execute();
	}catch(Exception e) {
	   System.out.println("ERRO EM Cadastrar cliente em pessoa fisica"+ e.getMessage());
	}
		
     c.desconectar();
  }
}
