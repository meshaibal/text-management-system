package com.pelican.text.management.persistence.conf;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.pelican.text.management.persistence.commons.PersistenceConstants;

@Configuration
@ComponentScan(basePackages = {PersistenceLayerConfig.PACKAGE_PERSISTENCE_LAYER})
@EnableJpaRepositories(
		basePackages = {PersistenceConstants.PACKAGE_PERSISTENCE_REPOSITORY},
		entityManagerFactoryRef = PersistenceConstants.BEAN_NAME_ENTITY_MANAGER_FACTORY
		)
public class PersistenceLayerConfig {

	public static final String PACKAGE_PERSISTENCE_LAYER = "com.pelican.text.management.persistence" ;
	
	private HsqlDataSourceConfig hsqlDataSourceConfig;

	@Autowired
	public void setHsqlDataSourceConfig(HsqlDataSourceConfig hsqlDataSourceConfig) {
		this.hsqlDataSourceConfig = hsqlDataSourceConfig;
	}
	
	@Bean(name = PersistenceConstants.BEAN_NAME_ENTITY_MANAGER_FACTORY)
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(hsqlDataSourceConfig.createDataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(hsqlDataSourceConfig.createJpaVendorAdaptor());
		entityManagerFactoryBean.setPackagesToScan(PersistenceConstants.PACKAGE_PERSISTENCE_MODEL);
		return entityManagerFactoryBean;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return transactionManager;
	}
	
	@Bean
	@Lazy(false)
	public ResourceDatabasePopulator populateDatabase() throws SQLException {
		
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("textData.sql"));
		
		Connection connection = null;
		
		try {
			connection = DataSourceUtils.getConnection(hsqlDataSourceConfig.createDataSource());
			populator.populate(connection);
		} finally {
			if (connection != null) {
				DataSourceUtils.releaseConnection(connection, hsqlDataSourceConfig.createDataSource());
			}
		}
		return populator;
	}
}
