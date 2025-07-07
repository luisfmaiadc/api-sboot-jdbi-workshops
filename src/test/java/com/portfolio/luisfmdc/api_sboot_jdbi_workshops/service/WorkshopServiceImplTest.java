package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.service;

import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.mapper.ManufacturerMapper;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.mapper.SpecialtyMapper;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.mapper.WorkshopMapper;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Manufacturer;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Specialty;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Workshop;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.repository.WorkshopRepository;
import com.portfolio.luisfmdc.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkshopServiceImplTest {

    @InjectMocks
    private WorkshopServiceImpl service;

    @Mock
    private WorkshopRepository repository;

    private Workshop workshop;

    private WorkshopRequest workshopRequest;

    private WorkshopUpdateRequest workshopUpdateRequest;

    private Specialty specialty;

    private SpecialtyRequest specialtyRequest;

    private Manufacturer manufacturer;

    private ManufacturerRequest manufacturerRequest;

    private WorkshopSpecialtyRequest workshopSpecialtyRequest;

    private WorkshopManufacturerRequest workshopManufacturerRequest;

    private UpdateWorkshopSpecialtyRequest updateWorkshopSpecialtyRequest;

    private UpdateWorkshopManufacturerRequest updateWorkshopManufacturerRequest;

    @BeforeEach
    void setUp() {
        workshop = new Workshop(1, "Oficina de Teste", "11111111111111", "São Paulo", "SP", true, new ArrayList<>(), new ArrayList<>());
        workshopRequest = new WorkshopRequest("Oficina de Teste", "111111111111", "São Paulo", "SP");
        workshopUpdateRequest = new WorkshopUpdateRequest();
        specialty = new Specialty(1, "Elétrica", true);
        specialtyRequest = new SpecialtyRequest();
        manufacturer = new Manufacturer(1, "Mercedes", true);
        manufacturerRequest = new ManufacturerRequest();
        workshopSpecialtyRequest = new WorkshopSpecialtyRequest();
        workshopManufacturerRequest = new WorkshopManufacturerRequest();
        updateWorkshopSpecialtyRequest = new UpdateWorkshopSpecialtyRequest();
        updateWorkshopManufacturerRequest = new UpdateWorkshopManufacturerRequest();
    }

    @Test
    void createWorkshop() {
        Workshop expectedWorkshop = WorkshopMapper.toEntity(workshopRequest);
        when(repository.insertNewWorkshop(expectedWorkshop)).thenReturn(1);

        ResponseEntity<WorkshopResponse> response = service.createWorkshop(workshopRequest);
        expectedWorkshop.setId(1);
        expectedWorkshop.setAtiva(true);

        assertEquals(WorkshopMapper.toResponse(expectedWorkshop), response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
    
    @Test
    void findWorkshop() {
        when(repository.findWorkshop(1)).thenReturn(Optional.of(workshop));
        when(repository.findManufacturersByWorkshopId(1)).thenReturn(new ArrayList<>());
        when(repository.findSpecialtiesByWorkshopId(1)).thenReturn(new ArrayList<>());

        ResponseEntity<WorkshopResponse> response = service.findWorkshop(1);

        assertEquals(WorkshopMapper.toResponse(workshop), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void notFoundWorkshop() {
        when(repository.findWorkshop(1)).thenReturn(Optional.empty());
        ResponseEntity<WorkshopResponse> response = service.findWorkshop(1);
        assertNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateWorkshop() {
        when(repository.findWorkshop(1)).thenReturn(Optional.of(workshop));
        when(repository.findManufacturersByWorkshopId(1)).thenReturn(new ArrayList<>());
        when(repository.findSpecialtiesByWorkshopId(1)).thenReturn(new ArrayList<>());
        workshopUpdateRequest.setNome("Teste 2");

        ResponseEntity<WorkshopResponse> response = service.updateWorkshop(workshopUpdateRequest, 1);

        verify(repository).updateWorkshop(Mockito.any());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(workshopUpdateRequest.getNome(), response.getBody().getNome());
    }

    @Test
    void notFoundWorkshopInUpdate() {
        when(repository.findWorkshop(1)).thenReturn(Optional.empty());
        ResponseEntity<WorkshopResponse> response = service.updateWorkshop(workshopUpdateRequest, 1);
        assertNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void invalidWorkshopUpdate() {
        when(repository.findWorkshop(1)).thenReturn(Optional.of(workshop));
        when(repository.findManufacturersByWorkshopId(1)).thenReturn(new ArrayList<>());
        when(repository.findSpecialtiesByWorkshopId(1)).thenReturn(new ArrayList<>());
        workshopUpdateRequest.setNome("Oficina de Teste");

        ResponseEntity<WorkshopResponse> response = service.updateWorkshop(workshopUpdateRequest, 1);

        verify(repository, never()).updateWorkshop(workshop);
        assertEquals(WorkshopMapper.toResponse(workshop), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createSpecialty() {
        specialtyRequest.setEspecialidade("Elétrica");
        Specialty expectedSpecialty = SpecialtyMapper.toEntity(specialtyRequest);
        when(repository.insertNewSpecialty(expectedSpecialty)).thenReturn(1);

        ResponseEntity<SpecialtyResponse> response = service.createSpecialty(specialtyRequest);
        expectedSpecialty.setId(1);

        assertEquals(SpecialtyMapper.toResponse(expectedSpecialty), response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void createManufacturer() {
        manufacturerRequest.setFabricante("Mercedes");
        Manufacturer expectedManufacturer = ManufacturerMapper.toEntity(manufacturerRequest);
        when(repository.insertNewManufacturer(expectedManufacturer)).thenReturn(1);

        ResponseEntity<ManufacturerResponse> response = service.createManufacturer(manufacturerRequest);
        expectedManufacturer.setId(1);

        assertEquals(ManufacturerMapper.toResponse(expectedManufacturer), response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void findManufacturers() {
        when(repository.findManufacturers()).thenReturn(List.of(manufacturer));
        ResponseEntity<List<ManufacturerResponse>> response = service.findManufacturers();
        assertEquals(ManufacturerMapper.toResponseList(List.of(manufacturer)), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void notFoundManufacturers() {
        when(repository.findManufacturers()).thenReturn(List.of());
        ResponseEntity<List<ManufacturerResponse>> response = service.findManufacturers();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void findSpecialties() {
        when(repository.findSpecialties()).thenReturn(List.of(specialty));
        ResponseEntity<List<SpecialtyResponse>> response = service.findSpecialties();
        assertEquals(SpecialtyMapper.toResponseList(List.of(specialty)), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void notFoundSpecialties() {
        when(repository.findSpecialties()).thenReturn(List.of());
        ResponseEntity<List<SpecialtyResponse>> response = service.findSpecialties();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void addWorkshopSpecialty() {
        workshopSpecialtyRequest.setIdEspecialidade(1);
        when(repository.findWorkshop(1)).thenReturn(Optional.of(workshop));
        when(repository.findManufacturersByWorkshopId(1)).thenReturn(new ArrayList<>());
        when(repository.findSpecialtiesByWorkshopId(1)).thenReturn(new ArrayList<>());
        when(repository.findSpecialty(workshopSpecialtyRequest.getIdEspecialidade())).thenReturn(Optional.of(specialty));
        workshop.getSpecialtyList().add(specialty);

        ResponseEntity<WorkshopResponse> response = service.addWorkshopSpecialty(1, workshopSpecialtyRequest);

        assertEquals(WorkshopMapper.toResponse(workshop), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void notFoundWorkshopInAddWorkshopSpecialty() {
        when(repository.findWorkshop(1)).thenReturn(Optional.empty());
        ResponseEntity<WorkshopResponse> response = service.addWorkshopSpecialty(1, workshopSpecialtyRequest);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void specialtyAlreadyAssigned() {
        workshopSpecialtyRequest.setIdEspecialidade(1);
        when(repository.findWorkshop(1)).thenReturn(Optional.of(workshop));
        when(repository.findManufacturersByWorkshopId(1)).thenReturn(new ArrayList<>());
        when(repository.findSpecialtiesByWorkshopId(1)).thenReturn(List.of(specialty));
        workshop.getSpecialtyList().add(specialty);

        ResponseEntity<WorkshopResponse> response = service.addWorkshopSpecialty(1, workshopSpecialtyRequest);

        assertEquals(WorkshopMapper.toResponse(workshop), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void notFoundSpecialtyInAddWorkshopSpecialty() {
        workshopSpecialtyRequest.setIdEspecialidade(1);
        when(repository.findWorkshop(1)).thenReturn(Optional.of(workshop));
        when(repository.findManufacturersByWorkshopId(1)).thenReturn(new ArrayList<>());
        when(repository.findSpecialtiesByWorkshopId(1)).thenReturn(new ArrayList<>());
        when(repository.findSpecialty(workshopSpecialtyRequest.getIdEspecialidade())).thenReturn(Optional.empty());

        ResponseEntity<WorkshopResponse> response = service.addWorkshopSpecialty(1, workshopSpecialtyRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void addWorkshopManufacturer() {
        workshopManufacturerRequest.setIdFabricante(1);
        when(repository.findWorkshop(1)).thenReturn(Optional.of(workshop));
        when(repository.findManufacturersByWorkshopId(1)).thenReturn(new ArrayList<>());
        when(repository.findSpecialtiesByWorkshopId(1)).thenReturn(new ArrayList<>());
        when(repository.findManufacturer(workshopManufacturerRequest.getIdFabricante())).thenReturn(Optional.of(manufacturer));
        workshop.getManufacturerList().add(manufacturer);

        ResponseEntity<WorkshopResponse> response = service.addWorkshopManufacturer(1, workshopManufacturerRequest);

        assertEquals(WorkshopMapper.toResponse(workshop), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void notFoundWorkshopInAddWorkshopManufacturer() {
        when(repository.findWorkshop(1)).thenReturn(Optional.empty());
        ResponseEntity<WorkshopResponse> response = service.addWorkshopManufacturer(1, workshopManufacturerRequest);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void manufacturerAlreadyAssigned() {
        workshopManufacturerRequest.setIdFabricante(1);
        when(repository.findWorkshop(1)).thenReturn(Optional.of(workshop));
        when(repository.findManufacturersByWorkshopId(1)).thenReturn(List.of(manufacturer));
        when(repository.findSpecialtiesByWorkshopId(1)).thenReturn(new ArrayList<>());
        workshop.getManufacturerList().add(manufacturer);

        ResponseEntity<WorkshopResponse> response = service.addWorkshopManufacturer(1, workshopManufacturerRequest);

        assertEquals(WorkshopMapper.toResponse(workshop), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void notFoundManufacturerInAddWorkshopManufacturer() {
        workshopManufacturerRequest.setIdFabricante(1);
        when(repository.findWorkshop(1)).thenReturn(Optional.of(workshop));
        when(repository.findManufacturersByWorkshopId(1)).thenReturn(new ArrayList<>());
        when(repository.findSpecialtiesByWorkshopId(1)).thenReturn(new ArrayList<>());
        when(repository.findManufacturer(workshopManufacturerRequest.getIdFabricante())).thenReturn(Optional.empty());

        ResponseEntity<WorkshopResponse> response = service.addWorkshopManufacturer(1, workshopManufacturerRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateWorkshopSpecialtyStatus() {
        updateWorkshopSpecialtyRequest.setIdEspecialidade(1);
        updateWorkshopSpecialtyRequest.setAtiva(false);
        when(repository.findWorkshop(1)).thenReturn(Optional.of(workshop));
        when(repository.findSpecialtiesByWorkshopId(1)).thenReturn(List.of(specialty));
        when(repository.findManufacturersByWorkshopId(1)).thenReturn(new ArrayList<>());

        ResponseEntity<WorkshopResponse> response = service.updateWorkshopSpecialtyStatus(1, updateWorkshopSpecialtyRequest);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void notFoundWorkshopInUpdateWorkshopSpecialtyStatus() {
        when(repository.findWorkshop(1)).thenReturn(Optional.empty());
        var response = service.updateWorkshopSpecialtyStatus(1, updateWorkshopSpecialtyRequest);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void equalUpdateSpecialtyInUpdateWorkshopSpecialtyStatus() {
        updateWorkshopSpecialtyRequest.setIdEspecialidade(1);
        updateWorkshopSpecialtyRequest.setAtiva(true);
        when(repository.findWorkshop(1)).thenReturn(Optional.of(workshop));
        when(repository.findManufacturersByWorkshopId(1)).thenReturn(new ArrayList<>());
        when(repository.findSpecialtiesByWorkshopId(1)).thenReturn(List.of(specialty));

        var response = service.updateWorkshopSpecialtyStatus(1, updateWorkshopSpecialtyRequest);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void notFoundSpecialtyInUpdateWorkshopSpecialtyStatus() {
        updateWorkshopSpecialtyRequest.setIdEspecialidade(1);
        when(repository.findWorkshop(1)).thenReturn(Optional.of(workshop));
        when(repository.findManufacturersByWorkshopId(1)).thenReturn(new ArrayList<>());
        when(repository.findSpecialtiesByWorkshopId(1)).thenReturn(new ArrayList<>());

        var response = service.updateWorkshopSpecialtyStatus(1, updateWorkshopSpecialtyRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateWorkshopManufacturerStatus() {
        updateWorkshopManufacturerRequest.setIdFabricante(1);
        updateWorkshopManufacturerRequest.setAtiva(false);
        when(repository.findWorkshop(1)).thenReturn(Optional.of(workshop));
        when(repository.findSpecialtiesByWorkshopId(1)).thenReturn(new ArrayList<>());
        when(repository.findManufacturersByWorkshopId(1)).thenReturn(List.of(manufacturer));

        var response = service.updateWorkshopManufacturerStatus(1, updateWorkshopManufacturerRequest);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void notFoundWorkshopInUpdateWorkshopManufacturerStatus() {
        when(repository.findWorkshop(1)).thenReturn(Optional.empty());
        var response = service.updateWorkshopManufacturerStatus(1, updateWorkshopManufacturerRequest);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void equalUpdateManufacturerInUpdateWorkshopManufacturerStatus() {
        updateWorkshopManufacturerRequest.setIdFabricante(1);
        updateWorkshopManufacturerRequest.setAtiva(true);
        when(repository.findWorkshop(1)).thenReturn(Optional.of(workshop));
        when(repository.findManufacturersByWorkshopId(1)).thenReturn(List.of(manufacturer));
        when(repository.findSpecialtiesByWorkshopId(1)).thenReturn(new ArrayList<>());

        var response = service.updateWorkshopManufacturerStatus(1, updateWorkshopManufacturerRequest);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void notFoundManufacturerInUpdateWorkshopManufacturerStatus() {
        updateWorkshopManufacturerRequest.setIdFabricante(1);
        when(repository.findWorkshop(1)).thenReturn(Optional.of(workshop));
        when(repository.findManufacturersByWorkshopId(1)).thenReturn(new ArrayList<>());
        when(repository.findSpecialtiesByWorkshopId(1)).thenReturn(new ArrayList<>());

        var response = service.updateWorkshopManufacturerStatus(1, updateWorkshopManufacturerRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void findByFilter() {
        when(repository.findSpecialtyByName("Elétrica")).thenReturn(Optional.of(specialty));
        when(repository.findManufacturerByName("Mercedes")).thenReturn(Optional.of(manufacturer));
        when(repository.findByFilter("São Paulo", "SP", specialty.getId(), manufacturer.getId())).thenReturn(List.of(1, 2));

        Workshop workshop1 = new Workshop();
        workshop1.setId(1);
        workshop1.setManufacturerList(List.of(manufacturer));
        workshop1.setSpecialtyList(List.of(specialty));
        when(repository.findWorkshop(1)).thenReturn(Optional.of(workshop1));
        when(repository.findManufacturersByWorkshopId(1)).thenReturn(List.of(manufacturer));
        when(repository.findSpecialtiesByWorkshopId(1)).thenReturn(List.of(specialty));

        Workshop workshop2 = new Workshop();
        workshop2.setId(2);
        workshop2.setManufacturerList(List.of(manufacturer));
        workshop2.setSpecialtyList(List.of(specialty));
        when(repository.findWorkshop(2)).thenReturn(Optional.of(workshop2));
        when(repository.findManufacturersByWorkshopId(2)).thenReturn(List.of(manufacturer));
        when(repository.findSpecialtiesByWorkshopId(2)).thenReturn(List.of(specialty));

        List<WorkshopResponse> expectedResponses = List.of(
                WorkshopMapper.toResponse(workshop1),
                WorkshopMapper.toResponse(workshop2)
        );

        ResponseEntity<List<WorkshopResponse>> response = service.findByFilter("São Paulo", "SP", "Elétrica", "Mercedes");

        assertEquals(expectedResponses, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void noFilterProvidedInFindByFilter() {
        ResponseEntity<List<WorkshopResponse>> response = service.findByFilter("  ", "  ", "", "");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void validFilterButNoResultsInFindByFilter() {
        when(repository.findSpecialtyByName("Elétrica")).thenReturn(Optional.of(specialty));
        when(repository.findManufacturerByName("Mercedes")).thenReturn(Optional.of(manufacturer));
        when(repository.findByFilter("São Paulo", "SP", specialty.getId(), manufacturer.getId())).thenReturn(Collections.emptyList());

        ResponseEntity<List<WorkshopResponse>> response = service.findByFilter("São Paulo", "SP", "Elétrica", "Mercedes");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void findSpecialtyById() {
        when(repository.findSpecialtyById(1)).thenReturn(Optional.of(specialty));
        ResponseEntity<SpecialtyResponse> response = service.findSpecialtyById(1);
        assertEquals(SpecialtyMapper.toResponse(specialty), response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void notFoundSpecialtyInFindSpecialtyById() {
        when(repository.findSpecialtyById(1)).thenReturn(Optional.empty());
        ResponseEntity<SpecialtyResponse> response = service.findSpecialtyById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}