package dominio;

import java.util.ArrayList;

public class PessoaFisica extends Cliente {
private String nome;//Nome da Pessoa Física
private String cpf;

public PessoaFisica(String cpf, String nome) {
	this.cpf=cpf;
	this.nome=nome;
}
public PessoaFisica() {
	
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getCpf() {
	return cpf;
}
public void setCpf(String cpf) {
	this.cpf = cpf;
}
//public void setEndereco(String endereco) {
//	super.setEndereco(endereco);
//}
//public void setTelefone(int telefone) {
//	super.setTelefone(telefone);
//}




}
