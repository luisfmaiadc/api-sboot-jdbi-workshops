package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.service;

import com.portfolio.luisfmdc.model.WorkshopRequest;
import com.portfolio.luisfmdc.model.WorkshopResponse;
import org.springframework.http.ResponseEntity;

public interface WorkshopService {

    ResponseEntity<WorkshopResponse> createWorkshop(WorkshopRequest workshopRequest);

}
