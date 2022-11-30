package persistencia;

import java.sql.PreparedStatement;

import dominio.Cliente;
import dominio.Venda;

public class VendaDAO {
	private Conexao c;
	private String Inserir="Insert into Venda (id,datavenda,horavenda,qntProduto,fk_produto,fk_usuario,fk_cliente) VALUES (?,?,?,?,?,?,?); ";
	private String Buscar= " SELECT* from Cliente where Cliente.id=?";


	public VendaDAO() {
		 c=new Conexao("jdbc:postgresql://127.0.0.1:5432/POO_projeto","postgres","ihosanaassis");
	}
	 public void inserirVenda(Venda venda){
		   try {
		 	c.conectar();
			 PreparedStatement instrucao =c.getConexao().prepareStatement(Inserir);
			 instrucao.setInt(1,venda.getId());
			 instrucao.setString(2,venda.getData_venda());
			 instrucao.setString(3,venda.getHorario_venda());
			 instrucao.setInt(4,venda.getQnt());
			 instrucao.setInt(5,venda.getFk_produto());
			 instrucao.setInt(6,venda.getFk_usuario());
			 instrucao.setString(7,venda.getFk_cliente());
			 instrucao.execute();
		    }catch(Exception e) {
			   System.out.println("ERRO EM Cadastrar VENDAAAA"+ e.getMessage());
		    }
		   
		   c.desconectar();
		   
		   }
}
