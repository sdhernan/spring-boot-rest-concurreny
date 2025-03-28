package com.kucw.exposicion.configuraciones;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * The Class DataSourceConfig.
 */
@Configuration
public class DataSourceConfig {

	/**
	 * Demo data source.
	 *
	 * @return the data source
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	DataSource demoDataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * Demo jdbc template.
	 *
	 * @param dataSource the data source
	 * @return the named parameter jdbc template
	 */
	@Bean
	NamedParameterJdbcTemplate demoJdbcTemplate(@Qualifier("demoDataSource") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
}