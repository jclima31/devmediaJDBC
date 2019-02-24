-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE FUNCTION `db_example`.`function_conta_livros_autor` (idAutor INT)
RETURNS VARCHAR(200)
BEGIN
	DECLARE retorno, nome VARCHAR(200);
	DECLARE total, count INT;
	
	SELECT 
		COUNT(L.ID_LIVRO), A.NOME
	INTO 
		count, nome
	FROM
		AUTORES A, LIVROS L, LIVROS_AUTORES LA
	WHERE 
		A.ID_AUTOR = LA.ID_AUTOR
	AND
		LA.ID_LIVRO = L.ID_LIVRO
	AND
		A.ID_AUTOR = idAutor;
	SET total = count;
	SET retorno = concat("O autor " , nome, "possui ", total, "livros(s) publicados(s)");
RETURN retorno;
END