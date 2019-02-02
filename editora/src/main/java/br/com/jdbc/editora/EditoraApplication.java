package br.com.jdbc.editora;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.jdbc.editora.dao.EditoraDao;
import br.com.jdbc.editora.dao.LivroDao;
import br.com.jdbc.editora.model.Editora;

@SpringBootApplication
public class EditoraApplication implements CommandLineRunner{
	
	@Autowired
	private EditoraDao editoraDao;
	
	@Autowired
	private LivroDao livroDao;

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
		execute();
		
		System.out.println("--------------------------------------");
	}

	private void execute() {
		
		livroDao.insert();
		
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

