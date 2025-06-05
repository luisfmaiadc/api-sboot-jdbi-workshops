SELECT tbe.id, tbe.especialidade, tboe.ativa
FROM TbEspecialidade tbe
INNER JOIN TbOficinaEspecialidade tboe ON tbe.id = tboe.id_especialidade
WHERE tboe.id_oficina = :id
ORDER BY tbe.id