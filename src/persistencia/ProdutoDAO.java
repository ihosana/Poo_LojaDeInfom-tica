package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dominio.Produto;

public class ProdutoDAO {
	private Conexao c;
	private String InserirProduto="INSERT INTO Produto(codigoproduto,nome_produto,valorproduto, categoria) VALUES (?,?,?,?) ";
	private String BuscarProduto= "SELECT*FROM Produto where codigoProduto=?";
	
	

public ProdutoDAO() {
	 c=new Conexao("jdbc:postgresql://127.0.0.1:5432/POO_projeto","postgres","ihosanaassis");
}
	
	public Produto buscarProduto(int id) {
		Produto p=null;
		try {
		
			c.conectar();
			PreparedStatement instrucao= c.getConexao().prepareStatement(BuscarProduto);
			instrucao.setInt(1,id);
			
			ResultSet r=instrucao.executeQuery();
			
			if(r.next()) {
				
				p=new Produto(r.getInt("codigoproduto"),r.getString("nome_produto"),r.getFloat("valorproduto"),r.getString("categoria")) ;
			}
			
			
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na busca do PRODUTO"+e.getMessage());
		}
		return p;
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

}
