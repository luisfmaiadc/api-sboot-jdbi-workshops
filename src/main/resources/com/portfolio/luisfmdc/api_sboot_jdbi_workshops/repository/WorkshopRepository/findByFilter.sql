SELECT DISTINCT (o.id)
FROM TbOficina o
LEFT JOIN TbOficinaEspecialidade oe ON o.id = oe.id_oficina AND oe.ativa = true
LEFT JOIN TbEspecialidade e ON oe.id_especialidade = e.id
LEFT JOIN TbOficinaFabricante ofa ON o.id = ofa.id_oficina AND ofa.ativa = true
LEFT JOIN TbFabricante f ON ofa.id_fabricante = f.id
WHERE o.ativa = true
    AND (:cidade IS NULL OR o.cidade = :cidade)
    AND (:estado IS NULL OR o.estado = :estado)
    AND (:id_especialidade IS NULL OR e.id = :id_especialidade)
    AND (:id_fabricante IS NULL OR f.id = :id_fabricante)