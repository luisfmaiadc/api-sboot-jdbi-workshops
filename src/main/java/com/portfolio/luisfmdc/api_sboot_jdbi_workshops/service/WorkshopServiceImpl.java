package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.service;

import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.mapper.ManufacturerMapper;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.mapper.SpecialtyMapper;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.mapper.WorkshopMapper;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Manufacturer;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Specialty;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Workshop;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.repository.WorkshopRepository;
import com.portfolio.luisfmdc.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkshopServiceImpl implements WorkshopService {

    private final WorkshopRepository workshopRepository;

    public WorkshopServiceImpl(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @Override
    public ResponseEntity<WorkshopResponse> createWorkshop(WorkshopRequest workshopRequest) {
        Workshop workshop = WorkshopMapper.toEntity(workshopRequest);
        int id = workshopRepository.insertNewWorkshop(workshop);
        workshop.setId(id);
        workshop.setAtiva(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(WorkshopMapper.toResponse(workshop));
    }

    @Override
    public ResponseEntity<WorkshopResponse> findWorkshop(Integer workshopId) {
        Optional<Workshop> optionalWorkshop = workshopRepository.findWorkshop(workshopId);
        if (optionalWorkshop.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Workshop workshop = optionalWorkshop.get();
        return ResponseEntity.ok(WorkshopMapper.toResponse(workshop));
    }

    @Override
    public ResponseEntity<WorkshopResponse> updateWorkshop(WorkshopUpdateRequest workshopUpdateRequest, Integer workshopId) {
        Optional<Workshop> optionalWorkshop = workshopRepository.findWorkshop(workshopId);
        if (optionalWorkshop.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Workshop workshop = optionalWorkshop.get();
        boolean isUpdateValid = workshop.updateWorkshop(workshopUpdateRequest);
        if (isUpdateValid) {
            workshopRepository.updateWorkshop(workshop);
        }

        return ResponseEntity.ok(WorkshopMapper.toResponse(workshop));
    }

    @Override
    public ResponseEntity<SpecialtyResponse> createSpecialty(SpecialtyRequest specialtyRequest) {
        Specialty specialty = SpecialtyMapper.toEntity(specialtyRequest);
        int id = workshopRepository.insertNewSpecialty(specialty);
        specialty.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(SpecialtyMapper.toResponse(specialty));
    }

    @Override
    public ResponseEntity<ManufacturerResponse> createManufacturer(ManufacturerRequest manufacturerRequest) {
        Manufacturer manufacturer = ManufacturerMapper.toEntity(manufacturerRequest);
        int id = workshopRepository.insertNewManufacturer(manufacturer);
        manufacturer.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(ManufacturerMapper.toResponse(manufacturer));
    }
}
