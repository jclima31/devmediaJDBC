package br.com.jdbc.editora.model;

public class Editora {
	
	private Integer id;
	private String razaoSocial;
	private String cidade;
	private String email;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Editora [id=" + id + ", razaoSocial=" + razaoSocial + ", cidade=" + cidade + ", email=" + email + "]";
	}
	
}
