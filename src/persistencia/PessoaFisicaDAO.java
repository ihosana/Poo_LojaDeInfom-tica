package persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dominio.PessoaFisica;


public class PessoaFisicaDAO {
 private String Inserir="INSERT INTO pessoafisica(cpf,nome) VALUES (?,?)";
 private Conexao c;	
 private String Buscar="Select*from pessoafisica where cpf=?";
 private String Excluir="Delete from pessoafisica WHERE cpf=?";
	
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
 public PessoaFisica buscarPessoaF(String id) {
	 PessoaFisica pf = null;
	 try {
		c.conectar();
		PreparedStatement instrucao= c.getConexao().prepareStatement(Buscar);
		instrucao.setString(1,id);
		ResultSet r = instrucao.executeQuery();
		if(r.next()) {
			pf= new PessoaFisica(r.getString("cpf"),r.getString("nome"));
		}
		c.desconectar();
		 
		 
	 }catch(Exception e) {
		 System.out.println("ERRO em BUSCAR PESSOAFISICA" + e.getMessage());
	 }
	 
	 return pf;
 }
 public void excluirPessoaF(String cod) {
	 try {
		 c.conectar();
		 
		 PreparedStatement instrucao= c.getConexao().prepareStatement(Excluir);
			instrucao.setString(1, cod);
			instrucao.execute();
		 c.desconectar();
		 
	 }catch(Exception e) {
		 System.out.println("ERRO em EXCLUIR PESSOAFISICA" +e.getMessage());
	 }
 }
}
