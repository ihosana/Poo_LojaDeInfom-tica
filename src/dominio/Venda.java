package dominio;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Venda {
private String data_venda;//data em que foi realizada a venda
private String horario_venda;//horario da venda realizada
private int qnt;
private Cliente fk_cliente;//chave estrangeira do cliente
private int fk_produto;
private Usuario fk_usuario;//cave estrangeira do usuario

private ArrayList<Produto> p;


public Venda() {
	
}
public Venda(String data_venda,String hora_venda,int qnt,int fk_produto) {
	this.data_venda=data_venda;
	this.horario_venda=hora_venda;
	this.qnt=qnt;
    this.fk_produto=fk_produto;
}


public ArrayList<Produto> getP() {
	return p;
}

public void setP(ArrayList<Produto> p) {
	this.p = p;
}

public int getQnt() {
	return qnt;
}

public void setQnt(int qnt) {
	this.qnt = qnt;
}

public void setFk_usuario(Usuario fk_usuario) {
	this.fk_usuario=fk_usuario;
}
public Usuario getFk_usuario() {
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
public Cliente getFk_cliente() {
	return fk_cliente;
}
public void setFk_cliente(Cliente fk_cliente) {
	this.fk_cliente = fk_cliente;
}
public int getFk_produto() {
	return fk_produto;
}
public void setFk_produto(int fk_produto) {
	this.fk_produto = fk_produto;
}

}
