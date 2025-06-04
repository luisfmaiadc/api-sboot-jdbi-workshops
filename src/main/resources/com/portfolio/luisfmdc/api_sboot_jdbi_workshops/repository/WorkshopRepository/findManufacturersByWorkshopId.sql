SELECT tbf.id, tbf.fabricante
FROM TbFabricante tbf
INNER JOIN TbOficinaFabricante tbof ON tbf.id = tbof.id_fabricante
WHERE tbof.id_oficina = :id
ORDER BY tbf.id