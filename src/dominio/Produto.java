package dominio;

public class Produto {
private int id;//chave primaria
private int codigoProduto;// codigo do produto
private String valorProduto;//preço do produto
private String Categoria;//A categoria em que se enquadra o produto

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCodigoProduto() {
	return codigoProduto;
}
public void setCodigoProduto(int codigoProduto) {
	this.codigoProduto = codigoProduto;
}
public String getValorProduto() {
	return valorProduto;
}
public void setValorProduto(String valorProduto) {
	this.valorProduto = valorProduto;
}
public String getCategoria() {
	return Categoria;
}
public void setCategoria(String categoria) {
	Categoria = categoria;
}

	
}
