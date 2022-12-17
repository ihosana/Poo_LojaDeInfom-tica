package dominio;

import java.util.ArrayList;

public class Cliente {
private String endereco;
private String id;
private int telefone;
private ArrayList<Venda> venda; 

public Cliente(String id, String endereco, int telefone){
	this.id=id;
	this.endereco=endereco;
	this.telefone=telefone;
	venda = new ArrayList<Venda>();
}
public int getQtdVenda() {
	return venda.size();
}
public int getTam() {
	 return venda.size();
}

public Cliente() {
	venda = new ArrayList<Venda>();
}
public ArrayList<Venda>getVenda(){
	return venda;
}
public void setVenda(ArrayList<Venda> venda) {
	this.venda = venda;
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
