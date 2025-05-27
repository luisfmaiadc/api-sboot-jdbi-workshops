package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.config;

import com.portfolio.luisfmdc.api_sboot_jdbi_workshops.repository.WorkshopRepository;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JdbiConfig {

    @Bean
    public Jdbi jdbi(DataSource dataSource) {
        Jdbi jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }

    @Bean
    public WorkshopRepository workshopRepository(Jdbi jdbi) {
        return jdbi.onDemand(WorkshopRepository.class);
    }
}
