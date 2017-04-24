package com.pelican.text.management.persistence.conf;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class HsqlDataSourceConfig {

	@Bean
	public DataSource createDataSource(){
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return (DataSource) builder.setType(EmbeddedDatabaseType.HSQL).build();
	}
	
	public HibernateJpaVendorAdapter createJpaVendorAdaptor(){
		HibernateJpaVendorAdapter jpaVendorAdaptor = new HibernateJpaVendorAdapter();
		jpaVendorAdaptor.setDatabase(Database.HSQL);
		jpaVendorAdaptor.setGenerateDdl(true);
		jpaVendorAdaptor.setShowSql(true);
		return jpaVendorAdaptor;
	}
}
