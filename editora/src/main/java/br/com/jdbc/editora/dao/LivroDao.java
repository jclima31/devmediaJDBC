package br.com.jdbc.editora.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.com.jdbc.editora.model.Autor;
import br.com.jdbc.editora.model.Editora;
import br.com.jdbc.editora.model.Livro;

@Repository
public class LivroDao {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${sql.livro.findLivroWithAutores}")
	private String SQL_FIND_LIVRO_WITH_AUTORES;
	
	public Livro findLivroWithAutores(int idLivro){
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL_FIND_LIVRO_WITH_AUTORES, idLivro);
		
		Livro livro = null;
		
		List<Autor> autores = new ArrayList<Autor>();
		
		for (Map row : rows) {
			
			if(livro == null){
				livro = new Livro();
				livro.setId((Integer) row.get("ID_LIVRO"));
				livro.setTitulo((String) row.get("TITULO"));
				livro.setPaginas((Integer) row.get("EDICAO"));
				livro.setEdicao((Integer) row.get("PAGINAS"));
			}
			
			Autor autor = new Autor();
			autor.setId((Integer) row.get("ID_AUTOR"));
			autor.setNome((String) row.get("NOME"));
			autor.setEmail((String) row.get("AUTOR_EMAIL"));
			
			Editora editora = new Editora();
			editora.setId((Integer) row.get("ID_EDITORA"));
			editora.setRazaoSocial((String) row.get("RAZAO_SOCIAL"));
			editora.setCidade((String) row.get("CIDADE"));
			editora.setEmail((String) row.get("EMAIL"));
			
			autor.setEditora(editora);
			
			autores.add(autor);
		}
		
		livro.setAutores(autores);
		
		return livro;
	}
	
	public Livro save(Livro livro){
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
				.withTableName("LIVROS")
				.usingColumns("TITULO", "EDICAO", "PAGINAS")
				.usingGeneratedKeyColumns("ID_LIVRO");
		
		Number key = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(livro));
		livro.setId(key.intValue());
		return livro;
	}
}
