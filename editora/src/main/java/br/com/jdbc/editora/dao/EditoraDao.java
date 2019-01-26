package br.com.jdbc.editora.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.com.jdbc.editora.dao.mapper.EditoraMapper;
import br.com.jdbc.editora.model.Editora;

@Repository
@PropertySource("classpath:sql/editora.xml")
public class EditoraDao {
	
	@Value("${sql.insert}")
	private String SQL_INSERT;
	
	@Value("${sql.findBy.razaosocial}")
	private String SQL_FIND_BY_RAZAO_SOCIAL;
	
	@Value("${sql.findBy.cidades}")
	private String SQL_FIND_BY_CIDADES;
	
	@Value("${sql.findBy.id}")
	private String SQL_FIND_BY_ID;
	
	@Value("${sql.find.All}")
	private String SQL_FIND_ALL;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Editora> findByRazaoSocial(String rz){
		
		return jdbcTemplate.query(
				SQL_FIND_BY_RAZAO_SOCIAL, 
				new String[]{rz}, 
				new EditoraMapper());
	}
	
	public List<Editora> findByCidades(String c1, String c2){
		
		return jdbcTemplate.query(
				SQL_FIND_BY_CIDADES, 
				new EditoraMapper(), 
				c1, 
				c2);
	}
	
	public Editora findById(int id){
		
		return jdbcTemplate.queryForObject(
				SQL_FIND_BY_ID, 
				new EditoraMapper(), 
				id);
		
	}
	
	public List<Editora> findAll(){
		
		return jdbcTemplate.query(
				SQL_FIND_ALL, 
				new EditoraMapper());
	}
	
	public Editora add(Editora editora){
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.setTableName("EDITORAS");
		insert.setColumnNames(Arrays.asList("RAZAO_SOCIAL", "CIDADE", "EMAIL"));
		insert.setGeneratedKeyName("ID_EDITORA");
		
		Number key = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(editora));
		editora.setId(key.intValue());
		return editora;
	}
	
	public int save(Editora editora){
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.setTableName("EDITORAS");
		insert.setColumnNames(Arrays.asList("RAZAO_SOCIAL", "CIDADE", "EMAIL"));
		insert.setGeneratedKeyName("ID_EDITORA");
		
		Number key = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(editora));
		return key.intValue();
	}
	
	public int insert(Editora editora){
		
		return jdbcTemplate.update(
				SQL_INSERT, 
				editora.getRazaoSocial(), 
				editora.getCidade(), 
				editora.getEmail());
	}
}
