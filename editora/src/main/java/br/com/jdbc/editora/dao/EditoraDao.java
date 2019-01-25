package br.com.jdbc.editora.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.com.jdbc.editora.dao.mapper.EditoraMapper;
import br.com.jdbc.editora.model.Editora;

@Repository
public class EditoraDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Editora> findByRazaoSocial(String rz){
		
		String sql = "SELECT * FROM EDITORAS WHERE RAZAO_SOCIAL LIKE '%' ? '%'";
		
		return jdbcTemplate.query(sql, new String[]{rz}, new EditoraMapper());
	}
	
	public List<Editora> findByCidades(String c1, String c2){
		
		String sql = "SELECT * FROM EDITORAS WHERE CIDADE LIKE ? OR CIDADE LIKE ?";
		
		return jdbcTemplate.query(sql, new EditoraMapper(), c1, c2);
	}
	
	public Editora findById(int id){
		
		String sql = "SELECT * FROM EDITORAS WHERE ID_EDITORA = ?";
		
		return jdbcTemplate.queryForObject(sql, new EditoraMapper(), id);
		
	}
	
	public List<Editora> findAll(){
		
		String sql = "SELECT * FROM EDITORAS";
		
		return jdbcTemplate.query(sql, new EditoraMapper());
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
		
		String sql = "INSERT INTO EDITORAS (RAZAO_SOCIAL, CIDADE, EMAIL) VALUES (?, ?, ?)";
		
		return jdbcTemplate.update(
				sql, 
				editora.getRazaoSocial(), 
				editora.getCidade(), 
				editora.getEmail());
	}
}
