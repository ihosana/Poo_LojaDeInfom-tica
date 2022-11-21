package dominio;

public class PessoaJuridica {
private int id;//id SERIAL PRIMARY KEY REFERENCES Cliente (id)
private String nome_social; // nome da pessoa Juridica
private int cnpj; //CNPJ da pessoa Juridica

public PessoaJuridica() {
	
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNome_social() {
	return nome_social;
}
public void setNome_social(String nome_social) {
	this.nome_social = nome_social;
}
public int getCnpj() {
	return cnpj;
}
public void setCnpj(int cnpj) {
	this.cnpj = cnpj;
}




}
