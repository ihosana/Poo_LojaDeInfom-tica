package dominio;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Venda {
private String data_venda;//data em que foi realizada a venda
private String horario_venda;//horario da venda realizada
private int qnt;
private int id;

private String fk_cliente;//chave estrangeira do cliente
private int fk_produto;//chave estrangeira do produto
private int fk_usuario;//cave estrangeira do usuario
//private ArrayList<Produto> p;
private ArrayList<Produto> p;


public Venda() {
	
}
public Venda(String data_venda,String hora_venda,int qnt,int fk_usuario,int fk_produto,String fk_cliente) {
	this.data_venda=data_venda;
	this.horario_venda=hora_venda;
	this.qnt=qnt;
	this.fk_usuario=fk_usuario;

	this.fk_produto=fk_produto;
	this.fk_cliente=fk_cliente;
}

public ArrayList<Produto> getP() {
	return p;
}

public void setP(ArrayList<Produto> p) {
	this.p = p;
}
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getQnt() {
	return qnt;
}

public void setQnt(int qnt) {
	this.qnt = qnt;
}

public void setFk_usuario(int fk_usuario) {
	this.fk_usuario=fk_usuario;
}
public int getFk_usuario() {
	return fk_usuario;
}
public String getData_venda() {
	return data_venda;
}
public void setData_venda(String data_venda) {
	this.data_venda=data_venda;
}
public String getHorario_venda() {
	return horario_venda;
}
public void setHorario_venda(String horario_venda) {
	this.horario_venda = horario_venda;
}
public String getFk_cliente() {
	return fk_cliente;
}
public void setFk_cliente(String fk_cliente) {
	this.fk_cliente = fk_cliente;
}
public int getFk_produto() {
	return fk_produto;
}
public void setFk_produto(int fk_produto) {
	this.fk_produto = fk_produto;
}

}
