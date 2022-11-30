package dominio;

public class PessoaJuridica extends Cliente {
private String nome_social; // nome da pessoa Juridica
private String cnpj; //CNPJ da pessoa Juridica

public PessoaJuridica() {
	
}
public String getNome_social() {
	return nome_social;
}
public void setNome_social(String nome_social) {
	this.nome_social = nome_social;
}
public String getCnpj() {
	return cnpj;
}
public void setCnpj(String cnpj) {
	this.cnpj = cnpj;
}
//public void setEndereco(String endereco) {
//	super.setEndereco(endereco);
//}
//public void setTelefone(int telefone) {
//	super.setTelefone(telefone);
//}


}
