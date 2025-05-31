package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.controller;

import com.portfolio.luisfmdc.api.WorkshopApi;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.service.WorkshopService;
import com.portfolio.luisfmdc.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
}
