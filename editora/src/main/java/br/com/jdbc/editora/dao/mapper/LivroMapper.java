package br.com.jdbc.editora.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import br.com.jdbc.editora.model.Livro;

public class LivroMapper implements RowMapper<Livro> {

	@Override
	public Livro mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Livro livro = new Livro();
		livro.setId(rs.getInt("ID_LIVRO"));
		livro.setTitulo(rs.getString("TITULO"));
		livro.setEdicao(rs.getInt("EDICAO"));
		livro.setPaginas(rs.getInt("PAGINAS"));
		return livro;
	}

	

}
