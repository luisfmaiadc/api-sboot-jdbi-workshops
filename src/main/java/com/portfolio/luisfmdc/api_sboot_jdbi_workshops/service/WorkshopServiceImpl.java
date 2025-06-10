package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.service;

import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.mapper.ManufacturerMapper;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.mapper.SpecialtyMapper;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.mapper.WorkshopMapper;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.UF;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Manufacturer;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Specialty;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Workshop;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.repository.WorkshopRepository;
import com.portfolio.luisfmdc.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Optional<Workshop> optionalWorkshop = findWorkshopEntity(workshopId);
        if (optionalWorkshop.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Workshop workshop = optionalWorkshop.get();
        return ResponseEntity.ok(WorkshopMapper.toResponse(workshop));
    }

    @Override
    public ResponseEntity<WorkshopResponse> updateWorkshop(WorkshopUpdateRequest workshopUpdateRequest, Integer workshopId) {
        Optional<Workshop> optionalWorkshop = findWorkshopEntity(workshopId);
        if (optionalWorkshop.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Workshop workshop = optionalWorkshop.get();
        boolean isUpdateValid = isUpdateValid(workshop, workshopUpdateRequest);
        if (isUpdateValid) {
            workshopRepository.updateWorkshop(workshop);
        }

        return ResponseEntity.ok(WorkshopMapper.toResponse(workshop));
    }

    private boolean isUpdateValid(Workshop workshop, WorkshopUpdateRequest workshopUpdateRequest) {
        boolean isUpdateValid = false;
        if (workshopUpdateRequest.getNome() != null && !workshopUpdateRequest.getNome().isBlank()) {
            if (!Objects.equals(workshopUpdateRequest.getNome(), workshop.getNome())) {
                workshop.setNome(workshopUpdateRequest.getNome());
                isUpdateValid = true;
            }
        }

        if (workshopUpdateRequest.getCidade() != null && !workshopUpdateRequest.getCidade().isBlank()) {
            if (!Objects.equals(workshopUpdateRequest.getCidade(), workshop.getCidade())) {
                workshop.setCidade(workshopUpdateRequest.getCidade());
                isUpdateValid = true;
            }
        }

        if (workshopUpdateRequest.getEstado() != null && !workshopUpdateRequest.getEstado().isBlank()) {
            if (!Objects.equals(workshopUpdateRequest.getEstado(), workshop.getEstado())) {
                workshop.setEstado(workshopUpdateRequest.getEstado());
                isUpdateValid = true;
            }
        }

        if (workshopUpdateRequest.getAtiva() != null) {
            if (!Objects.equals(workshopUpdateRequest.getAtiva(), workshop.getAtiva())) {
                workshop.setAtiva(workshopUpdateRequest.getAtiva());
                isUpdateValid = true;
            }
        }

        return isUpdateValid;
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

    @Override
    public ResponseEntity<List<ManufacturerResponse>> findManufacturers() {
        List<Manufacturer> manufacturerList = workshopRepository.findManufacturers();
        if (manufacturerList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ManufacturerMapper.toResponseList(manufacturerList));
    }

    @Override
    public ResponseEntity<List<SpecialtyResponse>> findSpecialties() {
        List<Specialty> specialtyList = workshopRepository.findSpecialties();
        if (specialtyList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(SpecialtyMapper.toResponseList(specialtyList));
    }

    @Override
    public ResponseEntity<WorkshopResponse> addWorkshopSpecialty(Integer workshopId, WorkshopSpecialtyRequest workshopSpecialtyRequest) {
        Optional<Workshop> optionalWorkshop = findWorkshopEntity(workshopId);
        if (optionalWorkshop.isEmpty()) return ResponseEntity.notFound().build();

        Workshop workshop = optionalWorkshop.get();
        boolean alreadyAssigned = workshop.getSpecialtyList().stream().anyMatch(s -> Objects.equals(s.getId(), workshopSpecialtyRequest.getIdEspecialidade()));

        if (!alreadyAssigned) {
            Optional<Specialty> optionalSpecialty = workshopRepository.findSpecialty(workshopSpecialtyRequest.getIdEspecialidade());
            if (optionalSpecialty.isEmpty()) return ResponseEntity.notFound().build();

            Specialty specialty = optionalSpecialty.get();
            workshopRepository.insertNewWorkshopSpecialty(workshop.getId(), specialty.getId());
            specialty.setAtiva(Boolean.TRUE);
            workshop.getSpecialtyList().add(specialty);
        }

        return ResponseEntity.ok(WorkshopMapper.toResponse(workshop));
    }

    @Override
    public ResponseEntity<WorkshopResponse> addWorkshopManufacturer(Integer workshopId, WorkshopManufacturerRequest workshopManufacturerRequest) {
        Optional<Workshop> optionalWorkshop = findWorkshopEntity(workshopId);
        if (optionalWorkshop.isEmpty()) return ResponseEntity.notFound().build();

        Workshop workshop = optionalWorkshop.get();
        boolean alreadyAssigned = workshop.getManufacturerList().stream().anyMatch(m -> Objects.equals(m.getId(), workshopManufacturerRequest.getIdFabricante()));

        if (!alreadyAssigned) {
            Optional<Manufacturer> optionalManufacturer = workshopRepository.findManufacturer(workshopManufacturerRequest.getIdFabricante());
            if (optionalManufacturer.isEmpty()) return ResponseEntity.notFound().build();

            Manufacturer manufacturer = optionalManufacturer.get();
            workshopRepository.insertNewWorkshopManufacturer(workshop.getId(), manufacturer.getId());
            manufacturer.setAtiva(Boolean.TRUE);
            workshop.getManufacturerList().add(manufacturer);
        }

        return ResponseEntity.ok(WorkshopMapper.toResponse(workshop));
    }

    @Override
    public ResponseEntity<WorkshopResponse> updateWorkshopSpecialtyStatus(Integer workshopId, UpdateWorkshopSpecialtyRequest updateWorkshopSpecialtyRequest) {
        Optional<Workshop> optionalWorkshop = findWorkshopEntity(workshopId);
        if (optionalWorkshop.isEmpty()) return ResponseEntity.notFound().build();
        Workshop workshop = optionalWorkshop.get();

        Optional<Specialty> optionalSpecialty = workshop.getSpecialtyList().stream()
                .filter(s -> Objects.equals(s.getId(), updateWorkshopSpecialtyRequest.getIdEspecialidade()))
                .findFirst();

        if (optionalSpecialty.isEmpty()) return ResponseEntity.notFound().build();
        Specialty specialty = optionalSpecialty.get();

        if (!Objects.equals(specialty.getAtiva(), updateWorkshopSpecialtyRequest.getAtiva())) {
            workshopRepository.updateWorkshopSpecialtyStatus(
                    updateWorkshopSpecialtyRequest.getAtiva(),
                    workshopId,
                    specialty.getId()
            );
        }

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity updateWorkshopManufacturerStatus(Integer workshopId, UpdateWorkshopManufacturerRequest updateWorkshopManufacturerRequest) {
        Optional<Workshop> optionalWorkshop = findWorkshopEntity(workshopId);
        if (optionalWorkshop.isEmpty()) return ResponseEntity.notFound().build();
        Workshop workshop = optionalWorkshop.get();

        Optional<Manufacturer> optionalManufacturer = workshop.getManufacturerList().stream()
                .filter(m -> Objects.equals(m.getId(), updateWorkshopManufacturerRequest.getIdFabricante()))
                .findFirst();

        if (optionalManufacturer.isEmpty()) return ResponseEntity.notFound().build();
        Manufacturer manufacturer = optionalManufacturer.get();

        if (!Objects.equals(manufacturer.getAtiva(), updateWorkshopManufacturerRequest.getAtiva())) {
            workshopRepository.updateWorkshopManufacturerStatus(
                    updateWorkshopManufacturerRequest.getAtiva(),
                    workshopId,
                    manufacturer.getId()
            );
        }

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<WorkshopResponse>> findByFilter(String cidade, String estado, String especialidade, String fabricante) {
        Optional<Specialty> optionalSpecialty = (especialidade != null && !especialidade.isBlank()) ? workshopRepository.findSpecialtyByName(especialidade) : Optional.empty();
        Optional<Manufacturer> optionalManufacturer = (fabricante != null && !fabricante.isBlank()) ? workshopRepository.findManufacturerByName(fabricante) : Optional.empty();
        cidade = validaCidade(cidade);
        estado = validaEstado(estado);

        if (cidade == null && estado == null && optionalSpecialty.isEmpty() && optionalManufacturer.isEmpty()) return ResponseEntity.badRequest().build();
        List<Integer> workshopsIds = validaFiltro(cidade, estado, optionalSpecialty, optionalManufacturer);

        if (workshopsIds.isEmpty()) return ResponseEntity.noContent().build();
        List<WorkshopResponse> workshopResponseList =  workshopsIds.stream()
                .map(this::findWorkshopEntity)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(WorkshopMapper::toResponse)
                .toList();
        return ResponseEntity.ok(workshopResponseList);
    }

    @Override
    public ResponseEntity<SpecialtyResponse> findSpecialtyById(Integer specialtyId) {
        Optional<Specialty> optionalSpecialty = workshopRepository.findSpecialtyById(specialtyId);
        return optionalSpecialty.map(specialty -> ResponseEntity.ok(SpecialtyMapper.toResponse(specialty))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private List<Integer> validaFiltro(String cidade, String estado, Optional<Specialty> optionalSpecialty, Optional<Manufacturer> optionalManufacturer) {
        Integer specialtyId = optionalSpecialty.map(Specialty::getId).orElse(null);
        Integer manufacturerId = optionalManufacturer.map(Manufacturer::getId).orElse(null);
        return workshopRepository.findByFilter(cidade, estado, specialtyId, manufacturerId);
    }

    private String validaCidade(String cidade) {
        if (cidade == null || cidade.isBlank()) return null;
        cidade = cidade.trim().replaceAll("\\s+", " ");
        return cidade.replaceAll("(?<=[a-z])(?=[A-Z])", " ");
    }

    private String validaEstado(String estado) {
        if (estado == null || estado.isBlank()) return null;
        estado = estado.toUpperCase();
        if (estado.length() != 2 || !UF.isValid(estado)) return null;
        return estado;
    }

    private Optional<Workshop> findWorkshopEntity(Integer workshopId) {
        Optional<Workshop> optionalWorkshop = workshopRepository.findWorkshop(workshopId);
        if (optionalWorkshop.isEmpty()) return Optional.empty();

        Workshop workshop = optionalWorkshop.get();
        workshop.setManufacturerList(workshopRepository.findManufacturersByWorkshopId(workshopId));
        workshop.setSpecialtyList(workshopRepository.findSpecialtiesByWorkshopId(workshopId));
        return Optional.of(workshop);
    }
}
