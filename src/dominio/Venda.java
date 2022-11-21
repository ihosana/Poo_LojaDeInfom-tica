package dominio;

public class Venda {
private int id;//chave primaria 
private String data_venda;//data em que foi realizada a venda
private String horario_venda;//horario da venda realizada
private int fk_cliente;//chave estrangeira do cliente
private int fk_produto;//chave estrangeira do cliente



public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getData_venda() {
	return data_venda;
}
public void setData_venda(String data_venda) {
	this.data_venda = data_venda;
}
public String getHorario_venda() {
	return horario_venda;
}
public void setHorario_venda(String horario_venda) {
	this.horario_venda = horario_venda;
}
public int getFk_cliente() {
	return fk_cliente;
}
public void setFk_cliente(int fk_cliente) {
	this.fk_cliente = fk_cliente;
}
public int getFk_produto() {
	return fk_produto;
}
public void setFk_produto(int fk_produto) {
	this.fk_produto = fk_produto;
}

}
