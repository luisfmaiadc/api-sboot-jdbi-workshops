package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.controller;

import com.portfolio.luisfmdc.api.WorkshopApi;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.service.WorkshopService;
import com.portfolio.luisfmdc.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkshopController implements WorkshopApi {

    private final WorkshopService workshopService;

    public WorkshopController(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @Override
    public ResponseEntity<WorkshopResponse> createWorkshop(WorkshopRequest workshopRequest) {
        return workshopService.createWorkshop(workshopRequest);
    }

    @Override
    public ResponseEntity<WorkshopResponse> findWorkshop(Integer workshopId) {
        return workshopService.findWorkshop(workshopId);
    }

    @Override
    public ResponseEntity<WorkshopResponse> updateWorkshop(Integer workshopId, WorkshopUpdateRequest workshopUpdateRequest) {
        return workshopService.updateWorkshop(workshopUpdateRequest, workshopId);
    }

    @Override
    public ResponseEntity<SpecialtyResponse> createSpecialty(SpecialtyRequest specialtyRequest) {
        return workshopService.createSpecialty(specialtyRequest);
    }

    @Override
    public ResponseEntity<ManufacturerResponse> createManufacturer(ManufacturerRequest manufacturerRequest) {
        return workshopService.createManufacturer(manufacturerRequest);
    }

    @Override
    public ResponseEntity<List<ManufacturerResponse>> findManufacturers() {
        return workshopService.findManufacturers();
    }

    @Override
    public ResponseEntity<List<SpecialtyResponse>> findSpecialties() {
        return workshopService.findSpecialties();
    }

    @Override
    public ResponseEntity<WorkshopResponse> addWorkshopSpecialty(Integer workshopId, WorkshopSpecialtyRequest workshopSpecialtyRequest) {
        return workshopService.addWorkshopSpecialty(workshopId, workshopSpecialtyRequest);
    }

    @Override
    public ResponseEntity<WorkshopResponse> addWorkshopManufacturer(Integer workshopId, WorkshopManufacturerRequest workshopManufacturerRequest) {
        return workshopService.addWorkshopManufacturer(workshopId, workshopManufacturerRequest);
    }

    @Override
    public ResponseEntity updateWorkshopSpecialtyStatus(Integer workshopId, UpdateWorkshopSpecialtyRequest updateWorkshopSpecialtyRequest) {
        return workshopService.updateWorkshopSpecialtyStatus(workshopId, updateWorkshopSpecialtyRequest);
    }

    @Override
    public ResponseEntity updateWorkshopManufacturerStatus(Integer workshopId, UpdateWorkshopManufacturerRequest updateWorkshopManufacturerRequest) {
        return workshopService.updateWorkshopManufacturerStatus(workshopId, updateWorkshopManufacturerRequest);
    }

    @Override
    public ResponseEntity<List<WorkshopResponse>> findByFilter(String cidade, String estado, String especialidade, String fabricante) {
        return workshopService.findByFilter(cidade, estado, especialidade, fabricante);
    }
}
