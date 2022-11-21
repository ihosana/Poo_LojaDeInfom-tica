package dominio;

public class PessoaFisica {
private int id;//id SERIAL PRIMARY KEY REFERENCES Cliente (id),
private String nome;//Nome da Pessoa Física
private int cpf;// CPF da pessoa física

public PessoaFisica() {
	
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public int getCpf() {
	return cpf;
}
public void setCpf(int cpf) {
	this.cpf = cpf;
}





}
