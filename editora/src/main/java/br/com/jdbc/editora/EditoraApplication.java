package br.com.jdbc.editora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.jdbc.editora.dao.EditoraDao;
import br.com.jdbc.editora.model.Editora;

@SpringBootApplication
public class EditoraApplication implements CommandLineRunner{
	
	@Autowired
	private EditoraDao editoraDao;

	public static void main(String[] args) {
		SpringApplication.run(EditoraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("--------------------------------------");
		
		insertEditora();
		
		System.out.println("--------------------------------------");
	}

	private void insertEditora() {
		
		Editora editora = new Editora();
		editora.setRazaoSocial("Editora Sao Paulo Ltda");
		editora.setCidade("São Paulo");
		editora.setEmail("teste@gmail.com.br");
		int ok = editoraDao.insert(editora);
		
		System.out.println("OK = "+ok);
	}

}

