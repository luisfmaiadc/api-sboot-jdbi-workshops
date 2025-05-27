package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.repository;

import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model.Workshop;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

@Repository
@UseClasspathSqlLocator
public interface WorkshopRepository {

    @SqlUpdate
    @GetGeneratedKeys
    int insertNewWorkshop(@BindBean Workshop workshop);

}
