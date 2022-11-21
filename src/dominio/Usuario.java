package dominio;

public class Usuario {

private int id;	
private	String nome_funcionario;
private	String endereco;
private	int telefone;
private	int n_carteira;
private	int fk_venda;
private int fk_cliente; 

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNome_funcionario() {
	return nome_funcionario;
}
public void setNome_funcionario(String nome_funcionario) {
	this.nome_funcionario = nome_funcionario;
}
public String getEndereco() {
	return endereco;
}
public void setEndereco(String endereco) {
	this.endereco = endereco;
}
public int getTelefone() {
	return telefone;
}
public void setTelefone(int telefone) {
	this.telefone = telefone;
}
public int getN_carteira() {
	return n_carteira;
}
public void setN_carteira(int n_carteira) {
	this.n_carteira = n_carteira;
}
public int getFk_venda() {
	return fk_venda;
}
public void setFk_venda(int fk_venda) {
	this.fk_venda = fk_venda;
}
public int getFk_cliente() {
	return fk_cliente;
}
public void setFk_cliente(int fk_cliente) {
	this.fk_cliente = fk_cliente;
}

	
	
}
