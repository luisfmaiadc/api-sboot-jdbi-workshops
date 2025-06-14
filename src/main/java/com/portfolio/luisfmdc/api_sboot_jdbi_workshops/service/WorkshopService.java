package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.service;

import com.portfolio.luisfmdc.model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WorkshopService {

    ResponseEntity<WorkshopResponse> createWorkshop(WorkshopRequest workshopRequest);

    ResponseEntity<WorkshopResponse> findWorkshop(Integer workshopId);

    ResponseEntity<WorkshopResponse> updateWorkshop(WorkshopUpdateRequest workshopUpdateRequest, Integer workshopId);

    ResponseEntity<SpecialtyResponse> createSpecialty(SpecialtyRequest specialtyRequest);

    ResponseEntity<ManufacturerResponse> createManufacturer(ManufacturerRequest manufacturerRequest);

    ResponseEntity<List<ManufacturerResponse>> findManufacturers();

    ResponseEntity<List<SpecialtyResponse>> findSpecialties();

    ResponseEntity<WorkshopResponse> addWorkshopSpecialty(Integer workshopId, WorkshopSpecialtyRequest workshopSpecialtyRequest);

    ResponseEntity<WorkshopResponse> addWorkshopManufacturer(Integer workshopId, WorkshopManufacturerRequest workshopManufacturerRequest);

    ResponseEntity<WorkshopResponse> updateWorkshopSpecialtyStatus(Integer workshopId, UpdateWorkshopSpecialtyRequest updateWorkshopSpecialtyRequest);

    ResponseEntity updateWorkshopManufacturerStatus(Integer workshopId, UpdateWorkshopManufacturerRequest updateWorkshopManufacturerRequest);

    ResponseEntity<List<WorkshopResponse>> findByFilter(String cidade, String estado, String especialidade, String fabricante);

    ResponseEntity<SpecialtyResponse> findSpecialtyById(Integer specialtyId);
}
