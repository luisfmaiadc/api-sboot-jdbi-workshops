package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workshop {

    private Integer id;
    private String nome;
    private String cnpj;
    private String cidade;
    private String estado;
    private Boolean ativa;

    public Workshop(String nome, String cnpj, String cidade, String estado) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.estado = estado;
    }
}
