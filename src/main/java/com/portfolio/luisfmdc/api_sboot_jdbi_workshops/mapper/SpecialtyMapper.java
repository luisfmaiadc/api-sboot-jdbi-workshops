package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.mapper;

import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Specialty;
import com.portfolio.luisfmdc.model.SpecialtyRequest;
import com.portfolio.luisfmdc.model.SpecialtyResponse;
import com.portfolio.luisfmdc.model.WorkshopSpecialtyResponse;

import java.util.ArrayList;
import java.util.List;

public class SpecialtyMapper {

    public static Specialty toEntity(SpecialtyRequest specialtyRequest) {
        return new Specialty(
                specialtyRequest.getEspecialidade()
        );
    }

    public static SpecialtyResponse toResponse(Specialty specialty) {
        return new SpecialtyResponse()
                .idEspecialidade(specialty.getId())
                .especialidade(specialty.getEspecialidade());
    }

    public static List<SpecialtyResponse> toResponseList(List<Specialty> specialtyList) {
        return specialtyList.stream().map(SpecialtyMapper::toResponse).toList();
    }

    public static List<WorkshopSpecialtyResponse> toResponseWorkshopList(List<Specialty> specialtyList) {
        return specialtyList.stream()
                .map(s -> new WorkshopSpecialtyResponse()
                        .idEspecialidade(s.getId())
                        .especialidade(s.getEspecialidade())
                        .ativa(s.getAtiva()))
                .toList();
    }
}
