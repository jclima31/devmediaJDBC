package br.com.jdbc.editora;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.jdbc.editora.dao.AutorDao;
import br.com.jdbc.editora.dao.EditoraDao;
import br.com.jdbc.editora.dao.LivroAutorDao;
import br.com.jdbc.editora.dao.LivroDao;
import br.com.jdbc.editora.model.Autor;
import br.com.jdbc.editora.model.Editora;
import br.com.jdbc.editora.model.Livro;
import br.com.jdbc.editora.model.LivroAutor;

@SpringBootApplication
public class EditoraApplication implements CommandLineRunner{
	
	@Autowired
	private EditoraDao editoraDao;
	
	@Autowired
	private AutorDao autorDao;
	
	@Autowired
	private LivroDao livroDao;
	
	@Autowired
	private LivroAutorDao livroAutorDao;

	public static void main(String[] args) {
		SpringApplication.run(EditoraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("--------------------------------------");
		
		//insertEditora();
		//findAllEditoras();
		//findByIdEditora();
		//findByCidadesEditora();
		//findByRazaoSocialEditora();
		//findCidadeAndEmailByIdEditora();
		//execute();
		//insertAutor();
		//findEditoraWithAutores();
		//findLivroWithAutores();
		//findAutorWithLivros();
		//findLivrosByEdicao();
		findLivrosByPaginas();
		
		insertLivro();
		
		System.out.println("--------------------------------------");
	}

	private void findLivrosByPaginas() {
		List<Livro> livro = livroDao.findByPaginas(170, 210); 
		
	}

	private void findLivrosByEdicao() {
		List<Livro> livros = livroDao.findByEdicao(1);
		
	}

	private void findAutorWithLivros() {
		Autor autor = autorDao.findAutorWithLivros(1);
		
	}

	private void findLivroWithAutores() {
		
		Livro livro = livroDao.findLivroWithAutores(1);
		
	}

	private void insertLivro() {
		String titulo = "Aprenda JSE em 40 dias";
		int edicao = 1;
		int paginas = 168;
		String[] autores = {"Autor1", "Autor2"};
		
		Livro livro = new Livro();
		livro.setTitulo(titulo);
		livro.setEdicao(edicao);
		livro.setPaginas(paginas);
		
		livro = livroDao.save(livro);
		
		Integer idLivro = livro.getId();
		
		for (String nome : autores) {
			
			Integer idAutor = autorDao.getIdByNome(nome);
			
			livroAutorDao.save(new LivroAutor(idLivro, idAutor));
		}
	}

	private void findEditoraWithAutores() {
		
		Editora editora = editoraDao.findEditoraWithAutores(2, 0, 2);
	}

	private void insertAutor() {
		Editora editora = editoraDao.findById(1);
		
		Autor autor = new Autor();
		autor.setEditora(editora);
		autor.setNome("Aline da Silva");
		autor.setEmail("alice@hotmail.com.br");
		
		autor = autorDao.Save(autor);
		System.out.println(autor);
	}

	private void execute() {
		
		//livroDao.insert();
		
	}

	private void findCidadeAndEmailByIdEditora() {
		
		List<String> lista = editoraDao.findCidadeAndEmailById(3);
		for (String s : lista) {
			System.out.println(s);
		}
	}

	private void findByRazaoSocialEditora() {
		
		List<Editora> editoras = editoraDao.findByRazaoSocial("RJ");
		for (Editora editora : editoras) {
			System.out.println(editora.toString());
		}
	}

	private void findByCidadesEditora() {
		
		List<Editora> editoras = editoraDao.findByCidades("Rio de Janeiro", "São Paulo");
		for (Editora editora : editoras) {
			System.out.println(editora.toString());
		}
	}

	private void findByIdEditora() {
		
		Editora editora = editoraDao.findById(1);
		System.out.println(editora.toString());
	}

	private void findAllEditoras() {
		
		List<Editora> editoras = new ArrayList<Editora>();
		editoras = editoraDao.findAll();
		
		for (Editora editora : editoras) {
			System.out.println(editora.toString());
		}
	}

	private void insertEditora() {
		
		Editora editora = new Editora();
		editora.setRazaoSocial("Editora RJ Ltda");
		editora.setCidade("Rio de Janeiro");
		editora.setEmail("teste2@gmail.com.br");
		
		//int ok = editoraDao.insert(editora);
		//System.out.println("OK = "+ok);
		
		int id = editoraDao.save(editora);
		System.out.println("ID = "+id);
		
	}

}

