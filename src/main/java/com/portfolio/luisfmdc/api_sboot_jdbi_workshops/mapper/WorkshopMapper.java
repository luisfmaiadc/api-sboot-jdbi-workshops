package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.mapper;

import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Workshop;
import com.portfolio.luisfmdc.model.WorkshopRequest;
import com.portfolio.luisfmdc.model.WorkshopResponse;

public class WorkshopMapper {

    public static Workshop toEntity(WorkshopRequest workshopRequest) {
        return new Workshop(
                workshopRequest.getNome(),
                workshopRequest.getCnpj(),
                workshopRequest.getCidade(),
                workshopRequest.getEstado()
        );
    }

    public static WorkshopResponse toResponse(Workshop workshop) {
        return new WorkshopResponse()
                .id(workshop.getId())
                .nome(workshop.getNome())
                .cnpj(workshop.getCnpj())
                .cidade(workshop.getCidade())
                .estado(workshop.getEstado())
                .ativa(workshop.getAtiva())
                .especialidade(SpecialtyMapper.toResponseList(workshop.getSpecialtyList()))
                .fabricante(ManufacturerMapper.toResponseList(workshop.getManufacturerList()));
    }
}
