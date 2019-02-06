package br.com.jdbc.editora.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.jdbc.editora.dao.EditoraDao;
import br.com.jdbc.editora.model.Autor;
import br.com.jdbc.editora.model.Editora;

public class AutorMapper implements RowMapper<Autor>{

	private EditoraDao editoraDao;
	public AutorMapper(){
		super();
	}
	
	public AutorMapper(EditoraDao editoraDao) {
		this.editoraDao = editoraDao;
	}
	
	@Override
	public Autor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Autor autor = new Autor();
		autor.setId(rs.getInt("ID_AUTOR"));
		autor.setNome(rs.getString("NOME"));
		autor.setEmail(rs.getString("EMAIL"));
 		
		Integer idEditora = rs.getInt("ID_EDITORA");
		Editora editora = editoraDao.findById(idEditora);
		autor.setEditora(editora);
		return autor;
	}
	
	public class AutorWithEditoraMapper implements RowMapper<Autor>{

		@Override
		public Autor mapRow(ResultSet rs, int rowNum) throws SQLException {
			Autor autor = new Autor();
			autor.setId(rs.getInt("ID_AUTOR"));
			autor.setNome(rs.getString("NOME"));
			autor.setEmail(rs.getString("EMAIL"));
			
			Editora editora = new Editora();
			editora.setId(rs.getInt("ED_ID_EDITORA"));
			editora.setRazaoSocial("RAZAO_SOCIAL");
			editora.setEmail("ED_EMAIL");
			editora.setCidade("CIDADE");
			
			autor.setEditora(editora);
			return autor;
		}
		
	}
	
	
	

}
