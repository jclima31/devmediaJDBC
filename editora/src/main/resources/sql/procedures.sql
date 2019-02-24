DELIMITER $$

CREATE PROCEDURE `db_example`.`uppercase_titulo` (IN idLivro INT)
BEGIN
	update
		livros
	set
		titulo = UPPER(TITULO)
	WHERE ID_LIVRO = idLivro;
END