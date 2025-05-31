package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Specialty {

    private Integer id;
    private String especialidade;

    public Specialty(String especialidade) {
        this.especialidade = especialidade;
    }

}
