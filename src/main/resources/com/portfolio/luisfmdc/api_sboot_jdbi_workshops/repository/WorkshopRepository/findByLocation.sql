SELECT  id
FROM TbOficina
WHERE (:cidade IS NULL OR cidade = :cidade)
AND (:estado IS NULL OR estado = :estado)
AND ativa = true