UPDATE TbOficina
SET
    nome = :nome,
    cidade = :cidade,
    estado = :estado,
    ativa = :ativa
WHERE id = :id
