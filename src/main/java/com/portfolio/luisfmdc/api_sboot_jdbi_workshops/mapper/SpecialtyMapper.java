package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.mapper;

import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Specialty;
import com.portfolio.luisfmdc.model.SpecialtyRequest;
import com.portfolio.luisfmdc.model.SpecialtyResponse;

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
                .id(specialty.getId())
                .especialidade(specialty.getEspecialidade());
    }

    public static List<SpecialtyResponse> toResponseList(List<Specialty> specialtyList) {
        List<SpecialtyResponse> specialtyResponseList = new ArrayList<>();
        specialtyList.forEach(s -> specialtyResponseList.add(SpecialtyMapper.toResponse(s)));
        return specialtyResponseList;
    }
}
