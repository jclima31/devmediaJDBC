package br.com.jdbc.editora.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.com.jdbc.editora.model.LivroAutor;

@Repository
public class LivroAutorDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public LivroAutorDao(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public LivroAutor save(LivroAutor livroAutor){
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate) //Inserindo dados
			.withTableName("LIVRO_AUTORES") //na tabela X
			.usingColumns("ID_LIVRO", "ID_AUTOR") //Usando as colunas 
			.usingColumns("ID_LIVRO_AUTOR");
		
		Number key = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(livroAutor)); //retorna o indice q foi salvo no banco
		
		livroAutor.setIdAutor(key.intValue());
		
		return livroAutor;
	}
	
	
}
