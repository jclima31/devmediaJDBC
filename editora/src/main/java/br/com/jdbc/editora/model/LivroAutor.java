package br.com.jdbc.editora.model;

public class LivroAutor {
	
	private Integer livroAutor;
	private Integer idLivro;
	private Integer idAutor;
	
	public LivroAutor(){};
	
	public LivroAutor(Integer idLivro, Integer idAutor){
		this.idAutor = idAutor;
		this.idLivro = idLivro;
	};
	
	public Integer getLivroAutor() {
		return livroAutor;
	}
	public void setLivroAutor(Integer livroAutor) {
		this.livroAutor = livroAutor;
	}
	public Integer getIdLivro() {
		return idLivro;
	}
	public void setIdLivro(Integer idLivro) {
		this.idLivro = idLivro;
	}
	public Integer getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(Integer idAutor) {
		this.idAutor = idAutor;
	}
	@Override
	public String toString() {
		return "LivroAutor [livroAutor=" + livroAutor + ", idLivro=" + idLivro + ", idAutor=" + idAutor + "]";
	}
	
	

}
