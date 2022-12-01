package dominio;

public class Produto {
private int codigoProduto;// codigo do produto
private String nomeProduto;//o nome do produto
private Float valorProduto;//preço do produto
private String categoria;//A categoria em que se enquadra o produto

public Produto(int codigoproduto,String nomeproduto,Float valorproduto,String categoria ) {
	this.codigoProduto=codigoproduto;
	this.nomeProduto=nomeproduto;
	this.valorProduto=valorproduto;
	this.categoria=categoria;
}
public Produto() {
	
}
public String getNomeProduto() {
	return nomeProduto;
}
public void setNomeProduto(String nomeProduto) {
	this.nomeProduto = nomeProduto;
}
public int getCodigoProduto() {
	return codigoProduto;
}
public void setCodigoProduto(int codigoProduto) {
	this.codigoProduto = codigoProduto;
}
public Float getValorProduto() {
	return valorProduto;
}
public void setValorProduto(Float valorProduto) {
	this.valorProduto = valorProduto;
}
public String getCategoria() {
	return categoria;
}
public void setCategoria(String categoria) {
	this.categoria = categoria;
}

	
}
