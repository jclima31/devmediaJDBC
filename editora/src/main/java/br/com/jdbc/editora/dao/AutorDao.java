package br.com.jdbc.editora.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.com.jdbc.editora.dao.mapper.AutorMapper;
import br.com.jdbc.editora.model.Autor;

@Repository
@PropertySource("classpath:sql/autor.xml")
public class AutorDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert;
	@Autowired
	private EditoraDao editoraDao;
	
	@Value("${sql.autor.findAll}")
	private String SQL_FIND_ALL;
	
	@Value("${sql.autor.findAutoresBy.editora}")
	private String SQL_FIND_AUTORES_BY_EDITORA;
	
	@Value("${sql.autor.getIdByNome}")
	private String SQL_GET_ID_BY_NOME;
	
	public Integer getIdByNome(String nome) {
		
		return jdbcTemplate.queryForObject(SQL_GET_ID_BY_NOME, Integer.class, nome);
	}
	public List<Autor> findAutoresByEditora(String razaoSocial){
		
		return jdbcTemplate.query(SQL_FIND_AUTORES_BY_EDITORA, new AutorMapper().new AutorWithEditoraMapper(), razaoSocial);
	}
	
	public List<Autor> findAll(){
		
		return jdbcTemplate.query(SQL_FIND_ALL, new AutorMapper(editoraDao));
	}
	
	public Autor Save(Autor autor){
		SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("NOME", autor.getNome())
				.addValue("EMAIL", autor.getEmail())
				.addValue("ID_EDITORA", autor.getEditora().getId());
		Number key = simpleJdbcInsert.executeAndReturnKey(parameterSource);
		
		autor.setId(key.intValue());
		
		return autor;
	}
	
	@Autowired
	private void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("AUTORES")
				.usingColumns("NOME", "EMAIL", "ID_EDITORAS")
				.usingGeneratedKeyColumns("ID_AUTOR");
	}

	
}
