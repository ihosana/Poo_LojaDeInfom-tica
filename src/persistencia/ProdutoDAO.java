package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Produto;
import dominio.Venda;

public class ProdutoDAO {
	private Conexao c;
	private String InserirProduto="INSERT INTO Produto(codigoproduto,nome_produto,valorproduto, categoria) VALUES (?,?,?,?) ";
	private String BuscarProduto= "SELECT*FROM Produto where codigoProduto=?";
	private String AlterarProduto="UPDATE Produto SET valorproduto=? WHERE codigoproduto=? ";
	private String Excluir ="delete from produto where codigoproduto=?";
	private String TodosProduto="SELECT*FROM Produto";
  
public ProdutoDAO() {
	 c=new Conexao("jdbc:postgresql://127.0.0.1:5432/POO_projeto","postgres","ihosanaassis");
}
	
	public Produto buscarProduto(int id) {
		Produto p1=null;
		try {
		
			c.conectar();
			PreparedStatement instrucao= c.getConexao().prepareStatement(BuscarProduto);
			instrucao.setInt(1,id);
			
			ResultSet r=instrucao.executeQuery();
			
			if(r.next()) {
				
				p1=new Produto(r.getInt("codigoproduto"),r.getString("nome_produto"),r.getFloat("valorproduto"),r.getString("categoria")) ;
			}
			
			
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na busca do PRODUTO"+e.getMessage());
		}
		return p1;
	}
	public void inserir(Produto produto) {
		try {
			c.conectar();
	         PreparedStatement instrucao=c.getConexao().prepareStatement(InserirProduto);	
             instrucao.setInt(1,produto.getCodigoProduto());
             instrucao.setString(2,produto.getNomeProduto());
             instrucao.setFloat(3,produto.getValorProduto());
			 instrucao.setString(4,produto.getCategoria());
			 instrucao.execute();
			
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na INSERÇÃO "+e.getMessage());
		}
	}
	
	public void alterar(Float v, int cod) {
		try {
			c.conectar();
			
			PreparedStatement instrucao= c.getConexao().prepareStatement(AlterarProduto);
			instrucao.setFloat(1,v);
			instrucao.setInt(2,cod);
			instrucao.execute();
			c.desconectar();
			
		}catch(Exception e) {
			System.out.println("Erro em ALTERAR o produto"+e.getMessage());
		}
	}
	
	public void excluirProduto(int cod) {
		try {
			c.conectar();
			PreparedStatement instrucao= c.getConexao().prepareStatement(Excluir);
			instrucao.setInt(1, cod);
			instrucao.execute();
			
			c.desconectar();
			
		}catch(Exception q) {
		System.out.println("ERRO em EXCLUIR produto" +q.getMessage());
			
		}
	}
	  public ArrayList<Produto> emitirProduto() {
		  Produto p ;
		ArrayList<Produto> lista= new ArrayList<Produto>();
			try {
				c.conectar();
				Statement instrucao= c.getConexao().createStatement();
				ResultSet r= instrucao.executeQuery(TodosProduto);
				
				//DESMONTANDO O RESULT SET
				while(r.next()) {
					p=new Produto(r.getInt("codigoproduto"),r.getString("nome_produto"),r.getFloat("valorproduto"),r.getString("categoria")) ;
				lista.add(p);
				}
			
					c.desconectar();
			}catch(Exception e) {
				System.out.println("ERRO EM EMITIR RELATORIO PRODUTO:"+ e.getMessage());
			}

			return lista;
		}
	
	
	
	
	
	
	
	
	
	

}
