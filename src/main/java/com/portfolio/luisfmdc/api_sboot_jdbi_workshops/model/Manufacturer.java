package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer {

    private Integer id;
    private String fabricante;

    public Manufacturer(String fabricante) {
        this.fabricante = fabricante;
    }
}
