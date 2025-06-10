package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.repository;

import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Manufacturer;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Specialty;
import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Workshop;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@UseClasspathSqlLocator
public interface WorkshopRepository {

    @SqlUpdate
    @GetGeneratedKeys
    int insertNewWorkshop(@BindBean Workshop workshop);

    @SqlQuery
    @RegisterBeanMapper(Workshop.class)
    Optional<Workshop> findWorkshop(@Bind("id") Integer id);

    @SqlUpdate
    void updateWorkshop(@BindBean Workshop workshop);

    @SqlUpdate
    @GetGeneratedKeys
    int insertNewSpecialty(@BindBean Specialty specialty);

    @SqlUpdate
    @GetGeneratedKeys
    int insertNewManufacturer(@BindBean Manufacturer manufacturer);

    @SqlQuery
    @RegisterBeanMapper(Manufacturer.class)
    List<Manufacturer> findManufacturers();

    @SqlQuery
    @RegisterBeanMapper(Specialty.class)
    List<Specialty> findSpecialties();

    @SqlQuery
    @RegisterBeanMapper(Specialty.class)
    Optional<Specialty> findSpecialty(@Bind("id") Integer id);

    @SqlQuery
    @RegisterBeanMapper(Specialty.class)
    List<Specialty> findSpecialtiesByWorkshopId(@Bind("id") Integer id);

    @SqlUpdate
    void insertNewWorkshopSpecialty(@Bind("id_oficina") Integer idOficina, @Bind("id_especialidade") Integer idEspecialidade);

    @SqlQuery
    @RegisterBeanMapper(Manufacturer.class)
    Optional<Manufacturer> findManufacturer(@Bind("id") Integer id);

    @SqlUpdate
    void insertNewWorkshopManufacturer(@Bind("id_oficina") Integer idOficina, @Bind("id_fabricante") Integer idFabricante);

    @SqlQuery
    @RegisterBeanMapper(Manufacturer.class)
    List<Manufacturer> findManufacturersByWorkshopId(@Bind("id") Integer id);

    @SqlUpdate
    void updateWorkshopSpecialtyStatus(@Bind("ativa") Boolean ativa, @Bind("id_oficina") Integer idOficina, @Bind("id_especialidade") Integer idEspecialidade);

    @SqlUpdate
    void updateWorkshopManufacturerStatus(@Bind("ativa") Boolean ativa, @Bind("id_oficina") Integer idOficina, @Bind("id_fabricante") Integer idFabricante);

    @SqlQuery
    List<Integer> findByFilter(@Bind("cidade") String cidade, @Bind("estado") String estado, @Bind("id_especialidade") Integer idEspecialidade, @Bind("id_fabricante") Integer idFabricante);

    @SqlQuery
    @RegisterBeanMapper(Specialty.class)
    Optional<Specialty> findSpecialtyByName(@Bind("especialidade") String especialidade);

    @SqlQuery
    @RegisterBeanMapper(Manufacturer.class)
    Optional<Manufacturer> findManufacturerByName(@Bind("fabricante") String fabricante);

    @SqlQuery
    @RegisterBeanMapper(Specialty.class)
    Optional<Specialty> findSpecialtyById(@Bind("id") Integer id);
}
