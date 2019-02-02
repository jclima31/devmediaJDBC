package br.com.jdbc.editora.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LivroDao {

	private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS LIVROS("
			+ "ID_LIVRO INT (11) PRIMARY KEY, "
			+ "TITULO VARCHAR(45) NOT NULL,"
			+ "AUTOR VARCHAR(45) NOT NULL)";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public LivroDao(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
		this.jdbcTemplate.execute(CREATE_TABLE);
	}
	
	public void insert(){
		String sql = "INSERT INTO LIVROS (ID_LIVRO, TITULO, AUTOR) VALUES (200, 'java', 'Oracle') ";
		jdbcTemplate.execute(sql);
	}
	
}
