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
    List<Integer> findWorkshopSpecialty(@Bind("id") Integer id);

    @SqlQuery
    @RegisterBeanMapper(Specialty.class)
    List<Specialty> findSpecialtiesByWorkshopId(@Bind("id") Integer id);

    @SqlUpdate
    void insertNewWorkshopSpecialty(@Bind("id_oficina") Integer idOficina, @Bind("id_especialidade") Integer idEspecialidade);
}
