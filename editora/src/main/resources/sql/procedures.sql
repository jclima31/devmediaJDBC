DELIMITER $$

CREATE PROCEDURE `db_example`.`uppercase_titulo` (IN idLivro INT)
BEGIN
	update
		livros
	set
		titulo = UPPER(TITULO)
	WHERE ID_LIVRO = idLivro;
END

-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE PROCEDURE `db_example`.`procedure_info`(
	IN in_id INT,
	OUT out_titulo VARCHAR(41),
	OUT out_autor VARCHAR(41),
	OUT out_editora VARCHAR(43)
)
BEGIN
	SELECT 
		E.RAZAO_SOCIAL, L.TITULO, A.NOME
	INTO 
		OUT_EDITORA, OUT_TITULO, OUT_AUTOR
	FROM 
		EDITORAS E, AUTORES A, LIVROS L, LIVROS_AUTORES LA
	WHERE
		L.ID_LIVRO = LA.ID_LIVRO
	AND
		LA.ID_AUTOR = A.ID_AUTOR
	AND 
		A.ID_EDITORA = E.ID_EDITORA
	AND 
		L.ID_LIVRO = in_id;
END