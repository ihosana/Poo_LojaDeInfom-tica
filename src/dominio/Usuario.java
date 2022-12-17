package dominio;

public class Usuario {

private	String nome_funcionario;
private	String endereco;
private	int telefone;
private	int n_carteira;
private int id;

public Usuario(int id,String nome_funcionario, String endereco, int telefone, int n_carteira){
	this.id=id;
	this.nome_funcionario=nome_funcionario;
	this.endereco=endereco;
	this.telefone=telefone;
	this.n_carteira=n_carteira;
}

public Usuario() {
	
}

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
	
}
