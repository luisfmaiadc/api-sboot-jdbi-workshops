package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.mapper;

import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Manufacturer;
import com.portfolio.luisfmdc.model.ManufacturerRequest;
import com.portfolio.luisfmdc.model.ManufacturerResponse;
import com.portfolio.luisfmdc.model.WorkshopManufacturerResponse;

import java.util.ArrayList;
import java.util.List;

public class ManufacturerMapper {

    public static Manufacturer toEntity(ManufacturerRequest manufacturerRequest) {
        return new Manufacturer(
                manufacturerRequest.getFabricante()
        );
    }

    public static ManufacturerResponse toResponse(Manufacturer manufacturer) {
        return new ManufacturerResponse()
                .id(manufacturer.getId())
                .fabricante(manufacturer.getFabricante());
    }

    public static List<ManufacturerResponse> toResponseList(List<Manufacturer> manufacturerList) {
        return manufacturerList.stream().map(ManufacturerMapper::toResponse).toList();
    }

    public static List<WorkshopManufacturerResponse> toResponseWorkshopList(List<Manufacturer> manufacturerList) {
        return manufacturerList.stream()
                .map(m -> new WorkshopManufacturerResponse()
                        .idFabricante(m.getId())
                        .fabricante(m.getFabricante())
                        .ativa(m.getAtiva()))
                .toList();
    }
}
