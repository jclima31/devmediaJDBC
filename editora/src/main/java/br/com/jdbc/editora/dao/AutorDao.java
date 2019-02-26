package br.com.jdbc.editora.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.com.jdbc.editora.dao.mapper.AutorMapper;
import br.com.jdbc.editora.model.Autor;
import br.com.jdbc.editora.model.Editora;
import br.com.jdbc.editora.model.Livro;

@Repository
@PropertySource("classpath:sql/autor.xml")
public class AutorDao {

	private static final String SQL_DELETE = null;
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert;
	@Autowired
	private EditoraDao editoraDao;
	
	@Value("${sql.autor.update}")
	private String SQL_UPDATE;
	
	@Value("${sql.autor.findAll}")
	private String SQL_FIND_ALL;
	
	@Value("${sql.autor.findAutoresBy.editora}")
	private String SQL_FIND_AUTORES_BY_EDITORA;
	
	@Value("${sql.autor.getIdByNome}")
	private String SQL_GET_ID_BY_NOME;
	
	@Value("${sql.autor.findAutorWithLivros}")
	private String SQL_FIND_AUTOR_WITH_LIVROS;

	public void deleteBatch(final List<Integer> ids){
		jdbcTemplate.batchUpdate(SQL_DELETE,
			new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setInt(1, ids.get(i));
					
				}
				
				@Override
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return ids.size();
				}
			});
	}
	
	public void updateBatch(final List<Autor> autores){
		jdbcTemplate.batchUpdate(SQL_UPDATE, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, autores.get(i).getNome());
				ps.setString(2, autores.get(i).getEmail());
				ps.setInt(3, autores.get(i).getEditora().getId());
				ps.setInt(4, autores.get(i).getEditora().getId());
				
			}
			
			@Override
			public int getBatchSize() {
				
				return autores.size();
			}
		});
	}
	
	public Autor findAutorWithLivros(int idAutor){
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL_FIND_AUTOR_WITH_LIVROS, idAutor);
		
		Autor autor = null;
		
		List<Livro> livros = new ArrayList<Livro>();
		
		for(Map row : rows){
			
			if(autor == null){
				autor = new Autor();
				autor.setId((Integer) row.get("ID_AUTOR"));
				autor.setNome((String) row.get("NOME"));
				autor.setEmail((String) row.get("EMAIL"));
				
				Editora editora = new Editora();
				editora.setId((Integer) row.get("ID_EDITORA"));
				editora.setRazaoSocial((String) row.get("RAZAO_SOCIAL"));
				editora.setCidade((String) row.get("CIDADE"));
				editora.setEmail((String) row.get("EMAIL"));
				
				autor.setEditora(editora);
			}
			
			Livro livro = new Livro();
			livro.setId((Integer) row.get("ID_LIVRO"));
			livro.setTitulo((String) row.get("TITULO"));
			livro.setPaginas((Integer) row.get("EDICAO"));
			livro.setEdicao((Integer) row.get("PAGINAS"));
			
			livros.add(livro);
			
		}
		
		autor.setLivros(livros);
		
		return autor;
	}
	
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
