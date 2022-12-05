package persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dominio.PessoaJuridica;

public class PessoaJuridicaDAO {
private Conexao c;
private String Inserir="INSERT INTO pessoajuridica VALUES (?,?);";
private String Buscar="Select*from pessoajuridica where cnpj=?";
private String Excluir="Delete from pessoajuridica WHERE cnpj=?";
private String AtualizarPJ ="UPDATE pessoajuridica set cnpj=? where cnpj=? ";

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


public PessoaJuridica buscarPessoaJ(String id) {
	 PessoaJuridica pj = null;
	 try {
		c.conectar();
		PreparedStatement instrucao= c.getConexao().prepareStatement(Buscar);
		instrucao.setString(1,id);
		ResultSet r = instrucao.executeQuery();
		if(r.next()) {
			pj= new PessoaJuridica(r.getString("cnpj"),r.getString("nome"));
		}
		c.desconectar();
		 
		 
	 }catch(Exception e) {
		 System.out.println("ERRO em BUSCAR PESSOAJURIDICA" + e.getMessage());
	 }
	 
	 return pj;
}
public void excluirPessoaJ(String cod) {
	 try {
		 c.conectar();
		 
		 PreparedStatement instrucao= c.getConexao().prepareStatement(Excluir);
			instrucao.setString(1, cod);
			instrucao.execute();
		 c.desconectar();
		 
	 }catch(Exception e) {
		 System.out.println("ERRO em EXCLUIR PESSOAJURIDICA" +e.getMessage());
	 }
}
public void atualizarPJ(String id_novo, String id_antigo) {
    try {
  	  c.conectar();
  	  PreparedStatement instrucao= c.getConexao().prepareStatement(AtualizarPJ);
			 instrucao.setString(1,id_novo);
			 instrucao.setString(2,id_antigo);
			 instrucao.execute();
  	  
  	  
  	  c.desconectar();
	  }catch(Exception e) {
			 System.out.println("erro na ATUALIZAÇÃO Do CLIENTE por PESSOAJURIDICA: " + e.getMessage());
		 }
	 }
}
