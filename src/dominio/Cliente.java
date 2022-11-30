package dominio;

import java.util.ArrayList;

public class Cliente {
private String endereco;
private String id;
private int telefone;
private ArrayList<PessoaFisica>Lista_pf;
private ArrayList<PessoaJuridica>Lista_pj;
public Cliente(String id, String endereco, int telefone){
	this.id=id;
	this.endereco=endereco;
	this.telefone=telefone;
}
public Cliente() {
	
}

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
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

}
