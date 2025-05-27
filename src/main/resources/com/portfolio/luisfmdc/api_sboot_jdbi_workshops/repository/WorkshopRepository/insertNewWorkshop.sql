INSERT INTO TbOficina (
    nome,
    cnpj,
    cidade,
    estado,
    ativa
) VALUES (
    :nome,
    :cnpj,
    :cidade,
    :estado,
    true
)