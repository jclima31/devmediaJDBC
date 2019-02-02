package br.com.jdbc.editora.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.jdbc.editora.model.Editora;

public class EditoraMapper implements RowMapper<Editora>{

	/*Modelo de objeto para ser preenchido e retornado nas consultas*/
	@Override
	public Editora mapRow(ResultSet rs, int rowNum) throws SQLException {
		Editora editora = new Editora();
		editora.setId(rs.getInt("ID_EDITORA"));
		editora.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));
		editora.setCidade(rs.getString("CIDADE"));
		editora.setEmail(rs.getString("EMAIL"));
		return editora;
	}
	
	public class CidadeAndEmailMapper implements RowMapper<Editora>{
		
		public Editora mapRow(ResultSet rs, int rowNum) throws SQLException {
			Editora editora = new Editora();
			editora.setCidade(rs.getString("CIDADE"));
			editora.setEmail(rs.getString("EMAIL"));
			return editora;
		}
	}
	

}
