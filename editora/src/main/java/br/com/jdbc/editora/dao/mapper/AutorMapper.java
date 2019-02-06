package br.com.jdbc.editora.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.jdbc.editora.dao.EditoraDao;
import br.com.jdbc.editora.model.Autor;
import br.com.jdbc.editora.model.Editora;

public class AutorMapper implements RowMapper<Autor>{

	private EditoraDao editoraDao;
	
	public AutorMapper(EditoraDao editoraDao) {
		this.editoraDao = editoraDao;
	}
	
	@Override
	public Autor mapRow(ResultSet rs, int arg1) throws SQLException {
		Autor autor = new Autor();
		autor.setId(rs.getInt("ID_AUTOR"));
		autor.setNome(rs.getString("NOME"));
		autor.setEmail(rs.getString("EMAIL"));
 		
		Integer idEditora = rs.getInt("ID_EDITORA");
		Editora editora = editoraDao.findById(idEditora);
		autor.setEditora(editora);
		return autor;
	}
	

}
