package br.com.jdbc.editora.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.jdbc.editora.model.Editora;

@Repository
public class EditoraDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insert(Editora editora){
		
		String sql = "INSERT INTO EDITORAS (RAZAO_SOCIAL, CIDADE, EMAIL) VALUES (?, ?, ?)";
		
		return jdbcTemplate.update(
				sql, 
				editora.getRazaoSocial(), 
				editora.getCidade(), 
				editora.getEmail());
	}
}
