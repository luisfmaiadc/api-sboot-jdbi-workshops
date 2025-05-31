package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model;

import com.portfolio.luisfmdc.model.WorkshopUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    public boolean updateWorkshop(WorkshopUpdateRequest updateRequest) {
        boolean isUpdateValid = false;
        if (updateRequest.getNome() != null && !updateRequest.getNome().isBlank()) {
            if (!Objects.equals(updateRequest.getNome(), this.nome)) {
                this.nome = updateRequest.getNome();
                isUpdateValid = true;
            }
        }

        if (updateRequest.getCidade() != null && !updateRequest.getCidade().isBlank()) {
            if (!Objects.equals(updateRequest.getCidade(), this.cidade)) {
                this.cidade = updateRequest.getCidade();
                isUpdateValid = true;
            }
        }

        if (updateRequest.getEstado() != null && !updateRequest.getEstado().isBlank()) {
            if (!Objects.equals(updateRequest.getEstado(), this.estado)) {
                this.estado = updateRequest.getEstado();
                isUpdateValid = true;
            }
        }

        if (updateRequest.getAtiva() != null) {
            if (!Objects.equals(updateRequest.getAtiva(), this.ativa)) {
                this.ativa = updateRequest.getAtiva();
                isUpdateValid = true;
            }
        }

        return isUpdateValid;
    }
}
